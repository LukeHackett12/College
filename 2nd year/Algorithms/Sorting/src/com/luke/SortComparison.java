package com.luke;// -------------------------------------------------------------------------

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * This class contains static methods that implementing sorting of an array of numbers
 * using different sort algorithms.
 * <p>
 * Results from timing the Sorting Functions:
 * <p>
 *
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
        if(a != null) {
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


}//end class

