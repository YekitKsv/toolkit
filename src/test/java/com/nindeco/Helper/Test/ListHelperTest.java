/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nindeco.Helper.Test;

import static com.nindeco.toolkit.helper.ListHelper.expandList;
import static com.nindeco.toolkit.helper.ListHelper.mergList;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import static junit.framework.Assert.fail;
import org.junit.Test;

/**
 *
 * @author danabol
 */
public class ListHelperTest
{
        //<editor-fold desc="Test">

    /**
     * Проверка метода {@link ListHelper#mergList(java.util.List, java.util.List)
     * }. На выходе должно быть:<br>
     * 1 2 3 4 11 22 33 44<br>
     * 1 2 3 4 55 66 77 88<br>
     * 5 6 7 8 11 22 33 44<br>
     * 5 6 7 8 55 66 77 88<br>
     */
    @Test
    public void testMergingList ()
    {
        LinkedList<Integer[]> lst_1 = new LinkedList<>();
        lst_1.add(new Integer[]
        {
            1, 2, 3, 4
        });
        lst_1.add(new Integer[]
        {
            5, 6, 7, 8
        });
        LinkedList<Integer[]> lst_2 = new LinkedList<>();
        lst_2.add(new Integer[]
        {
            11, 22, 33, 44
        });
        lst_2.add(new Integer[]
        {
            55, 66, 77, 88
        });
        List<Object[]> out_list = expandList(lst_1, lst_2);
        int count = 0;
        for (int i = 0; i < out_list.size(); i++)
        {
            Integer[] obj1 = (Integer[]) out_list.get(i)[0];
            for (int j = 0; j < lst_1.size(); j++)
            {
                Integer[] one = (Integer[]) lst_1.get(j);
                if (obj1.equals(one))
                {
                    count++;
                    break;
                }
            }
            Integer[] obj2 = (Integer[]) out_list.get(i)[1];
            for (int j = 0; j < lst_2.size(); j++)
            {
                Integer[] two = (Integer[]) lst_2.get(j);
                if (obj2.equals(two))
                {
                    count++;
                    break;
                }
            }
        }
        if (count != 8)
        {
            fail("ListHelper.mergingList don't work." + count);
        }
    }

    /**
     * Проверка метода {@link ListHelper#expandList(java.util.List, java.util.List)
     * }. На выходе должно быть:<br>
     * 1 1<br>
     * 2 2<br>
     * 3 1<br>
     * 4 2<br>
     * 5 1<br>
     */
    @Test
    public void testExpandList ()
    {
        List<Integer> listL = new ArrayList<>();
        listL.add(1);
        listL.add(2);
        listL.add(3);
        listL.add(4);
        listL.add(5);
        List<Integer> listS = new ArrayList<>();
        listS.add(1);
        listS.add(2);
        List<Object[]> listRes = mergList(listL, listS);
        listRes.forEach((obj) ->
        {
            out.println(obj[0] + " " + obj[1]);
        });
    }
    //</editor-fold>
}
