/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nindeco.toolkit;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import java.io.File;
import java.net.URI;
import java.util.logging.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Конфигурация браузера. В зависимости от проекта необходимо указать URL сайта
 * в теле метода Connect() в методе open(URL).
 *
 * @author danabol
 * @version 1.0.0
 */
public class Browser
{

    //<editor-fold defaultstate="collapsed" desc="Field">
    /**
     * Путь до драйвера браузера в проекте.
     */
    private String pathToDriver = "src/main/resources/drivers/chromedriver";
    /**
     * URL сайта.
     */
    private String SITE_URL = GlobalVariable.TEST_SITE_URL;
    /**
     * URL сайта. Слэш в конце урла обязательный.
     */
    private String REMOTE_URL = GlobalVariable.REMOTE_URL;

    /**
     * Имя драйвера.
     */
    private String nameDriver = "webdriver.chrome.driver";
    /**
     * Имя браузера.
     */
    private String nameBrowser = "chrome";
    /**
     * Версия браузера.
     */
    private String versionBrowser = "";
    /**
     * Страница url-а.
     */
    private String page = "";
    /**
     * Работа браузера в фоне.
     */
    private boolean headless = false;
    /**
     * Закрытие окна браузера после выполнения теста (закрывает при false).
     */
    private boolean holdBrowserOpen = false;
    /**
     * Файл драйвера.
     */
    private File file;
    /**
     * Отключить сертификат безопасности браузера.
     */
    private boolean acceptInsecureCerts = false;
    /**
     * Включает VNC браузера
     */
    private boolean enableVNC = true;

// </editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Constructor">  
    /**
     * Конструктор класса. Разрешает логирование класса по умолчанию.
     */
    public Browser ()
    {

    }

// </editor-fold>
    //<editor-fold desc="Methods">  
    /**
     * Устанавливает локальное подключение с браузером.<br>
     * Осторожно! Устанавливает 1 поток на время работы метода.
     */
    public void Connect ()
    {
        try
        {
            System.setProperty("junit.parallel.threads", "1");
            file = new File(pathToDriver);
            System.setProperty(nameDriver, file.getAbsolutePath());
            System.setProperty("selenide.browser", getNameBrowser());
            Configuration.browserVersion = "65.0";
            Configuration.headless = getHeadless();
            Configuration.holdBrowserOpen = getHoldBrowserOpen();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            System.setProperty("junit.parallel.threads", String.valueOf(GlobalVariable.THREAD_COUNT));
        }
    }

    /**
     * Устанавливает удаленное подключение с браузером(на полный экран).<br>
     * <p> Вызывать из JUnit @Before
     * <p> 1 экземпляр на 1 тест.
     * <p> Или вызвать из JUnit @BeforeClass
     * <p> 1 экземпляр на все тесты
     * <p> 1920x1080 разрешение по умолчанию.
     * @param driver драйвер передается в Selenide.
     */
    public void ConnectRemote (RemoteWebDriver driver)
    {
        try
        {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("enableVNC", true);//можно установить false для увелечения скорости теста
            capabilities.setBrowserName(nameBrowser);
            capabilities.setVersion(versionBrowser);
            capabilities.setAcceptInsecureCerts(getAcceptInsecureCerts());
            driver = new RemoteWebDriver(URI.create(REMOTE_URL).toURL(), capabilities);
            Dimension dimension = new Dimension(1920, 1080);//если в тесте требуется менять разрешение, то закомментить этот объект класса
            driver.manage().window().setSize(dimension);//этот метод тоже и вызывать его из @Before 
            WebDriverRunner.setWebDriver(driver);//передаем драйвер в Selenide 
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    /**
     * Устанавливает удаленное подключение с браузером(на полный экран).
     * Сохранят экземпляр драйвера при прогоне тестов. Зависит от кол-ва
     * потоков. Используется в JUnit @BeforeClass
     */
    public void ConnectRemoteWithSafeThread ()
    {
        try
        {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("enableVNC", getEnableVNC());//можно установить в false для увелечения скорости теста
            capabilities.setCapability("acceptInsecureCerts", getAcceptInsecureCerts());
            Configuration.browserCapabilities = capabilities;
            Configuration.browser = getNameBrowser();
            Configuration.browserVersion = getVersionBrowser();
            Configuration.remote = URI.create(REMOTE_URL).toString();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    /**
     * Запускае браузер локально для отладки тестов.
     */
    public void ConnectForDebug ()
    {
        try
        {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("acceptInsecureCerts", getAcceptInsecureCerts());
            System.setProperty("junit.parallel.threads", "1");
            file = new File(pathToDriver);
            System.setProperty(nameDriver, file.getAbsolutePath());
            System.setProperty("selenide.browser", getNameBrowser());
            Configuration.browserVersion = "65.0";
            Configuration.headless = false;
            Configuration.holdBrowserOpen = true;
            Configuration.browserCapabilities = capabilities;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            System.setProperty("junit.parallel.threads", String.valueOf(GlobalVariable.THREAD_COUNT));
        }
    }

    /**
     * Close browser.
     */
    public void close ()
    {
        Selenide.close();
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="get">
    public boolean getEnableVNC ()
    {
        return enableVNC;
    }

    /**
     *
     * @return true acceptInsecureCerts
     */
    public boolean getAcceptInsecureCerts ()
    {
        return acceptInsecureCerts;
    }

    /**
     *
     * @return url remote
     */
    public String getREMOTE_URL ()
    {
        return REMOTE_URL;
    }

    /**
     * Метод получает значение парамера {@link Browser#holdBrowserOpen}
     *
     * @return - возвращает false, при закрытии браузера после выполнения теста.
     */
    public boolean getHoldBrowserOpen ()
    {
        return holdBrowserOpen;
    }

    /**
     * Метод получает значение парамера {@link Browser#SITE_URL} Если задана
     * страница {@link Browser#page}, то вернет URL/PAGE/
     *
     * @return - возвращает url сайта проекта.
     */
    public String getSITE_URL ()
    {
        if (page == null || page == "")
        {
            return SITE_URL;
        }
        else
        {
            return SITE_URL + page;
        }
    }

    /**
     * Метод получает значение парамера {@link Browser#pathToDriver}
     *
     * @return - возвращает относительный путь до драйвера.
     */
    public String getPathToDriver ()
    {
        return pathToDriver;
    }

    /**
     * Метод получает значение парамера {@link Browser#versionBrowser}
     *
     * @return - возвращает относительный путь до драйвера.
     */
    public String getVersionBrowser ()
    {
        return versionBrowser;
    }

    /**
     * Метод получает значение парамера {@link Browser#file}
     *
     * @return - возвращает абсолютный путь до драйвера.
     */
    public String getAbsolutePathToDriver ()
    {
        this.file = new File(pathToDriver);
        return file.getAbsolutePath();
    }

    /**
     * Метод получает значение парамера {@link Browser#headless}
     *
     * @return - true, если браузер запускается в фоновом режиме.
     */
    public boolean getHeadless ()
    {
        return headless;
    }

    /**
     * Метод получает значение парамера {@link Browser#page}
     *
     * @return - возвращает стартовую страницу.
     */
    public String getPage ()
    {
        return page;
    }

    /**
     * Метод получает значение парамера {@link Browser#nameDriver}
     *
     * @return - возвращает имя драйвера.
     */
    public String getNameDriver ()
    {
        return nameDriver;
    }

    /**
     * Метод получает значение парамера {@link Browser#nameBrowser}
     *
     * @return - возвращает имя браузера.
     */
    public String getNameBrowser ()
    {
        return nameBrowser;
    }
// </editor-fold>//end get

    // <editor-fold defaultstate="collapsed" desc="set">
    /**
     * Включает VNC браузера.
     *
     * @param enableVNC true on
     */
    public void setEnableVNC (boolean enableVNC)
    {
        this.enableVNC = enableVNC;
    }

    /**
     * Отключить сертификат безопасности браузера.
     *
     * @param acceptInsecureCerts true off
     */
    public void setAcceptInsecureCerts (boolean acceptInsecureCerts)
    {
        this.acceptInsecureCerts = acceptInsecureCerts;
    }

    /**
     * Устанавливает remote
     *
     * @param REMOTE_URL url remote
     */
    public void setREMOTE_URL (String REMOTE_URL)
    {
        int l = REMOTE_URL.length() - 1;
        if (REMOTE_URL.charAt(l) == '/')
        {
            this.REMOTE_URL = REMOTE_URL;
        }
        else
        {
            this.REMOTE_URL = REMOTE_URL + '/';
        }
    }

    /**
     * Метод устанавливает закрытие браузера после выполнения теста. Изменяет
     * параметр {@link Browser#holdBrowserOpen}
     *
     * @param holdBrowserOpen true, браузер не закрывается. false, браузер
     *                        закрывается.
     */
    public void setHoldBrowserOpen (boolean holdBrowserOpen)
    {
        this.holdBrowserOpen = holdBrowserOpen;
    }

    /**
     * Метод устанавливает относительный путь до файла драйвера браузера.
     * Изменяет параметр {@link Browser#pathToDriver} По умолчанию папка с
     * драйверами: "src/test/resources/drivers".
     *
     * @param pathToDriver относительный путь файла драйвера.
     */
    public void setPathToDriver (String pathToDriver)
    {
        this.pathToDriver = pathToDriver;
        this.file = new File(pathToDriver);
    }

    /**
     * Метод устанавливает запуск браузера в фоновом режиме. Изменяет параметр
     * {@link Browser#headless}
     *
     * @param headless true, браузер запускается в фоновом режиме.
     */
    public void setHeadless (boolean headless)
    {
        this.headless = headless;
    }

    /**
     * Метод устанавливает стартовую страницу сайта. Изменяет параметр
     * {@link Browser#page}
     *
     * @param page страница, которую нужно открыть внутри URL сайта.
     */
    public void setPage (String page)
    {
        this.page = page;
    }

    /**
     * Метод устанавливает версию браузера. Изменяет параметр
     * {@link Browser#versionBrowser}
     *
     * @param versionBrowser страница, которую нужно открыть внутри URL сайта.
     */
    public void setVersionBrowser (String versionBrowser)
    {
        this.versionBrowser = versionBrowser;
    }

    /**
     * Метод устанавливает имя драйвера. Изменяет параметр
     * {@link Browser#nameDriver}
     *
     * @param nameDriver имя драйвера, вида "webdriver.chrome.driver".
     */
    public void setNameDriver (String nameDriver)
    {
        this.nameDriver = nameDriver;
    }

    /**
     * Метод устанавливает имя браузера. Изменяет параметр
     * {@link Browser#nameBrowser}
     *
     * @param nameBrowser имя браузера, вида "chrome".
     */
    public void setNameBrowser (String nameBrowser)
    {
        this.nameBrowser = nameBrowser;
    }

    /**
     * Метод устанавливает URL сайта. Изменяет параметр {@link Browser#SITE_URL}
     *
     * @param SITE_URL URL сайта, вида "http://example.com/", в конце url "/"
     *                 обязателен.
     */
    public void setSITE_URL (String SITE_URL)
    {
        int l = SITE_URL.length() - 1;
        if (SITE_URL.charAt(l) == '/')
        {
            this.SITE_URL = SITE_URL;
        }
        else
        {
            this.SITE_URL = SITE_URL + '/';
        }
    }
// </editor-fold>//end set
}
