package org.lxb.algorithmic.sort;

/**
 * Bubble sort is a simple sorting algorithm that works by repeatedly stepping
 * through the list to be sorted, comparing each pair of adjacent items and
 * swapping them if they are in the wrong order. The pass through the list is
 * repeated until no swaps are needed, which indicates that the list is sorted.
 * <p>
 * Family: Exchanging.<br>
 * Space: In-place.<br>
 * Stable: True.<br>
 * <p>
 * Average case = O(n^2)<br>
 * Worst case = O(n^2)<br>
 * Best case = O(n)<br>
 * <p>
 * @see <a href="https://en.wikipedia.org/wiki/Bubble_sort">Bubble Sort (Wikipedia)</a>
 */
public class BubbleSort<T extends Comparable<T>> {

    public <T extends Comparable<T>> T[] sort(T[] unsortData) {

        int len = unsortData.length;
        for (int i = 0; i < len; i++) {
            for (int j = 1; j < len; j++) {
                if (unsortData[j - 1].compareTo(unsortData[j]) > 0) {
                    swap(unsortData, j - 1, j);
                }
            }
        }
        return unsortData;
    }

    public <T extends Comparable<T>> T[] sortImprove(T[] unsortData) {
        boolean swaped = false;
        int len = unsortData.length;
        for (int i = 0; i < len; i++) {
            for (int j = 1; j < len; j++) {
                if (unsortData[j - 1].compareTo(unsortData[j]) > 0) {
                    swap(unsortData, j - 1, j);
                    swaped = true;
                }
            }
            if(!swaped) {
                break;
            }
        }
        return unsortData;
    }

    public <T extends Comparable<T>> void swap(T[] unsortData,int index,int index2) {
        T temp = unsortData[index];
        unsortData[index] = unsortData[index2];
        unsortData[index2] = temp;
    }

}
