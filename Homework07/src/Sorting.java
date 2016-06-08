import java.util.Comparator;
import java.util.Random;
import java.util.LinkedList;

/**
 * Your implementation of various sorting algorithms.
 *
 * @author Sreeramamurthy Tripuramallu
 * @version 1.0
 */
public class Sorting {
    /**
     * Implement cocktail shaker sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable)
     *
     * When writing your sort, don't recheck already sorted items. The amount of
     * items you are comparing should decrease by 1 for each pass of the array
     * (in either direction). See the PDF for more info.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void cocktailShakerSort(T[] arr,
                                              Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Data not valid");
        } else {
            int left = 0;
            int right = arr.length - 1;
            while (left < right) {
                for (int i = left; i < right; i++) {
                    if (comparator.compare(arr[i], arr[i + 1]) > 0) {
                        T val = arr[i];
                        arr[i] = arr[i + 1];
                        arr[i + 1] = val;
                    }
                }
                right--;
                for (int i = right; i > left; i--) {
                    if (comparator.compare(arr[i], arr[i - 1]) < 0) {
                        T val = arr[i];
                        arr[i] = arr[i - 1];
                        arr[i - 1] = val;
                    }
                }
                left++;
            }
        }
    }


    /**
     * Implement insertion sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable)
     *
     * See the PDF for more info on this sort.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Invalid data inputted");
        } else {
            for (int i = 1; i < arr.length; i++) {
                T temp = arr[i];
                int j = i;
                while (j > 0 && comparator.compare(temp, arr[j - 1]) < 0) {
                    arr[j] = arr[j - 1];
                    j--;
                }
                arr[j] = temp;
            }
        }
    }

    /**
     * Implement selection sort.
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n^2)
     *
     * Note that there may be duplicates in the array.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void selectionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Inputs not valid");
        }
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (comparator.compare(arr[minIndex], arr[j]) > 0) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                T temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
    }

    /**
     * Implement quick sort.
     *
     * Use the provided random object to select your pivots.
     * For example if you need a pivot between a (inclusive)
     * and b (exclusive) where b > a, use the following code:
     *
     * int pivotIndex = r.nextInt(b - a) + a;
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * Note that there may be duplicates in the array.
     *
     * @throws IllegalArgumentException if the array or comparator or rand is
     * null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     */
    public static <T> void quickSort(T[] arr, Comparator<T> comparator,
                                     Random rand) {
        if (arr == null || comparator == null || rand == null) {
            throw new IllegalArgumentException("Invalid data inputted");
        } else {
            quickHelper(arr, comparator, rand, 0, arr.length - 1);
        }
    }

    /**
     * Helper method for quicksort
     * @param arr the array being sorted
     * @param comparator the comparator object used for comparison
     * @param rand random object used to obtain pivot
     * @param a starting value
     * @param b ending value
     * @param <T> the type of the object
     * */
    private static <T> void quickHelper(T[] arr, Comparator<T> comparator,
                                        Random rand, int a, int b) {
        if (a < b) {
            int left = a;
            int right = b - 1;
            int pivot = rand.nextInt(b - a) + a;
            T pivotValue = arr[pivot];
            T val = arr[b];
            arr[b] = arr[pivot];
            arr[pivot] = val;
            while (left <= right) {
                while (left <= right && comparator.compare(arr[left],
                        pivotValue) < 0) {
                    left++;
                }
                while (left <= right && comparator.compare(arr[right],
                        pivotValue) > 0) {
                    right--;
                }
                if (left <= right) {
                    T leftVal = arr[left];
                    arr[left] = arr[right];
                    arr[right] = leftVal;
                    left++;
                    right--;
                }
            }
            T leftVal = arr[left];
            T other = arr[b];
            arr[b] = arr[left];
            arr[left] = other;
            quickHelper(arr, comparator, rand, a, left - 1);
            quickHelper(arr, comparator, rand, left + 1, b);
        }

    }

    /**
     * Implement merge sort.
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(n log n)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * You can create more arrays to run mergesort, but at the end,
     * everything should be merged back into the original T[]
     * which was passed in.
     *
     * ********************* IMPORTANT ************************
     * FAILURE TO DO SO MAY CAUSE ClassCastException AND CAUSE
     * YOUR METHOD TO FAIL ALL THE TESTS FOR MERGE SORT
     * ********************************************************
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Invalid data inputted");
        } else {
            if (arr.length >= 2) {
                T[] a = (T[]) new Object[arr.length / 2];
                T[] b = (T[]) new Object[arr.length - arr.length / 2];
                for (int i = 0; i < arr.length / 2; i++) {
                    a[i] = arr[i];
                }
                for (int j = arr.length / 2; j < arr.length; j++) {
                    b[j - arr.length / 2] = arr[j];
                }
                mergeSort(a, comparator);
                mergeSort(b, comparator);
                merge(arr, comparator, a, b);
            }
        }
    }

    /**
     * Helper method for merge sort
     * @param arr the array being sorted
     * @param comparator the comparator object used for comparison
     * @param a first half of array
     * @param b second half of array
     * @param <T> type of object in the array
     */
    private static <T> void merge(T[] arr,
                                  Comparator<T> comparator, T[] a, T[] b) {
        int i = 0;
        int j = 0;
        while (i + j < arr.length) {
            if (j == b.length || (i < a.length
                    && comparator.compare(a[i], b[j]) <= 0)) {
                arr[i + j] = a[i++];
            } else {
                arr[i + j] = b[j++];
            }
        }
    }

    /**
     * Implement radix sort.
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code!
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(kn)
     *
     * And a best case running time of:
     *  O(kn)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable)
     *
     * DO NOT USE {@code Math.pow()} in your sort. Instead, if you need to, use
     * the provided {@code pow()} method below.
     *
     * You may use an ArrayList or LinkedList if you wish, but it may only be
     * used inside radix sort and any radix sort helpers. Do NOT use these
     * classes with other sorts.
     *
     * @throws IllegalArgumentException if the array is null
     * @param arr the array to be sorted
     * @return the sorted array
     */
    public static int[] radixSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Null data inputted");
        }
        int negativeNum = 0;
        int positiveNum = 0;
        int maxDigits = 1;
        //find max digits and number of negatives and positives
        for (int i : arr) {
            if (i < 0) {
                negativeNum++;
            } else {
                positiveNum++;
            }
            int newMaxDigits = 1;
            while (i / 10 != 0) {
                newMaxDigits++;
                i /= 10;
            }
            if (newMaxDigits > maxDigits) {
                maxDigits = newMaxDigits;
            }
        }

        int[] negaArr = new int[negativeNum];
        int[] posArr = new int[positiveNum];
        int nCount = 0;
        int pCount = 0;
        for (int i : arr) {
            if (i < 0) {
                negaArr[nCount++] = i;
            } else {
                posArr[pCount++] = i;
            }
        }

        LinkedList<Integer>[] negativeCounter = new LinkedList[] {
            new LinkedList<Integer>(),
            new LinkedList<Integer>(), new LinkedList<Integer>(),
            new LinkedList<Integer>(), new LinkedList<Integer>(),
            new LinkedList<Integer>(), new LinkedList<Integer>(),
            new LinkedList<Integer>(), new LinkedList<Integer>(),
            new LinkedList<Integer>()};
        LinkedList<Integer>[] positiveCounter = new LinkedList[] {
            new LinkedList<Integer>(),
            new LinkedList<Integer>(), new LinkedList<Integer>(),
            new LinkedList<Integer>(), new LinkedList<Integer>(),
            new LinkedList<Integer>(), new LinkedList<Integer>(),
            new LinkedList<Integer>(), new LinkedList<Integer>(),
            new LinkedList<Integer>()};
        int mod = 10;
        int dev = 1;
        for (int i = 0; i < maxDigits; i++, mod *= 10, dev *= 10) {
            for (int j = 0; j < negaArr.length; j++) {
                int bucket = Math.abs((negaArr[j] % mod) / dev);
                negativeCounter[bucket].add(negaArr[j]);
            }
            for (int j = 0; j < posArr.length; j++) {
                int bucket = (posArr[j] % mod) / dev;
                positiveCounter[bucket].add(posArr[j]);
            }
            int negativePos = 0;
            for (int j = 0; j < negativeCounter.length; j++) {
                Integer value = null;
                while ((value = negativeCounter[j].poll()) != null) {
                    negaArr[negativePos++] = value;
                }
            }
            int positivePos = 0;
            for (int j = 0; j < positiveCounter.length; j++) {
                Integer value = null;
                while ((value = positiveCounter[j].poll()) != null) {
                    posArr[positivePos++] = value;
                }
            }
        }
        int count = 0;
        for (int i = negaArr.length - 1; i >= 0; i--) {
            arr[count++] = negaArr[i];
        }
        for (int i = 0; i < posArr.length; i++) {
            arr[count++] = posArr[i];
        }

        return arr;
    }




    /**
     * Calculate the result of a number raised to a power. Use this method in
     * your radix sort instead of {@code Math.pow()}. DO NOT MODIFY THIS METHOD.
     *
     * @param base base of the number
     * @param exp power to raise the base to. Must be 0 or greater.
     * @return result of the base raised to that power.
     */
    private static int pow(int base, int exp) {
        if (exp < 0) {
            throw new IllegalArgumentException("Invalid exponent.");
        } else if (base == 0 && exp == 0) {
            throw new IllegalArgumentException(
                    "Both base and exponent cannot be 0.");
        } else if (exp == 0) {
            return 1;
        } else if (exp == 1) {
            return base;
        }
        int halfPow = pow(base, exp / 2);
        if (exp % 2 == 0) {
            return halfPow * halfPow;
        } else {
            return halfPow * pow(base, (exp / 2) + 1);
        }
    }
}
