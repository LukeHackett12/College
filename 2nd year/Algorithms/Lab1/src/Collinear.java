// -------------------------------------------------------------------------

/**
 * This class contains only two static methods that search for points on the
 * same line in three arrays of integers.
 *
 * @author Luke Hackett
 * @version 18/09/18 12:21:09
 */
class Collinear {

    // ----------------------------------------------------------

    /**
     * Counts for the number of non-hoizontal lines that go through 3 points in arrays a1, a2, a3.
     * This method is static, thus it can be called as Collinear.countCollinear(a1,a2,a3)
     *
     * @param a1: An UNSORTED array of integers. Each integer a1[i] represents the point (a1[i], 1) on the plain.
     * @param a2: An UNSORTED array of integers. Each integer a2[i] represents the point (a2[i], 2) on the plain.
     * @param a3: An UNSORTED array of integers. Each integer a3[i] represents the point (a3[i], 3) on the plain.
     * @return the number of points which are collinear and do not lie on a horizontal line.
     * <p>
     * Array a1, a2 and a3 contain points on the horizontal line y=1, y=2 and y=3, respectively.
     * A non-horizontal line will have to cross all three of these lines. Thus
     * we are looking for 3 points, each in a1, a2, a3 which lie on the same
     * line.
     * <p>
     * Three points (x1, y1), (x2, y2), (x3, y3) are collinear (i.e., they are on the same line) if
     * <p>
     * x1(y2−y3)+x2(y3−y1)+x3(y1−y2)=0
     * <p>
     * In our case y1=1, y2=2, y3=3.
     * <p>
     * You should implement this using a BRUTE FORCE approach (check all possible combinations of numbers from a1, a2, a3)
     * <p>
     * ----------------------------------------------------------
     * <p>
     * <p>
     * Order of Growth
     * -------------------------
     * <p>
     * Caclulate and write down the order of growth of your algorithm. You can use the asymptotic notation.
     * You should adequately explain your answer. Answers without adequate explanation will not be counted.
     * <p>
     * Order of growth: N^3
     * <p>
     * Explanation: Three linear for loops.
     */
    static int countCollinear(int[] a1, int[] a2, int[] a3) {
        int numberOfCol = 0;

        for(int i = 0; i < a1.length; i++){
            for(int j = 0; j < a2.length; j++){
                for(int q = 0; q < a3.length; q++){
                    int col = (a1[i]*(-1))+(a2[j]*2)+(a3[q]*(-1));
                    if(col == 0) numberOfCol++;
                }
            }
        }

        return numberOfCol;
    }

    // ----------------------------------------------------------

    /**
     * Counts for the number of non-hoizontal lines that go through 3 points in arrays a1, a2, a3.
     * This method is static, thus it can be called as Collinear.countCollinearFast(a1,a2,a3)
     *
     * @param a1: An UNSORTED array of integers. Each integer a1[i] represents the point (a1[i], 1) on the plain.
     * @param a2: An UNSORTED array of integers. Each integer a2[i] represents the point (a2[i], 2) on the plain.
     * @param a3: An UNSORTED array of integers. Each integer a3[i] represents the point (a3[i], 3) on the plain.
     * @return the number of points which are collinear and do not lie on a horizontal line.
     * <p>
     * In this implementation you should make non-trivial use of InsertionSort and Binary Search.
     * The performance of this method should be much better than that of the above method.
     * <p>
     * <p>
     * Order of Growth
     * -------------------------
     * <p>
     * Caclulate and write down the order of growth of your algorithm. You can use the asymptotic notation.
     * You should adequately explain your answer. Answers without adequate explanation will not be counted.
     * <p>
     * Order of Growth: N^2 logN
     * <p>
     * Explanation: Two linear for loops and a binarySearch
     */
    static int countCollinearFast(int[] a1, int[] a2, int[] a3) {
        int numberOfCol = 0;

        sort(a3);

        for(int i = 0; i < a1.length; i++){
            for(int j = 0; j < a2.length; j++){
                int numberThree = ((a1[i]*(-1))+(a2[j]*2))*-1;
                if(binarySearch(a3, numberThree)){
                    numberOfCol++;
                }
            }
        }

        return numberOfCol;
    }

    // ----------------------------------------------------------

    /**
     * Sorts an array of integers according to InsertionSort.
     * This method is static, thus it can be called as Collinear.sort(a)
     *
     * @param a: An UNSORTED array of integers.
     * @return after the method returns, the array must be in ascending sorted order.
     * <p>
     * ----------------------------------------------------------
     * <p>
     * Order of Growth
     * -------------------------
     * <p>
     * Caclulate and write down the order of growth of your algorithm. You can use the asymptotic notation.
     * You should adequately explain your answer. Answers without adequate explanation will not be counted.
     * <p>
     * Order of Growth: N^2
     * <p>
     * Explanation: Two linear for-loops.
     */
    static void sort(int[] a) {
        for (int j = 1; j < a.length; j++) {
            int i = j - 1;
            while (i >= 0 && a[i] > a[i + 1]) {
                int temp = a[i];
                a[i] = a[i + 1];
                a[i + 1] = temp;
                i--;
            }
        }
    }

    // ----------------------------------------------------------

    /**
     * Searches for an integer inside an array of integers.
     * This method is static, thus it can be called as Collinear.binarySearch(a,x)
     *
     * @param a: A array of integers SORTED in ascending order.
     * @param x: An integer.
     * @return true if 'x' is contained in 'a'; false otherwise.
     * <p>
     * ----------------------------------------------------------
     * <p>
     * Order of Growth
     * -------------------------
     * <p>
     * Caclulate and write down the order of growth of your algorithm. You can use the asymptotic notation.
     * You should adequately explain your answer. Answers without adequate explanation will not be counted.
     * <p>
     * Order of Growth: logN
     * <p>
     * Explanation: The algorithm will half the amount of elements that
     *              it has to check each iteration, therefore the order
     *              of growth is logarithmic
     */
    static boolean binarySearch(int[] a, int x) {
        int lo = 0, hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (x < a[mid]) hi = mid - 1;
            else if (x > a[mid]) lo = mid + 1;
            else return true;
        }
        return false;
    }

}
