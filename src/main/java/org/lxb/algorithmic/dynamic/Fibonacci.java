package org.lxb.algorithmic.dynamic;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lixiaobao
 * @create 2019-10-30 5:20 下午
 * 斐波那契数列的实现
 **/

public class Fibonacci {

    private Map<Integer,Integer> cache = new HashMap<>();
    /**
     * 使用memoization技术来实现获取第n个斐波那契数
     * memoization 是一种优化技术，主要用于通过存储昂贵的函数调用的结果来加速计算机程序，
     * 并在再次发生相同的输入时返回缓存的结果。
     */
    public int fibMemo(int n) {
        if(cache.containsKey(n)) {
            return cache.get(n);
        }

        int f;

        if (n <= 1) {
            f = n;
        } else {
            f = fibMemo(n - 1) + fibMemo( n - 2);
            cache.put(n,f);
        }
        return f;
    }

    /**
     * 使用 bottom up 方法来获取第 n 个斐波那契数
     */
    public int fibBottomUp(int n) {

        Map<Integer,Integer> fib = new HashMap<>();

        for (int i = 0; i <= n; i++) {
            int f;
            if (i <= 1) {
                f = i;
            } else {
                f = fib.get(i - 1) + fib.get(i - 2);
            }
            fib.put(i, f);
        }

        return fib.get(n);

    }

    /**
     * 优化以后的算法，不使用递归以及Map
     * 节省了空间以及时间
     * 空间复杂度是 O(1)
     * 时间复杂度是 O(n)
     * 上边的两种方法的空间复杂度是 O(n)
     */

    public int fibOptimized(int n) {
        if(n == 0) {
            return 0;
        }

        int pre = 0,res = 1,next;
        for (int i = 2; i <= n ; i++) {
            next = pre + res;
            pre = res;
            res = next;
        }

        return res;
    }
}
