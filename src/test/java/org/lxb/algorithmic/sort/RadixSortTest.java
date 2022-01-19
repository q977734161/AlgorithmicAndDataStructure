package org.lxb.algorithmic.sort;

import org.junit.Test;

public class RadixSortTest extends SortBase {

    public RadixSortTest() {
        UNSORT_DATA = new Integer[]{3,112,34,104,72,14,7,85,900,2222,0};
        EXPECT_DATA = new Integer[]{0,3,7,14,34,72,85,104,112,900,2222};
    }

    @Override
    public Integer[] sort(Integer[] data) {
        QuickSortOfLomuto<Integer> sort = new QuickSortOfLomuto<>();
        return sort.sort(data,0,data.length-1);
    }

    @Test
    public void TestDefault() {
        RadixSortTest shellSortTest = new RadixSortTest();
        shellSortTest.compareResult();
    }

}
