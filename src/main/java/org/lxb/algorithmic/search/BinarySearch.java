package org.lxb.algorithmic.search;

public class BinarySearch<T extends Comparable<T>> {

    public static int UNSECCUESSFUL = -1;

    //first method
    /**
     * function binary_search(A, n, T) is
     *     L := 0
     *     R := n − 1
     *     while L ≤ R do
     *         m := floor((L + R) / 2)
     *         if A[m] < T then
     *             L := m + 1
     *         else if A[m] > T then
     *             R := m − 1
     *         else:
     *             return m
     *     return unsuccessful
     * @param sortData
     * @param target
     * @return
     */
    public int search(T[] sortData,T target) {
        int len = sortData.length;
        int l = 0;
        int r = len - 1;
        while (l <= r) {
            int m = (int) Math.floor((l + r)/2);
            if(sortData[m].compareTo(target) < 0) {
                l = m + 1;
            } else if(sortData[m].compareTo(target) > 0) {
                r = m - 1;
            } else {
                return m;
            }
        }
        return UNSECCUESSFUL;
    }

    //second method
    /**
     * function binary_search_leftmost(A, n, T):
     *     L := 0
     *     R := n
     *     while L < R:
     *         m := floor((L + R) / 2)
     *         if A[m] < T:
     *             L := m + 1
     *         else:
     *             R := m
     *     return L
     * @param sortData
     * @param target
     * @return
     */
    public int searchLeftmost(T[] sortData,T target) {
        int len = sortData.length;
        int l = 0;
        int r = len;
        while (l < r) {
            int m = (int) Math.floor((l + r)/2);
            if(sortData[m].compareTo(target) < 0) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        if(l >= 0 && l < len) {
            return l;
        }
        return UNSECCUESSFUL;
    }

    //third method
    /**
     * function binary_search_rightmost(A, n, T):
     *     L := 0
     *     R := n
     *     while L < R:
     *         m := floor((L + R) / 2)
     *         if A[m] > T:
     *             R := m
     *         else:
     *             L := m + 1
     *     return R - 1
     * @param sortData
     * @param target
     * @return
     */
    public int searchRightmost(T[] sortData,T target) {
        int len = sortData.length;
        int l = 0;
        int r = len;
        while (l < r) {
            int m = (int) Math.floor((l + r)/2);
            if(sortData[m].compareTo(target) > 0) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        if(r - 1 >= 0 && r - 1 < len) {
            return r - 1;
        }
        return UNSECCUESSFUL;
    }
}
