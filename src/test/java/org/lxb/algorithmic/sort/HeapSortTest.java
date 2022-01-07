package org.lxb.algorithmic.sort;

import org.junit.Test;

public class HeapSortTest extends SortBase {

    @Override
    public Integer[] sort(Integer[] data) {
        HeapSort<Integer> sort = new HeapSort<>();
        return sort.sort(data);
    }

    @Test
    public void Test() {
        HeapSortTest quickSortTest = new HeapSortTest();
        quickSortTest.compareResult();
    }
}