package org.lxb.algorithmic.sort;

public class InsertionSort<T extends Comparable<T>> {

    public <T extends Comparable<T>> T[] sort( T[] unsortData) {
        int len = unsortData.length;
        for (int i = 1; i < len; i++) {
            sort(i,unsortData);
        }
        return unsortData;
    }

    private <T extends Comparable<T>> void sort(int index, T[] unsortData) {
        for (int i = index; i > 0; i--) {
            T currentValue = unsortData[i];
            T preValue = unsortData[i-1];
            if(currentValue.compareTo(preValue) < 0) {
                unsortData[i - 1] = currentValue;
                unsortData[i] = preValue;
            } else {
                break;
            }
        }
    }

    public <T extends Comparable<T>> T[] sortOther(T[] unsortData) {
        int len = unsortData.length;
        for (int i = 1; i < len; i++) {
            T currentValue = unsortData[i];
            int preIndex = i - 1;
            while (preIndex >= 0 && unsortData[preIndex].compareTo(currentValue) > 0) {
                unsortData[preIndex + 1] = unsortData[preIndex];
                preIndex = preIndex - 1;
            }
            unsortData[preIndex + 1] = currentValue;
        }
        return unsortData;
    }

}
