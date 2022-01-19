package org.lxb.algorithmic.sort;

import org.junit.Test;

public class MergeSortTest  extends SortBase{

    private String type = "default";

    @Override
    public Integer[] sort(Integer[] data) {
        MergeSort<Integer> sort = new MergeSort<>();
        if(type.equals("default")) {
            return sort.sort(data,0,data.length - 1, MergeSort.SPACE_TYPE.IN_PLACE);
        } else {
            return sort.sort(data,0,data.length - 1, MergeSort.SPACE_TYPE.NOT_IN_PLACE);
        }
    }

    @Test
    public void Test() {
        MergeSortTest insertionSortTest = new MergeSortTest();
        insertionSortTest.compareResult();
    }

    @Test
    public void TestOther() {
        MergeSortTest insertionSortTest = new MergeSortTest();
        insertionSortTest.type = "other";
        insertionSortTest.compareResult();
    }
}
