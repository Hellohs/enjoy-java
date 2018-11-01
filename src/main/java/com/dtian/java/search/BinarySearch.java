package com.dtian.java.search;

/**
 * 二分查找
 * 算法原理：
 *     对于有序排列的数组，我们可以通过比较 (low+high)/2
 *     来定位 目标数值在哪个区间
 *     最后通过循环，来得到 target所在位置
 * 需要注意的是，如果数组的大小是 2的31次方，
 * 刚好要找的数据在倒数第二位，那么 (2的31次方-2)+(2的31次方-1)
 * 肯定超过2的31次方了，会溢出，
 * 详情可以看：http://www.sohu.com/a/215243987_465914?tdsourcetag=s_pctim_aiomsg&qq-pf-to=pcqq.c2c
 * 为了避免这个问题，可以通过使用位操作符，来避免这个问题
 * @author Dingxc
 */
public class BinarySearch {
    public static void main(String[] args) {
        //int[] test = {1,2,3,4,5,6,7,8};
        int[] test = {3,4,8,8,8,8,8,10,13,14};
        int index = BinarySearch.binarySearch1(test, 8);
        System.out.println("index: " + index);
    }

    public static int binarySearch(int[] arr, int target) {
        int low = 0;//起始位置
        int high = arr.length - 1;//最后位置
        while (low <= high) {
            //获取mid 的下标
            int mid = (low + high) >>> 1;
            //获取下面为mid的值
            int midValue = arr[mid];
            //判断target所在区间
            if (midValue < target) {
                //中间值 < 目标值，在后区间
                low = mid + 1;
            } else if (midValue > target) {
                //中间值 > 目标值，在前区间
                high = mid - 1;
            } else {
                return mid;
            }
        }
        //如果没找到
        return -1;
    }

    /**
     * 对于存在重复情况的数组，需要找到最前面一个的目标下标
     * 就需要对上面binarySearch 做一下变化
     * @param arr
     * @param target
     * @return
     */
    public static int binarySearch1(int[] arr, int target) {
        int low = 0;//起始位置
        int high = arr.length - 1;//最后位置
        while (low <= high) {
            //获取mid 的下标
            int mid = (low + high) >>> 1;
            //获取下面为mid的值
            int midValue = arr[mid];
            //判断target所在区间
            if (midValue < target) {
                //中间值 < 目标值，在后区间
                low = mid + 1;
            } else if (midValue > target) {
                //中间值 > 目标值，在前区间
                high = mid - 1;
            } else {
                //有改变的地方
                while (mid >= 0) {
                    if (arr[mid] != target)
                        break;
                    mid--;
                }
                if (mid <= -1)
                    return 0;
                return mid + 1;
            }
        }
        //如果没找到
        return -1;
    }
}
