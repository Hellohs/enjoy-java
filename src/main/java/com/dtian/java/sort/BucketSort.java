package com.dtian.java.sort;

import java.util.Arrays;

/**
 * 桶排序
 * 算法原理：
 *     计数排序，假设我们有数组A arra：{2,1,3,4,5,7,9,7}
 *     我们把数组A的数据，用数组B的下表来表示。所以，我们要申请一个新的数组
 *     首先找出 最大的数：9，申请一个长度 为 max+1的数组B,arrb = new int[max+1]
 *     然后我们遍历数组A，将arrb[arra[i]]++ ，用来表示，如果存在数据2，就讲数组B的 index2 所在位置 +1
 *     最后遍历数组B，将数组B的下标，填到数组A 中
 * 复杂度：时间复杂度：O(n)  空间复杂度：O(1)，与数组有多少个元素无关
 * @author Dingxc
 */
public class BucketSort {

    public static void main(String[] args) {
        BucketSort bucketSort = new BucketSort();
        int[] test = {2,3,7,7,1,4,8};
        bucketSort.bucketSort(test);
        System.out.println(Arrays.toString(test));
    }

    /**
     * 桶排序
     * @param arr
     */
    public void bucketSort(int[] arr) {
        int max = findMaxVal(arr);
        int[] bucket = new int[max + 1];
        for (int i = 0; i < arr.length; i++) {
            //往桶中，添加数据
            bucket[arr[i]]++;
        }
        //把数据放回去
        int index = 0;
        for (int j = 0; j < bucket.length; j++) {
            while (bucket[j]-- > 0) {
                arr[index++] = j;
            }
        }
    }

    /**
     * 找到数组中最大的值
     *
     * @param arr
     * @return
     */
    private int findMaxVal(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max)
                max = arr[i];
        }
        return max;
    }
}
