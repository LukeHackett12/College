/* SELF ASSESSMENT
 1. Did I use easy-to-understand meaningful variable names formatted properly (in lowerCamelCase)?
        Mark out of 5: 5
        All variable names clearly indicate their intended value
 2. Did I indent the code appropriately?
        Mark out of 5: 5
        Code indented in appropriate places to make code readable
 3. Did I write the determineStarNumber or determineTriangleNumber function correctly (parameters, return type and function body) and invoke it correctly?
       Mark out of 20: 20
       Wrote a determineTriangleNumber function that took a number n and returned an long value of the nth triangle number,
       also wrote a determineStarNumber function that took a number and returned a long value of the numbers star
 4. Did I write the isStarNumber function correctly (parameters, return type and function body) and invoke it correctly?
       Mark out of 20: 20
       isStarNumber is passed a triangle number to test and will return a boolean true or false based on whether the number is also a star
 5. Did I calculate and/or check triangle numbers correctly?
       Mark out of 15: 15
       Triangle numbers are generated by the addition of the last triangle number and n, the new number. This will continue to produce triangle number correctly.
 6. Did I loop through all possibilities in the program using system defined constants to determine when to stop?
       Mark out of 10: 10
       Made all variables long and stopped when the value was bigger then the max Integer.MAX_VALUE constant.
 7. Does my program compute and print all the correct triangular star numbers?
       Mark out of 20: 20
       The program computed correct values for triangular star numbers until they were bigger then Integer.MAX_VALUE. This list can be verified at http://oeis.org/A006060
 8. How well did I complete this self-assessment?
        Mark out of 5: 5
        All comments explained properly
 Total Mark out of 100 (Add all the previous marks): 100
*/

public class TriangularStars{

    public static final long MAX_VALUE = Integer.MAX_VALUE;

    public static void main(String[] Args){
        long triangleNumber = 0;
        long index = 0;
        while(triangleNumber <= MAX_VALUE){
            triangleNumber = determineTriangleNumber(index, triangleNumber);
            if(isStarNumber(triangleNumber)){
                System.out.println(triangleNumber);
            }
            index++;
        }
    }

    public static boolean isStarNumber(long triangleNumber){
        boolean isStarNumber = false;
        long testStar = 0;
        long index = 0;
        while(testStar < triangleNumber){
            testStar = determineStarNumber(index);
            if(testStar == triangleNumber){
                isStarNumber = true;
            }
            index++;
        }
        return isStarNumber;
    }

    public static long determineTriangleNumber(long count, long lastTriangle){
        return count + lastTriangle;
    }

    public static long determineStarNumber(long count){
        return (6*count*(count-1)) + 1;
    }
}