package org.lxb.algorithmic.struct;

import org.junit.Test;

public class BTreeTest {

    /**
     .....4.... <br>
     ..2.....5. <br>
     1...3..... <br>
     */
    private static final BTree<Integer> testBST = new BTree<Integer>();
    static {
        testBST.add(4);
        testBST.add(2);
        testBST.add(5);
        testBST.add(1);
        testBST.add(3);
    }

    @Test
    public void test() {
        System.out.println(testBST.size());
    }

}
