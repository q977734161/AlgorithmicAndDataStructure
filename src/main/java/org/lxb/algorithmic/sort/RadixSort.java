package org.lxb.algorithmic.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * 基数排序（英语：Radix sort）是一种非比较型整数排序算法，其原理是将整数按位数切割成不同的数字，然后按每个位数分别比较。
 * 由于整数也可以表达字符串（比如名字或日期）和特定格式的浮点数，所以基数排序也不是只能使用于整数。
 * 基数排序的发明可以追溯到1887年赫尔曼·何乐礼在打孔卡片制表机（Tabulation Machine）上的贡献[1]。
 * 它是这样实现的：将所有待比较数值（正整数）统一为同样的数位长度，数位较短的数前面补零。
 * 然后，从最低位开始，依次进行一次排序。这样从最低位排序一直到最高位排序完成以后，数列就变成一个有序序列。
 *
 * 基数排序的方式可以采用LSD（Least significant digital）或MSD（Most significant digital），LSD的排序方式由键值的最右边开始，
 * 而MSD则相反，由键值的最左边开始。
 *
 * 最坏时间复杂度	O(kN)
 * 空间复杂度	O(k+N)
 * 其中 n是排序元素个数， k是数字位数。
 */
public class RadixSort {

    static final int BUCKETS_NUM = 10;

    public Integer[] sort(Integer[] unsortData) {
        int maxNumberOfDigital = getMaxNumberOfDigital(unsortData);
        List<Integer>[] buckets = new List[BUCKETS_NUM];
        for (int i = 0; i < maxNumberOfDigital; i++) {
            int mod = (int) Math.pow(10,i + 1);
            int divisor = (int) Math.pow(10,i);
            for (int j = 0; j < unsortData.length; j++) {
                int remainder = unsortData[j] % mod / divisor;
                if (buckets[remainder] == null) {
                    buckets[remainder] = new ArrayList<>();
                }
                buckets[remainder].add(unsortData[j]);
            }
        }
        int arrayIndex = 0;
        for (int i = 0; i < BUCKETS_NUM; i++) {
            if(buckets[i] != null) {
                for(Integer val : buckets[i]) {
                    unsortData[arrayIndex++] = val;
                }
            }
        }
        return  unsortData;
    }

    private int getMaxNumberOfDigital(Integer[] unsortData) {
        int max = Integer.MIN_VALUE;
        for (Integer i: unsortData) {
            int tempValue = (int) Math.log10(i) + 1;
            if(tempValue > max) {
                max = tempValue;
            }
        }
        return max;
    }
}
