package org.lxb.algorithmic.search;

import org.junit.Assert;

public abstract class BaseSearchTest {

    public Integer[] EXPECT_DATA = new Integer[]{0,1,2,2,3,4,4,7,7,8,9};
    public static Integer TARGET = 2;

    public abstract Integer search(Integer[] data,int a);

    public void compareResult(String comment) throws Exception {
        Integer result = search(EXPECT_DATA,TARGET);
        System.out.println(comment);
        System.out.println("input data :");
        for (int i = 0; i < EXPECT_DATA.length; i++) {
            System.out.print(EXPECT_DATA[i] + " ");
        }
        System.out.println();
        System.out.println("result data : " + result);

        System.out.println();
        if(result > EXPECT_DATA.length - 1 || result < 0) {
            throw new Exception("can not find element,result is " + result);
        }
        Assert.assertEquals(EXPECT_DATA[result],TARGET);

    }

    public void compareResult() throws Exception {
        compareResult("");
    }
}
