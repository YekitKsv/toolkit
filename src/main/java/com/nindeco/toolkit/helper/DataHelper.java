/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nindeco.toolkit.helper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author danabol
 */
public class DataHelper
{

    public static class Create
    {

        /**
         * Рефлексией присваивает значения к полям экземляра класса.<br>
         * Поля определяются с помощью имен. Сравниваются с именем слобца из
         * ключа(в ключе содержится название столбца).
         *
         * @param <E>      обобщенный тип.
         * @param list_map список с данными для заполнения.
         * @param klass    класс в который необходимо конвертировать данные из
         *                 базы.
         * @return Лист заполненный экземплярами класса.
         */
        public <E> List<E> instanceList (List<Map<String, Object>> list_map, Class<E> klass)
        {
            try
            {
                List<E> data = new ArrayList<>();
                Field[] publicFields = klass.getFields();
                for (int i = 0; i < list_map.size(); i++)
                {
                    Map<String, Object> map_obj = list_map.get(i);
                    E instance = klass.newInstance();
                    for (Map.Entry<String, Object> entry : map_obj.entrySet())
                    {
                        int length = publicFields.length;
                        for (int j = 1; j <= length; j++)
                        {
                            if (entry.getKey().equals(publicFields[j - 1].getName()))
                            {
                                publicFields[j - 1].set(instance, castObject(entry.getValue().getClass(), entry.getValue()));
                                break;
                            }
                            else if (entry.getKey().equals(publicFields[(length - j)].getName()))
                            {
                                publicFields[(length - j)].set(instance, castObject(entry.getValue().getClass(), entry.getValue()));
                                break;
                            }
                        }
                    }
                    data.add(instance);
                }
                return data;
            }
            catch (IllegalAccessException | IllegalArgumentException | InstantiationException e)
            {
                e.printStackTrace();
                return null;
            }
        }

        /**
         * Автоматическое приведение типов.
         *
         * @param <T>
         * @param klass  тип в который необходимо привести объект.
         * @param object объект приведения.
         * @return
         */
        public <T> T castObject (Class<T> klass, Object object)
        {
            return (T) object;
        }
    }
}
