/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nindeco.Helper.Test;

import com.nindeco.toolkit.helper.ImageFile;
import java.io.File;
import static java.lang.System.out;
import org.junit.Test;

/**
 *
 * @author danabol
 */
public class ImageFileTest
{

    @Test
    public void imageTest ()
    {
        ImageFile img = new ImageFile(new File("src/test/resources/images/1.png"));
        ImageFile img1 = new ImageFile("src/test/resources/images/2.png");
        ImageFile img2 = new ImageFile("src/test/resources/images/", "3.png");
        ImageFile img3 = new ImageFile("src/test/resources/images/", "4", ".png");
        out.println(img.equals(img1)); //false
        out.println(img1.equals(new ImageFile("src/test/resources/images/dfg.png")));//true
        out.println(img2.equals(img3));//false
        out.println(img3.equals(img1));//false
    }

}
