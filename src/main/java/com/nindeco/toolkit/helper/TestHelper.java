/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nindeco.toolkit.helper;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;

/**
 *
 * @author danabol
 */
public class TestHelper
{

    public static class out
    {

        /**
         * Распечатывает список данных.
         *
         * @param <E>
         * @param data принимает список экземпляров класса данных.
         */
        public static <E> void printList (List<E> data)
        {
            try
            {
                if (data.isEmpty())
                {
                    throw new NullPointerException(Test.class.getName() + " -> data is empty.");
                }
                Iterator<E> iter = data.iterator();
                while (iter.hasNext())
                {
                    E instance = iter.next();
                    print(instance);
                }
            }
            catch (IllegalArgumentException ex)
            {
                Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        /**
         * Распечатывает экземпляр класса данных.
         *
         * @param <E>
         * @param data принимает список экземпляров класса данных.
         */
        public static <E> void print (E data)
        {
            try
            {
                if (data == null)
                {
                    throw new NullPointerException(Test.class.getName() + " -> data is empty.");
                }
                if (data instanceof List)
                {
                    throw new IllegalArgumentException("Используйте printList(List<E> data) для вывода списка объектов класса.");
                }
                E instance = data;
                Class klass = instance.getClass();
                Field[] fields = klass.getFields();
                System.out.print(klass.getName() + " ->  { ");
                for (Field field : fields)
                {
                    Object value = field.get(instance);
                    System.out.print(field.getName() + ": " + value.toString() + "; ");
                }
                System.out.print("}");
                System.out.println();

            }
            catch (IllegalArgumentException | IllegalAccessException ex)
            {
                Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//end out
}
