package org.lxb.algorithmic.sort;

public class CountingSort {

    public Integer[] sort(Integer[] unsortData) {
        int maxValue = findMax(unsortData);
        Integer[] counter = new Integer[maxValue + 1];
        count(unsortData,counter);
        findResult(unsortData,counter);
        return unsortData;
    }

    private void findResult(Integer[] unsortData, Integer[] counter) {
        int index = 0;
        for (int i = 0; i < counter.length ; i++) {
            Integer val = counter[i];
            while (val != null && val > 0) {
                unsortData[index] = i;
                index ++;
                val --;
            }
        }
    }

    private void count(Integer[] unsortData, Integer[] counter) {
        for (int i = 0; i < unsortData.length; i++) {
            if(counter[unsortData[i]] == null) {
                counter[unsortData[i]] = 0;
            }
            counter[unsortData[i]] ++;
        }
    }

    private Integer findMax(Integer[] unsortData) {
        int max = 0;
        for (int i = 0; i < unsortData.length; i++) {
            if(unsortData[i] > max) {
                max = unsortData[i];
            }
        }
        return max;
    }

}
