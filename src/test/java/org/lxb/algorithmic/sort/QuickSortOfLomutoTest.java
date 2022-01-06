package org.lxb.algorithmic.sort;

import org.junit.Test;

public class QuickSortOfLomutoTest extends SortBase {
    @Override
    public Integer[] sort(Integer[] data) {
        QuickSortOfLomuto<Integer> sort = new QuickSortOfLomuto<>();
        return sort.sort(data,0,data.length-1);
    }

    @Test
    public void Test() {
        QuickSortOfLomutoTest quickSortTest = new QuickSortOfLomutoTest();
        quickSortTest.compareResult();
    }
}
