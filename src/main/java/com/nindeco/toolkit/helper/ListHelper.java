/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nindeco.toolkit.helper;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс-помощник для различного взаимодействия с листами.
 *
 * @author danabol
 */
public class ListHelper
{

    //<editor-fold desc="Field">
//</editor-fold>
    //<editor-fold desc="Method">
    /**
     * Метод выполняет слияние 2-х списков в один ArrayList<Object[]>
     * Умножает список в 2 раза.
     *
     * @param dataBrowser данные браузеров.
     * @param inData      принимает данные для теста
     * @return - возвращает LinkedList<Object[]>.
     */
    public static List<Object[]> expandList (List<?> dataBrowser, List<?> inData)
    {
        try
        {
            List<Object[]> outData = new ArrayList<>();
            if (!dataBrowser.isEmpty())
            {
                dataBrowser.forEach((browser) ->
                {
                    if (!inData.isEmpty())
                    {
                        inData.forEach((in_data) ->
                        {
                            outData.add(new Object[]
                            {
                                browser, in_data
                            });
                        });
                    }
                });
            }
            return outData;
        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex);
        }
    }

    /**
     *
     * @param list1
     * @param list2
     * @return
     */
    public static List<Object[]> mergList (List<?> list1, List<?> list2)
    {

        try
        {
            List<Object[]> list = new ArrayList<>();
            int size_list1 = list1.size();
            int size_list2 = list2.size();
            if (size_list1 > size_list2)
            {
                for (int i = 0, j = 0; i < size_list1; i++, j++)
                {
                    //продолжаем заполнять литс1 данными из листа 2 (с 0) 
                    if (j >= size_list2)
                    {
                        j = 0;
                    }
                    Object obj1 = list1.get(i);
                    Object obj2 = list2.get(j);
                    list.add(new Object[]
                    {
                        obj1, obj2
                    });

                }
            }
            else if (size_list1 <= size_list2)
            {
                for (int i = 0; i < size_list1; i++)
                {
                    Object obj1 = list1.get(i);
                    Object obj2 = list2.get(i);
                    list.add(new Object[]
                    {
                        obj1, obj2
                    });

                }
            }
            return list;
        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex);
        }
    }
//</editor-fold>

}
