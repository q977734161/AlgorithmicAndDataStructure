package org.lxb.algorithmic.sort;

/**
 * Heapsort is a comparison-based sorting algorithm to create a sorted array (or
 * list), and is part of the selection sort family. Although somewhat slower in
 * practice on most machines than a well-implemented quicksort, it has the
 * advantage of a more favorable worst-case O(n log n) runtime.
 * <p>
 * Family: Selection.<br>
 * Space: In-place.<br>
 * Stable: False.<br>
 * <p>
 * Average case = O(n*log n)<br>
 * Worst case = O(n*log n)<br>
 * Best case = O(n*log n)<br>
 * <p>
 * @see <a href="https://en.wikipedia.org/wiki/Heap_sort">Heap Sort (Wikipedia)</a>
 * <br>
 * @author Justin Wetherell <phishman3579@gmail.com>
 */
public class HeapSort<T extends Comparable<T>> {

    public <T extends Comparable<T>> T[] sort(T[] unsortData) {
        /**
         * 如果是null或者只有一个元素，直接返回
         */
        if(unsortData == null || unsortData.length == 1) {
            return unsortData;
        }
        int arrayLen = unsortData.length;
        for (int i = arrayLen - 1; i >= 0; i--) {
            buildHeap(unsortData,i);
            swap(0,i,unsortData);
        }
        return unsortData;

    }

    private <T extends Comparable<T>> void buildHeap(T[] unsortData,int len) {
        for (int i = len; i > 0 ; i--) {
            int parentId = (i - 1) / 2;
            T parentValue = unsortData[parentId];
            T childValue = unsortData[i];
            int tempI = i;
            while (parentValue.compareTo(childValue) < 0) {
                swap(parentId,tempI,unsortData);
                tempI = parentId;
                parentId = (tempI - 1) / 2;
                parentValue = unsortData[parentId];
                childValue = unsortData[tempI];
            }
        }
    }

    private <T extends Comparable<T>> void swap(int s, int e, T[] unsortData) {
        T temp = unsortData[s];
        unsortData[s] = unsortData[e];
        unsortData[e] = temp;
    }


}
