package org.lxb.algorithmic.sort;

import java.util.Random;

/**
 * Quicksort is a sorting algorithm which, on average, makes O(n*log n) comparisons to sort
 * n items. In the worst case, it makes O(n^2) comparisons, though this behavior is
 * rare. Quicksort is often faster in practice than other algorithms.
 * <p>
 * Family: Divide and conquer.<br>
 * Space: In-place.<br>
 * Stable: False.<br>
 * <p>
 * Average case = O(n*log n)<br>
 * Worst case = O(n^2)<br>
 * Best case = O(n) [three-way partition and equal keys]<br>
 * <p>
 * @see <a href="https://en.wikipedia.org/wiki/Quick_sort">Quicksort (Wikipedia)</a>
 */
public class QuickSortOfLomuto<T extends Comparable<T>> {

    public <T extends Comparable<T>> T[] sort(T[] unsortData,int low,int hi) {
        if(low >= hi || low < 0) {
            return unsortData;
        }

        int p = partition(unsortData,low,hi);
        sort(unsortData,low,p-1);
        sort(unsortData,p+1,hi);
        return unsortData;
    }

    private  <T extends Comparable<T>> int partition(T[] unsortData,int low,int hi) {
        T pivot = unsortData[hi];

        int pivotIndex = low - 1;

        for (int i = low; i < hi; i++) {
            if(unsortData[i].compareTo(pivot) < 0) {
                pivotIndex ++;
                swap(i,pivotIndex,unsortData);
            }
        }

        pivotIndex ++;
        swap(pivotIndex,hi,unsortData);
        return pivotIndex;
    }

    private <T extends Comparable<T>> void swap(int s, int e, T[] unsortData) {
        T temp = unsortData[s];
        unsortData[s] = unsortData[e];
        unsortData[e] = temp;
    }
}
