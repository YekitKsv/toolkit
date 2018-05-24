/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nindeco.toolkit.helper;

import com.google.common.io.Files;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author danabol
 */
public final class ImageFile
{

    //<editor-fold desc="field">
    private File file;
    private String canonicalPath;
    private String absolutePath;
    private String path;
    private String name;
    private String extension;

//</editor-fold>//end field
    //<editor-fold desc="Constructor">
    public ImageFile (File file)
    {
        try
        {
            setFile(file);
            setAbsolutePath(getFile().getAbsolutePath());
            setCanonicalPath(getFile().getCanonicalPath());
            setPath(getFile().getPath());
            setName(getFile().getName());
            setExtension(Files.getFileExtension(getFile().getAbsolutePath()));
        }
        catch (IOException ex)
        {
            Logger.getLogger(ImageFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ImageFile (String path)
    {
        this(new File(path));
    }

    public ImageFile (String directory, String name)
    {
        this(directory + name);
    }

    public ImageFile (String directory, String name, String extension)
    {
        this(directory, name + extension);
    }
//</editor-fold>//end Constructor

    //<editor-fold desc="methods">
    /**
     * Проверяет идентичны ли объекты. Попиксельно сравнивает images. hashCode()
     * не используется
     *
     * @param other файл с изображением класса {@link ImageFile}
     * @return результат проверки соответствия объектов.
     */
    @Override
    public boolean equals (Object other)
    {
        boolean check = false;
        try
        {
            if (other == null)
            {
                return false;
            }
            if (!(other instanceof ImageFile))
            {
                return false;
            }
            ImageFile otherImage = (ImageFile) other;
            BufferedImage image_file1 = ImageIO.read(this.getFile());
            BufferedImage image_file2 = ImageIO.read(otherImage.getFile());
            check = compareImages(image_file1, image_file2);
            return check;
        }
        catch (IOException ex)
        {
            Logger.getLogger(ImageFile.class.getName()).log(Level.SEVERE, null, ex);
            return check;
        }
    }

    @Override
    public int hashCode ()
    {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.file);
        return hash;
    }

    /**
     * Compares two images pixel by pixel.
     *
     * @param imgA the first image.
     * @param imgB the second image.
     * @return whether the images are both the same or not.
     */
    public boolean compareImages (BufferedImage imgA, BufferedImage imgB)
    {
        // The images must be the same size.
        if (imgA.getWidth() != imgB.getWidth() || imgA.getHeight() != imgB.getHeight())
        {
            return false;
        }

        int width = imgA.getWidth();
        int height = imgA.getHeight();

        // Loop over every pixel.
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                // Compare the pixels for equality.
                if (imgA.getRGB(x, y) != imgB.getRGB(x, y))
                {
                    return false;
                }
            }
        }
        return true;
    }
//</editor-fold>//end methods
    //<editor-fold desc="set">

    public void setCanonicalPath (String canonicalPath)
    {
        this.canonicalPath = canonicalPath;
    }

    public void setAbsolutePath (String absolutePath)
    {
        this.absolutePath = absolutePath;
    }

    public void setPath (String path)
    {
        this.path = path;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public void setExtension (String extension)
    {
        this.extension = extension;
    }

    public void setFile (File file)
    {
        this.file = file;
    }
//</editor-fold>//end set
    //<editor-fold desc="get">

    public File getFile ()
    {
        return file;
    }

    public String getPath ()
    {
        return path;
    }

    public String getAbsolutePath ()
    {
        return absolutePath;
    }

    public String getCanonicalPath ()
    {
        return canonicalPath;
    }

    public String getName ()
    {
        return name;
    }

    public String getExtension ()
    {
        return extension;
    }
//</editor-fold>//end get

}
