package org.lxb.algorithmic.sort;

import java.util.ArrayList;
import java.util.List;

public class MergeSort <T extends Comparable<T>> {

    public static enum SPACE_TYPE {
        IN_PLACE,
        NOT_IN_PLACE
    }

    public <T extends Comparable<T>> T[] sort(T[] unsortData,int start,int end,SPACE_TYPE space_type) {
        List<T> temp = new ArrayList<>(unsortData.length);
        if (start < end) {

            int middle = (start + end) / 2;
            sort(unsortData,start,middle,space_type);
            sort(unsortData,middle + 1,end,space_type);
            if(space_type == SPACE_TYPE.IN_PLACE) {
                mergeInPlace(start,middle,end,unsortData);
            } else {
                merge(unsortData, start, middle, end, temp);
            }
        }
        return unsortData;
    }

    private static <T extends Comparable<T>> void mergeInPlace(int start, int middle, int end, T[] unsorted) {
        int i = start;
        int j = middle;
        while (i <= middle && j <= end) {
            T a = unsorted[i];
            T b = unsorted[j];
            if (b.compareTo(a) < 0) {
                // Shift everything to the right one spot
                System.arraycopy(unsorted, i, unsorted, i+1, j-i);
                unsorted[i] = b;
                i++;
                j++;
                //中间数据也向后面移动了一位
                middle++;
            } else {
                i++;
            }
        }
    }

    private <T extends Comparable<T>> void merge(T[] unsortData, int start, int middle, int end,List<T> temp) {

        int i = start;
        int j = middle + 1;
        int iStop = middle;
        int jStop = end;
        int tempIndex = 0;
        while (i <= iStop && j <= jStop) {
            if(unsortData[i].compareTo(unsortData[j]) > 0) {
                temp.add(tempIndex++, unsortData[j++]);
            } else {
                temp.add(tempIndex++, unsortData[i++]);
            }
        }
        while (i <= iStop) {
            temp.add(tempIndex++, unsortData[i++]);
        }

        while (j <= jStop) {
            temp.add(tempIndex++, unsortData[j++]);
        }
        tempIndex = 0;
        while (start <= end) {
            unsortData[start++] = temp.get(tempIndex++);
        }
        temp.clear();
    }
}