package org.lxb.algorithmic.sort;

import org.junit.Test;

public class QuickSortTest extends SortBase {
    @Override
    public Integer[] sort(Integer[] data) {
        QuckSort<Integer> sort = new QuckSort<>();
        return sort.sort(data, QuckSort.PivotEnum.RANDOM);
    }

    @Test
    public void Test() {
        QuickSortTest quickSortTest = new QuickSortTest();
        quickSortTest.compareResult();
    }
}
