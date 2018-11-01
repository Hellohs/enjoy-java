package com.dtian.java.sort;

import java.util.Arrays;

/**
 * 快速排序
 * 算法：先找到一个基数，通过和基数的比较，
 *      分成二个数组，
 *      一个比基数大的；一个比基数小的
 * 然后 递归调用 对左边的数组和右边的数组 分别排序
 * 算法复杂度：时间复杂度：O(nlogn)  空间复杂度：O(logn)
 * 参考：https://blog.csdn.net/morewindows/article/details/6684558
 * 其他 可以参照jdk源码 查看 快速排序的优化方式，还有还多变种的优化
 *     例如增加一个基准数，如果没有到基准数，就采用其他的排序方法，来增加效率
 * @author Dingxc
 */
public class QuickSort {

    public static void main(String[] args) {
        QuickSort qs = new QuickSort();
        int[] test = {3, 1, 5, 2, 11, 9, 4, 8, 17, 6, 22, 15, 33, 77, 32, 88, 21, 22, 99, 123, 100, 12};
        long t1 = System.currentTimeMillis();
        qs.quickSort(test, 0, test.length - 1);
        //qs.quickSort1(test, 0, test.length-1);
        long t2 = System.currentTimeMillis();
        System.out.println(Arrays.toString(test));
        System.out.println("use time: " + (t2 - t1));
    }

    /**
     * 将数组分开的算法
     *
     * @param arr   被分的数组
     * @param left  开始位置
     * @param right 结束位置
     * @return
     */
    private int division(int[] arr, int left, int right) {
        //先找到基准点，已这个基准点做数组的左右二边的分隔
        int base = arr[left];
        //这个很重要，如果左边小于右边，才执行循环
        while (left < right) {
            //从右至左，找到比base小的数，交换位置
            while (left < right && base <= arr[right])
                right--;
            //找到比base小的数，交换位置，此时，arr[right]是空的了
            arr[left] = arr[right];
            //从左至右，找到比base大的数
            while (left < right && arr[left] <= base)
                left++;
            //找到比base大的数，需要交换位置
            arr[right] = arr[left];
        }
        //最后把base填到
        arr[left] = base;
        return left;
    }

    /**
     * 分治思想，左右二边数据 分别排序
     *
     * @param arr
     * @param start
     * @param end
     * @return
     */
    public void quickSort(int[] arr, int start, int end) {
        if (start < end) {
            int baseIndex = division(arr, start, end);
            quickSort(arr, start, baseIndex - 1);
            quickSort(arr, baseIndex + 1, end);
        }
    }

    /**
     * 将上面二个函数合起来的写法，并且优化了一些多余的比较
     *
     * @param arr
     * @param left
     * @param right
     */
    public void quickSort1(int[] arr, int left, int right) {
        if (left < right) {
            int base = arr[left];
            int l = left, r = right;
            while (l < r) {
                while (l < r && base <= arr[r])
                    r--;
                arr[l++] = arr[r];
                while (l < r && arr[l] <= base)
                    l++;
                arr[r--] = arr[l];
            }
            arr[l] = base;
            quickSort1(arr, left, l - 1);
            quickSort1(arr, l + 1, right);
        }
    }
}
