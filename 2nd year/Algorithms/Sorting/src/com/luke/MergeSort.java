
import java.util.Arrays;

class MergeSort {
    // Merge two sorted sub-arrays A[from .. mid] and A[mid + 1 .. to]
    private static void merge(double[] a, double[] temp, int from, int mid, int to) {
        int k = from, i = from, j = mid + 1;

        while (i <= mid && j <= to) {
            if (a[i] < a[j]) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = a[i++];
        }

        for (i = from; i <= to; i++) {
            a[i] = temp[i];
        }
    }

    public static void mergeSortIterative(double[] a) {
        int low = 0;
        int high = a.length - 1;

        double[] temp = Arrays.copyOf(a, a.length);

        for (int m = 1; m <= high - low; m = 2 * m) {
            for (int i = low; i < high; i += 2 * m) {
                int mid = i + m - 1;
                int to = i + 2 * m - 1;

                merge(a, temp, i, mid, (to < high) ? to : high);
            }
        }
    }

    public static void main(String[] args) {
        double[] a = {5, 7, -9, 3, -4, 2, 8};

        System.out.println("Original Array : " + Arrays.toString(a));
        mergeSortIterative(a);
        System.out.println("Modified Array : " + Arrays.toString(a));
    }
}