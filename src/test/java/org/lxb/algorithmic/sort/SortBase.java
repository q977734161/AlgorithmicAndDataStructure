package org.lxb.algorithmic.sort;

import org.junit.Assert;

public abstract class SortBase {

    public Integer[] UNSORT_DATA = new Integer[]{3,2,4,1,7,4,7,8,9,2,0};
    public Integer[] EXPECT_DATA = new Integer[]{0,1,2,2,3,4,4,7,7,8,9};

    public abstract Integer[] sort(Integer[] data);

    public void compareResult() {
        Integer[] arrayCopy = new Integer[UNSORT_DATA.length];
        System.arraycopy(UNSORT_DATA,0,arrayCopy,0,UNSORT_DATA.length);
        Integer[] result = sort(arrayCopy);
        System.out.println("input data :");
        for (int i = 0; i < result.length; i++) {
            System.out.print(UNSORT_DATA[i] + " ");
        }
        System.out.println();
        System.out.println("result data :");
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
        for (int i = 0; i < result.length; i++) {
            Assert.assertArrayEquals(result,EXPECT_DATA);
        }
    }
}
