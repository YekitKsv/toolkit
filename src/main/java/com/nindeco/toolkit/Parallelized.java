/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nindeco.toolkit;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.runners.Parameterized;
import org.junit.runners.model.RunnerScheduler;

/**
 *
 * @author danabol
 */
public class Parallelized extends Parameterized
{

    private static class ThreadPoolScheduler implements RunnerScheduler
    {

        private ExecutorService executor;

        public ThreadPoolScheduler()
        {
            System.setProperty("junit.parallel.threads", String.valueOf(GlobalVariable.THREAD_COUNT));
            String threads = System.getProperty("junit.parallel.threads");//кол-во потоков (больше 10 не выставлять, т.к придется менять конфиг Selenoid и комп повесится от нагрузки)
            int numThreads = Integer.parseInt(threads);
            executor = Executors.newFixedThreadPool(numThreads);
        }

        @Override
        public void finished()
        {
            executor.shutdown();
            try
            {
                executor.awaitTermination(10, TimeUnit.MINUTES);
            }
            catch (InterruptedException exc)
            {
                throw new RuntimeException(exc);
            }
        }

        @Override
        public void schedule(Runnable childStatement)
        {
            executor.submit(childStatement);
        }
    }

    public Parallelized(Class<?> klass) throws Throwable
    {
        super(klass);
        setScheduler(new ThreadPoolScheduler());
    }
}
