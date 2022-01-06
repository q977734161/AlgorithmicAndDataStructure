package org.lxb.algorithmic.sort;

import org.junit.Test;

public class QuickSortOfHoareTest extends SortBase {
    @Override
    public Integer[] sort(Integer[] data) {
        QuickSortOfHoare<Integer> sort = new QuickSortOfHoare<>();
        return sort.sort(data, QuickSortOfHoare.PivotEnum.RANDOM);
    }

    @Test
    public void Test() {
        QuickSortOfHoareTest quickSortTest = new QuickSortOfHoareTest();
        quickSortTest.compareResult();
    }
}
