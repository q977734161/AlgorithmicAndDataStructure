package org.lxb.algorithmic.sort;

import org.junit.Test;

public class CountingSortTest extends SortBase{
    @Override
    public Integer[] sort(Integer[] data) {
        CountingSort sort = new CountingSort();
        return sort.sort(data);
    }

    @Test
    public void Test() {
        CountingSortTest quickSortTest = new CountingSortTest();
        quickSortTest.compareResult();
    }
}
