/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nindeco.Config.Test;

import com.nindeco.toolkit.Parallelized;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Random;
import org.bouncycastle.crypto.prng.FixedSecureRandom;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author danabol
 */
@RunWith(Parallelized.class)
public class ParallelizedTest
{

    public Integer numberThread;

    @Parallelized.Parameters
    public static ArrayList<?> data ()
    {
        ArrayList<Integer> data = new ArrayList<>();
        for (int i = 1; i < 6; i++)
        {
            data.add(i);
        }
        return data;
    }

    public ParallelizedTest (Integer countThread)
    {
        this.numberThread = countThread;
    }

    @Test
    public void parallelizedTest () throws InterruptedException
    {
        Random rnd = new Random();
        Thread.sleep((rnd.nextInt(250-100)+100));
        out.println("thread id: " + Thread.currentThread().getId()
                + " |  thread name: " + Thread.currentThread().getName()
                + " |  thread number: " + numberThread);
    }
}
