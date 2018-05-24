/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nindeco.Helper.Test;

import com.nindeco.toolkit.helper.RandomString;
import static java.lang.System.out;
import java.util.Random;
import org.junit.Test;

/**
 *
 * @author danabol
 */
public class RandomStringTest
{
    @Test
    public void randomStringTest() throws NoSuchMethodException
    {
        RandomString rs = new RandomString();
        Random rnd = new Random();
        RandomString rs1 = new RandomString(10);
        RandomString rs2 = new RandomString(10,rnd);
        RandomString rs3 = new RandomString(10,rnd,"81681thrtu56u444>?M<M684/*");
        out.println(rs.getClass().getConstructor() + " = " + rs.nextString());
        out.println(rs1.getClass().getConstructor(int.class) + " = " + rs1.nextString());
        out.println(rs2.getClass().getConstructor(int.class, Random.class) + " = " + rs2.nextString());
        out.println(rs3.getClass().getConstructor(int.class, Random.class, String.class) + " = " + rs3.nextString());
    }
}
