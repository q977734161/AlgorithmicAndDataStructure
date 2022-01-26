package org.lxb.algorithmic.search;

import org.junit.Test;

public class BinarySearchTest extends BaseSearchTest{

    private String searchMethod = "default";

    @Override
    public Integer search(Integer[] data, int a) {
        BinarySearch<Integer> binarySearch = new BinarySearch<>();
        if(searchMethod.equals("default")) {
            return binarySearch.search(data,a);
        } else if(searchMethod.equals("leftmost")) {
            return binarySearch.searchLeftmost(data,a);
        } else if(searchMethod.equals("rightmost")) {
            return binarySearch.searchRightmost(data,a);
        } else {
            return binarySearch.search(data,a);
        }
    }

    @Test
    public void testDefault() throws Exception {
        BinarySearchTest binarySearchTest = new BinarySearchTest();
        binarySearchTest.compareResult();
    }

    @Test
    public void testLeft() throws Exception {
        BinarySearchTest binarySearchTest = new BinarySearchTest();
        binarySearchTest.searchMethod = "leftmost";
        binarySearchTest.compareResult();
    }

    @Test
    public void testRight() throws Exception {
        BinarySearchTest binarySearchTest = new BinarySearchTest();
        binarySearchTest.searchMethod = "rightmost";
        binarySearchTest.compareResult();
    }
}
