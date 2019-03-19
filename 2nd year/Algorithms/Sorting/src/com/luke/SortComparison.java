package com.luke;
// -------------------------------------------------------------------------

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * This class contains static methods that implementing sorting of an array of numbers
 * using different sort algorithms.
 * <p>
 * Results from timing the Sorting Functions:
 * <p>
 * <p>
 * Selection Sort time for numbers10.txt: 0.005878ms
 * Insertion Sort time for numbers10.txt: 0.005613ms
 * MergeSort Iterative time for numbers10.txt was 0.069142ms
 * MergeSort Recursive time for numbers10.txt: 0.035247ms
 * QuickSort time for numbers10.txt: 0.040363ms
 * Selection Sort time for numbers100.txt: 0.141607ms
 * Insertion Sort time for numbers100.txt: 0.077825ms
 * MergeSort Iterative time for numbers100.txt was 0.063602ms
 * MergeSort Recursive time for numbers100.txt: 0.080441ms
 * QuickSort time for numbers100.txt: 0.058687ms
 * Selection Sort time for numbers1000.txt: 11.463871ms
 * Insertion Sort time for numbers1000.txt: 6.857396ms
 * MergeSort Iterative time for numbers1000.txt was 0.716099ms
 * MergeSort Recursive time for numbers1000.txt: 0.655944ms
 * QuickSort time for numbers1000.txt: 0.58029ms
 * Selection Sort time for numbers1000Duplicates.txt: 1.444372ms
 * Insertion Sort time for numbers1000Duplicates.txt: 7.74397ms
 * MergeSort Iterative time for numbers1000Duplicates.txt was 0.216179ms
 * MergeSort Recursive time for numbers1000Duplicates.txt: 0.223837ms
 * QuickSort time for numbers1000Duplicates.txt: 0.181155ms
 * Selection Sort time for numbersNearlyOrdered1000.txt: 1.545533ms
 * Insertion Sort time for numbersNearlyOrdered1000.txt: 0.037984ms
 * MergeSort Iterative time for numbersNearlyOrdered1000.txt was 0.158112ms
 * MergeSort Recursive time for numbersNearlyOrdered1000.txt: 0.184141ms
 * QuickSort time for numbersNearlyOrdered1000.txt: 0.250779ms
 * Selection Sort time for numbersReverse1000.txt: 1.490914ms
 * Insertion Sort time for numbersReverse1000.txt: 0.433917ms
 * MergeSort Iterative time for numbersReverse1000.txt was 0.154914ms
 * MergeSort Recursive time for numbersReverse1000.txt: 0.154091ms
 * QuickSort time for numbersReverse1000.txt: 3.085238ms
 * Selection Sort time for numbersSorted1000.txt: 1.442627ms
 * Insertion Sort time for numbersSorted1000.txt: 0.003715ms
 * MergeSort Iterative time for numbersSorted1000.txt was 0.083805ms
 * MergeSort Recursive time for numbersSorted1000.txt: 0.159477ms
 * QuickSort time for numbersSorted1000.txt: 3.499122ms
 *
 * @author Luke Hackett
 * @version HT 2019
 */

class SortComparison {

    /**
     * Sorts an array of doubles using InsertionSort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     *
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order.
     */
    static double[] insertionSort(double a[]) {
        if (a != null) {
            for (int i = 1; i < a.length; i++) {
                double key = a[i];

                int j = i - 1;
                while (j >= 0 && a[j] > key) {
                    a[j + 1] = a[j];
                    j = j - 1;
                }
                a[j + 1] = key;
            }
            return a;
        } else {
            return null;
        }
    }//end insertionsort

    /**
     * Sorts an array of doubles using Quick Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     *
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     */
    static double[] quickSort(double a[]) {
        if (a != null) {
            quickSort(a, 0, a.length - 1);
            return a;
        } else {
            return null;
        }
    }//end quicksort

    private static void quickSort(double a[], int low, int high) {
        if (low < high) {
            int pivot = partition(a, low, high);

            quickSort(a, low, pivot - 1);
            quickSort(a, pivot + 1, high);
        }
    }

    private static int partition(double arr[], int low, int high) {
        double pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    /**
     * Sorts an array of doubles using iterative implementation of Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     *
     * @param a: An unsorted array of doubles.
     * @return after the method returns, the array must be in ascending sorted order.
     */
    static double[] mergeSortIterative(double[] a) {
        if (a != null) {
            int low = 0;
            int high = a.length - 1;

            double[] temp = Arrays.copyOf(a, a.length);

            for (int m = 1; m <= high - low; m = 2 * m) {
                for (int bottom = low; bottom < high; bottom += 2 * m) {
                    int mid = bottom + m - 1;
                    int top = bottom + 2 * m - 1;

                    merge(a, temp, bottom, mid, (top < high) ? top : high);
                }
            }
            return a;
        } else {
            return null;
        }
    }

    private static void merge(double[] a, double[] temp, int bottom, int mid, int top) {
        int k = bottom, i = bottom, j = mid + 1;

        while (i <= mid && j <= top) {
            if (a[i] < a[j]) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }

        while (i <= mid && i < a.length) {
            temp[k++] = a[i++];
        }

        for (i = bottom; i <= top; i++) {
            a[i] = temp[i];
        }
    }

    /**
     * Sorts an array of doubles using recursive implementation of Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     *
     * @param a: An unsorted array of doubles.
     * @return after the method returns, the array must be in ascending sorted order.
     */
    static double[] mergeSortRecursive(double[] a) {
        mergeSort(a);
        return a;
    }//end mergeSortRecursive

    private static void mergeSort(double[] a) {
        if (a != null) {
            if (a.length > 1) {
                int mid = a.length / 2;

                double[] leftArray = new double[mid];
                System.arraycopy(a, 0, leftArray, 0, mid);

                double[] rightArray = new double[a.length - mid];
                if (a.length - mid >= 0) System.arraycopy(a, mid, rightArray, 0, a.length - mid);
                mergeSort(leftArray);
                mergeSort(rightArray);

                int i = 0;
                int j = 0;
                int k = 0;

                while (i < leftArray.length && j < rightArray.length) {
                    if (leftArray[i] < rightArray[j]) {
                        a[k] = leftArray[i];
                        i++;
                    } else {
                        a[k] = rightArray[j];
                        j++;
                    }
                    k++;
                }

                while (i < leftArray.length) {
                    a[k] = leftArray[i];
                    i++;
                    k++;
                }

                while (j < rightArray.length) {
                    a[k] = rightArray[j];
                    j++;
                    k++;
                }
            }
        }
    }

    /**
     * Sorts an array of doubles using Selection Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     *
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     */
    static double[] selectionSort(double[] a) {
        if (a != null) {
            for (int i = 0; i < a.length - 1; i++) {
                int min = i;
                for (int j = i + 1; j < a.length; j++) {
                    if (a[j] < a[min]) {
                        min = j;
                    }
                }
                swap(a, i, min);
            }
            return a;
        } else {
            return null;
        }
    }//end selectionsort

    private static void swap(double[] a, int i, int j) {
        double temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        String[] files = new String[]{"/home/luke/Documents/College/2nd year/Algorithms/Sorting/src/com/luke/numbers10.txt",
                "/home/luke/Documents/College/2nd year/Algorithms/Sorting/src/com/luke/numbers100.txt",
                "/home/luke/Documents/College/2nd year/Algorithms/Sorting/src/com/luke/numbers1000.txt",
                "/home/luke/Documents/College/2nd year/Algorithms/Sorting/src/com/luke/numbers1000Duplicates.txt",
                "/home/luke/Documents/College/2nd year/Algorithms/Sorting/src/com/luke/numbersNearlyOrdered1000.txt",
                "/home/luke/Documents/College/2nd year/Algorithms/Sorting/src/com/luke/numbersReverse1000.txt",
                "/home/luke/Documents/College/2nd year/Algorithms/Sorting/src/com/luke/numbersSorted1000.txt"};

        for (String file : files) {
            ArrayList<Double> doubles = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line = br.readLine();
                while (line != null) {
                    line = br.readLine();
                    if (line != null) doubles.add(Double.parseDouble(line));
                }

                double[] a = resetArray(doubles);
                //System.out.println("Selection Sort: ");
                //printArray(a);
                double startTime = System.nanoTime();
                selectionSort(a);
                double endTime = System.nanoTime();
                double duration = (endTime - startTime) / 1000000;
                //printArray(a);
                System.out.println("Selection Sort time for " + file.split("/")[file.split("/").length - 1] + ": " + duration + "ms");

                a = resetArray(doubles);
                //System.out.println("Insertion Sort: ");
                //printArray(a);
                startTime = System.nanoTime();
                insertionSort(a);
                endTime = System.nanoTime();
                duration = (endTime - startTime) / 1000000;
                //printArray(a);
                System.out.println("Insertion Sort time for " + file.split("/")[file.split("/").length - 1] + ": " + duration + "ms");

                a = resetArray(doubles);
                //System.out.println("MergeSort Iterative: ");
                //printArray(a);
                startTime = System.nanoTime();
                mergeSortIterative(a);
                endTime = System.nanoTime();
                duration = (endTime - startTime) / 1000000;
                //printArray(a);
                System.out.println("MergeSort Iterative time for " + file.split("/")[file.split("/").length - 1] + " was " + duration + "ms");


                a = resetArray(doubles);
                //System.out.println("MergeSort Recursive: ");
                //printArray(a);
                startTime = System.nanoTime();
                mergeSortRecursive(a);
                endTime = System.nanoTime();
                duration = (endTime - startTime) / 1000000;
                //printArray(a);
                System.out.println("MergeSort Recursive time for " + file.split("/")[file.split("/").length - 1] + ": " + duration + "ms");

                a = resetArray(doubles);
                //System.out.println("QuickSort: ");
                //printArray(a);
                startTime = System.nanoTime();
                quickSort(a);
                endTime = System.nanoTime();
                duration = (endTime - startTime) / 1000000;
                //printArray(a);
                System.out.println("QuickSort time for " + file.split("/")[file.split("/").length - 1] + ": " + duration + "ms");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    private static void printArray(double[] a) {
        for (double d : a) {
            System.out.print(d + ", ");
        }
        System.out.println();
    }

    private static double[] resetArray(ArrayList<Double> doubles) {
        double[] a;
        a = new double[doubles.size()];
        for (int j = 0; j < a.length; j++) {
            a[j] = doubles.get(j);
        }
        return a;
    }
}//end class

