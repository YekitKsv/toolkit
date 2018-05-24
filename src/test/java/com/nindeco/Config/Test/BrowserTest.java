/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nindeco.Config.Test;

import static com.codeborne.selenide.Selenide.open;
import org.junit.Test;
import com.nindeco.toolkit.Browser;
import static java.lang.System.out;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 *
 * @author danabol
 */
public class BrowserTest
{
    /**
     * Тест запуска локального браузера
     */
    @Test
    public void testConnect()
    {
       Browser browser = new Browser();
       browser.Connect();
       open("http://example.com");
       browser.close();
    }
    /**
     * Тест запуска браузера на сервере.
     * @throws InterruptedException 
     */
    @Test
    public void testConnectRemote() throws InterruptedException
    {
        RemoteWebDriver driver = null;
        Browser browser = new Browser();
        browser.ConnectRemote(driver);
        open("http://example.com");
        browser.close();
    }
    /**
     * Тест запуска браузера на сервере.
     * @throws InterruptedException 
     */
    @Test
    public void testSetSITE_URL() throws InterruptedException
    {
       Browser browser = new Browser();
       browser.setSITE_URL("http://example.com");
       out.println(browser.getSITE_URL());
       browser.close();
    }
}
