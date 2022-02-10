package org.lxb.algorithmic.struct;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class BinarySearchTreeTest {

    /**
     .....4.... <br>
     ..2.....5. <br>
     1...3..... <br>
     */
    private static final BinarySearchTree<Integer> testBST = new BinarySearchTree<Integer>();
    static {
        testBST.add(4);
        testBST.add(2);
        testBST.add(5);
        testBST.add(1);
        testBST.add(3);
    }

    @Test
    public void testSearchNode() {
        BinarySearchTree.Node<Integer> node = testBST.getNode(4);
        Assert.assertEquals(4+"",node.id+"");
    }

    @Test
    public void getSuccessor() {
        BinarySearchTree.Node<Integer> node = testBST.successor(4);
        Assert.assertEquals(5+"",node.id+"");
    }

    @Test
    public void getPredecessor() {
        BinarySearchTree.Node<Integer> node = testBST.predecessor(4);
        Assert.assertEquals(3+"",node.id+"");
    }

    @Test
    public void getMinMax() {
        BinarySearchTree.Node<Integer> node = testBST.minimum(testBST.root);
        Assert.assertEquals(1+"",node.id+"");

        BinarySearchTree.Node<Integer> nodeMax = testBST.maximum(testBST.root);
        Assert.assertEquals(5+"",nodeMax.id+"");
    }
    
    @Test
    public void testRandomInt() {
        int i = 0;
        Map<Integer,Integer> integerMap = new HashMap<>();
        integerMap.put(0,0);
        integerMap.put(1,0);
        while (i < 10000) {
            int result = Math.abs(BinarySearchTree.RANDOM.nextInt() % 2);
            Integer val = integerMap.get(result);
            integerMap.put(result, ++val);
            i ++;
        }
        System.out.println(integerMap);
        int zeroNum = integerMap.get(0);
        int oneNum = integerMap.get(1);
        System.out.println(((float)zeroNum) / (zeroNum + oneNum));
        System.out.println(((float)oneNum)  / (zeroNum + oneNum));
    }

    @Test
    public void remove() {
        Integer val = testBST.remove(3);
        Assert.assertEquals(false,testBST.contains(3));
    }

    @Test
    public void getBFS() {
        Integer[] result = testBST.getBFS();
        Integer[] expectResult = new Integer[]{4,2,5,1,3};
        for (int i = 0; i < result.length; i++) {
            Assert.assertEquals(expectResult[i],result[i]);
        }
    }

    @Test
    public void getDFS() {
        Integer[] result = testBST.getDFS(BinarySearchTree.DepthFirstSearchOrder.postOrder);
        Integer[] expectResult = new Integer[]{4,2,5,1,3};
        for (int i = 0; i < result.length; i++) {
            Assert.assertEquals(expectResult[i],result[i]);
        }
    }

    @Test
    public void testPreOrderDFS() {
        final Integer[] inOrder = testBST.getDFS(BinarySearchTree.DepthFirstSearchOrder.preOrder);
        final Integer[] expectation = new Integer[]{4, 2, 1, 3, 5};
        for (int i=0; i<inOrder.length; i++) {
            Assert.assertTrue(inOrder[i] == expectation[i]);
        }
    }

    @Test
    public void testLevelOrder() {
        final Integer[] inOrder = testBST.getBFS();
        final Integer[] expectation = new Integer[]{4, 2, 5, 1, 3};
        for (int i=0; i<inOrder.length; i++) {
            Assert.assertTrue(inOrder[i] == expectation[i]);
        }
    }

    @Test
    public void testInOrderDFS() {
        final Integer[] inOrder = testBST.getDFS(BinarySearchTree.DepthFirstSearchOrder.inOrder);
        final Integer[] expectation = new Integer[]{1, 2, 3, 4, 5};
        for (int i=0; i<inOrder.length; i++) {
            Assert.assertTrue(inOrder[i] == expectation[i]);
        }
    }

    @Test
    public void testPostOrderDFS() {
        final Integer[] inOrder = testBST.getDFS(BinarySearchTree.DepthFirstSearchOrder.postOrder);
        final Integer[] expectation = new Integer[]{1, 3, 2, 5, 4};
        for (int i=0; i<inOrder.length; i++) {
            Assert.assertTrue(inOrder[i] == expectation[i]);
        }
    }

}
