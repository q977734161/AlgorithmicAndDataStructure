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
public class QuickSortOfHoare<T extends Comparable<T>> {

    private static Random RANDOM = new Random();

    public static enum PivotEnum {
        FISTR,MIDDLE,RANDOM
    }

    private int getRandom(int num,PivotEnum pivotEnum) {
        if(pivotEnum == PivotEnum.RANDOM) {
            return RANDOM.nextInt(num);
        } else if(pivotEnum == PivotEnum.FISTR){
            return 0;
        }
        return num/2;
    }

    public <T extends Comparable<T>> T[] sort(T[] unsortData,PivotEnum pivotEnum) {
        int pivot = 0;
        if(pivotEnum == PivotEnum.MIDDLE) {
            pivot = unsortData.length/2;
        } else if(pivotEnum == PivotEnum.RANDOM){
            pivot = getRandom(unsortData.length,pivotEnum);
        }
        sort(pivot,0,unsortData.length-1,unsortData,pivotEnum);
        return unsortData;
    }

    private  <T extends Comparable<T>> void sort(int index,int start,int finish,T[] unsortData,PivotEnum pivotEnum) {
        int pivotIndex = index + start;
        T pivotValue = unsortData[pivotIndex];
        int s = start;
        int e = finish;
        while (s <= e) {
            while (unsortData[s].compareTo(pivotValue) < 0) {
                s++;
            }
            while (unsortData[e].compareTo(pivotValue) > 0) {
                e--;
            }
            if (s <= e) {
                swap(s, e, unsortData);
                s++;
                e--;
            }
        }
        if (start < e) {
            pivotIndex = getRandom((e - start) + 1, pivotEnum);
            sort(pivotIndex, start, e, unsortData, pivotEnum);
        }
        if (s < finish) {
            pivotIndex = getRandom((finish - s) + 1, pivotEnum);
            sort(pivotIndex, s, finish, unsortData, pivotEnum);
        }
    }

    private <T extends Comparable<T>> void swap(int s, int e, T[] unsortData) {
        T temp = unsortData[s];
        unsortData[s] = unsortData[e];
        unsortData[e] = temp;
    }
}
