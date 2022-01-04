package org.lxb.algorithmic.sort;

import org.junit.Test;

public class InsertionSortTest extends SortBase{

    private String type = "default";

    @Override
    public Integer[] sort(Integer[] data) {
        InsertionSort<Integer> sort = new InsertionSort<>();
        if(type.equals("default")) {
            return sort.sort(data);
        } else {
            return sort.sortOther(data);
        }
    }

    @Test
    public void Test() {
        InsertionSortTest insertionSortTest = new InsertionSortTest();
        insertionSortTest.compareResult();
    }

    @Test
    public void TestOther() {
        InsertionSortTest insertionSortTest = new InsertionSortTest();
        insertionSortTest.type = "other";
        insertionSortTest.compareResult();
    }
}
