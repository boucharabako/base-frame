package com.base.frame.socle.core.utils;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Alassani
 */
public  class Util {
    public final static  Integer[] niveauHabilitations={0,1,2,3,99};
    public static List<Integer> findChildren(Integer i) {
        List<Integer> l = new ArrayList<>();

        for (Integer a : niveauHabilitations) {
            if (Integer.valueOf(a) <= i) {
                l.add(Integer.valueOf(a));
            } else {
                return l;
            }
        }
        return l;
    }


}
