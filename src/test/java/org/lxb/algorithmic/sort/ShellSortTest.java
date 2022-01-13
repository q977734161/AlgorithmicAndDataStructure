package org.lxb.algorithmic.sort;

import org.junit.Test;

public class ShellSortTest extends SortBase {

    private String method = "default";

    @Override
    public Integer[] sort(Integer[] data) {
        ShellSort<Integer> sort = new ShellSort<>();
        int[] gaps = new int[]{5,2,1};
        if(method.equals("default")) {
            return sort.sort(gaps, data);
        }else {
            return sort.sortWithSubArray(gaps, data);
        }
    }

    @Test
    public void TestDefault() {
        ShellSortTest shellSortTest = new ShellSortTest();
        shellSortTest.method = "default";
        shellSortTest.compareResult();
    }

    @Test
    public void TestWithArray() {
        ShellSortTest shellSortTest = new ShellSortTest();
        shellSortTest.method = "withArray";
        shellSortTest.compareResult("use arrays");
    }
}
