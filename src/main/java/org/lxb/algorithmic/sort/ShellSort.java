package org.lxb.algorithmic.sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Shellsort, also known as Shell sort or Shell's method, is an in-place
 * comparison sort. It generalizes an exchanging sort, such as insertion or
 * bubble sort, by starting the comparison and exchange of elements with
 * elements that are far apart before finishing with neighboring elements.
 * Starting with far apart elements can move some out-of-place elements into
 * position faster than a simple nearest neighbor exchange.
 * <p>
 * Family: Exchanging.<br>
 * Space: In-place.<br>
 * Stable: False.<br>
 * <p>
 * Average case = depends on the gap<br>
 * Worst case = O(n * log^2 n)<br>
 * Best case = O(n)<br>
 * <p>
 * @see <a href="https://en.wikipedia.org/wiki/Shell_sort">Shell Sort (Wikipedia)</a>
 * <br>
 * @author Justin Wetherell <phishman3579@gmail.com>
 */
public class ShellSort<T extends Comparable<T>> {

    /**
     * 不借助数组，直接在原始数组上进行排序
     * @param gaps
     * @param unsortData
     * @param <T>
     * @return
     */
    public <T extends Comparable<T>> T[] sort(int[] gaps,T[] unsortData) {
        if (unsortData == null || unsortData.length == 1) {
            return unsortData;
        }
        int length = unsortData.length;
        for (int gap : gaps) {
            for (int i = gap; i < length; i++) {
                int tempIndex = i;
                T preValue = unsortData[tempIndex - gap];
                T currentValue = unsortData[tempIndex];
                while (tempIndex - gap >= 0 && preValue.compareTo(currentValue) > 0) {
                    swap(tempIndex - gap, tempIndex, unsortData);
                    tempIndex = tempIndex - gap;
                    if(tempIndex - gap >= 0) {
                        preValue = unsortData[tempIndex - gap];
                        currentValue = unsortData[tempIndex];
                    }
                }
            }
        }
        return unsortData;
    }

    /**
     * 借助数组实现
     * 1、先按照 gap 来将数组划分成 gap 个数子数组
     * 2、针对每个数组进行排序
     * 3、将子数组重新组合
     * 4、跳转到步骤1，直到所有gap遍历完成
     * @param gaps
     * @param unsortData
     * @param <T>
     * @return
     */
    public <T extends Comparable<T>> T[] sortWithSubArray(int[] gaps,T[] unsortData) {
        if (unsortData == null || unsortData.length == 1) {
            return unsortData;
        }
        int length = unsortData.length;

        for (int gap : gaps) {
            List<List<T>> subarrays = new ArrayList<>();
            for (int i = 0; i < gap; i++) {
                subarrays.add(new ArrayList<>(10));
            }

            int i = 0;
            int maxIndex = 0;
            while (i < length) {
                int subarrayIndex = i%gap;
                subarrays.get(subarrayIndex).add(unsortData[i]);
                if(subarrays.get(subarrayIndex).size() > maxIndex) {
                    maxIndex = subarrays.get(subarrayIndex).size();
                }
                i++;
            }
            sortArrays(subarrays);

            i = 0;
            int subarrayValueIndex = 0;
            while (i < length) {
                for (int j = 0; j < gap; j++) {
                    if(subarrayValueIndex < subarrays.get(j).size()) {
                        unsortData[i++] = subarrays.get(j).get(subarrayValueIndex);
                    }
                }
                subarrayValueIndex ++;
            }

        }

        return unsortData;
    }

    private <T extends Comparable<T>> void sortArrays(List<List<T>> subarrays) {
        for (List<T> subList : subarrays) {
            subList.sort(new Comparator<T>() {
                @Override
                public int compare(T o1, T o2) {
                    return o1.compareTo(o2);
                }
            });
        }
    }

    private <T extends Comparable<T>> void swap(int s, int e, T[] unsortData) {
        T temp = unsortData[s];
        unsortData[s] = unsortData[e];
        unsortData[e] = temp;
    }

}
