package org.lxb.algorithmic.sort;

import org.junit.Test;

public class BubbleSortTest extends SortBase {

    String testMethod = "sort";

    @Override
    public Integer[] sort(Integer[] data) {
        BubbleSort<Integer> sort = new BubbleSort<>();
        if(testMethod.equals("sort")) {
            return sort.sort(data);
        } else if(testMethod.equals("sortImprove")) {
            return sort.sortImprove(data);
        }
        return data;
    }

    @Test
    public void Test() {
        BubbleSortTest quickSortTest = new BubbleSortTest();
        quickSortTest.testMethod = "sort";
        quickSortTest.compareResult();
    }

    @Test
    public void TestImproveMethod() {
        BubbleSortTest quickSortTest = new BubbleSortTest();
        quickSortTest.testMethod = "sortImprove";
        quickSortTest.compareResult();
    }
}
