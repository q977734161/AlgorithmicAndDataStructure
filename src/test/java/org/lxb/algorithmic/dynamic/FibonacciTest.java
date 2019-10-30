package org.lxb.algorithmic.dynamic;

import org.junit.Test;

/**
 * @author lixiaobao
 * @create 2019-10-30 5:29 下午
 **/

public class FibonacciTest {

    @Test
    public void testMemo() {
        Fibonacci fibonacci = new Fibonacci();
        int n = fibonacci.fibMemo(10);
        assert n == 55 : "error";
        System.out.println("testMemo => " + n);
    }

    @Test
    public void testBottomUp() {
        Fibonacci fibonacci = new Fibonacci();
        int n = fibonacci.fibBottomUp(10);
        assert n == 55 : "error";
        System.out.println("testBottomUp => " + n);
    }

    @Test
    public void testOptimized() {
        Fibonacci fibonacci = new Fibonacci();
        int n = fibonacci.fibOptimized(10);
        assert n == 55 : "error";
        System.out.println("testOptimized => " + n);
    }

}
