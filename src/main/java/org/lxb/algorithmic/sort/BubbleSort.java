package org.lxb.algorithmic.sort;

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
