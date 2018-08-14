package com.dtian.java.core_java.collection.array;

/**
 * 数组工具类
 * //参照 {@link java.util.Arrays}
 */
public class ArraysUtil {
    /***
     * 比较二个Object数组 是否完全相等
     * @param a1
     * @param a2
     * @return
     */

    public static boolean equals(Object[] a1, Object[] a2) {
        //因为面试时被要求写，当时写的不好，特记录下来，
        if (a1 == a2)
            return true;

        if (a1 == null || a2 == null)
            return false;

        int length1 = a1.length;
        if (a2.length != length1)
            return false;

        for (int i = 0; i < length1; i++) {
            Object o1 = a1[i];
            Object o2 = a2[i];
            //需要比较非空情况下 equals 和
            //空情况下 是不是 都为空
            if (!(o1 == null ? o2 == null : o1.equals(o2)))
                return false;
        }
        return true;
    }
}
