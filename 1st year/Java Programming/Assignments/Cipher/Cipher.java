/* SELF ASSESSMENT

 1. Did I use easy-to-understand meaningful variable names formatted properly (in lowerCamelCase)?

        Mark out of 5:5
        All variable named explained their purpose and were formatted correctly.
 2. Did I indent the code appropriately?

        Mark out of 5:5
        All code is indented at appropriate points.
 3. Did I write the createCipher function correctly (parameters, return type and function body) and invoke it correctly?

       Mark out of 20:20
       createCipher returned a 2d array mapping each letter of the alphabet(and space) to a randomised letter(or space) so that there was a
       1:1 mapping of each letter to another.
 4. Did I write the encrypt function correctly (parameters, return type and function body) and invoke it correctly?

       Mark out of 20:20
       encrypt took in an array of characters and the desired cipherArray and would return a string that had encrypted the
       message as desired.
 5. Did I write the decrypt function correctly (parameters, return type and function body) and invoke it correctly?

       Mark out of 20:20
       decrypt took the encrypted message in and the cipherArray and used this to convert the message back to its original form
 6. Did I write the main function body correctly (repeatedly obtaining a string and encrypting it and then decrypting the encrypted version)?

       Mark out of 25:25
       main took in a sentence and made it lowercase before passing it to be encrypted and decrypted. The cipherArray was also created here
 7. How well did I complete this self-assessment?

        Mark out of 5:5
        I commented on all of the points saying why I deserved the marks
 Total Mark out of 100 (Add all the previous marks):100

*/

import java.util.Scanner;
import java.util.Objects;
import java.util.Random;

public class Cipher{

    public static final int ALPHABET_START = 97;

    public static void main(String[] args){
        boolean quit = false;

        while(!quit){
            System.out.print("What would you like to encrypt: ");
            Scanner input = new Scanner(System.in);
            String inputString = input.nextLine();

            if(Objects.equals(inputString, "quit")){
                quit = true;
                System.out.println("Goodbye.");
            }
            else {
                inputString = inputString.toLowerCase();

                char[][] cipherArray = createCipher();
                String encryptedString = encrypt(inputString.toCharArray(), cipherArray);
                String decryptedString = decrypt(encryptedString.toCharArray(), cipherArray);

                printCipherArray(cipherArray);

                System.out.println("The encrypted string is: " + encryptedString);
                System.out.println("The decrypted string is: " + decryptedString);
            }
        }
    }

    public static char[][] createCipher(){
        Random generator = new Random();
        char[][] randomMapping = new char[27][27];
        randomMapping[0][26] = ' ';
        randomMapping[1][26] = ' ';

        int ascii = ALPHABET_START;
        for(int index = 0; index < randomMapping.length - 1; index++){
            randomMapping[0][index] = (char)ascii;
            randomMapping[1][index] = (char)ascii;
            ascii++;
        }

        for (int index = 0; index < randomMapping.length; index++){
            int otherIndex = generator.nextInt(randomMapping.length);
            char temp = randomMapping[1][index];
            randomMapping[1][index] = randomMapping[1][otherIndex];
            randomMapping[1][otherIndex] = temp;
        }
        return randomMapping;
    }

    public static String encrypt(char[] inputString, char[][] cipherArray){
        char[] encryptedString = new char[inputString.length];

        for(int index = 0; index < encryptedString.length; index++){
            for(int cipherIndex = 0; cipherIndex < cipherArray[0].length; cipherIndex++){
                if(inputString[index] == cipherArray[0][cipherIndex]){
                    encryptedString[index] = cipherArray[1][cipherIndex];
                }
            }
        }
        return new String(encryptedString);
    }

    public static String decrypt(char[] encryptedString, char[][] cipherArray){
        char[] decryptedString = new char[encryptedString.length];

        for(int index = 0; index < decryptedString.length; index++){
            for(int cipherIndex = 0; cipherIndex < cipherArray[1].length; cipherIndex++){
                if(encryptedString[index] == cipherArray[1][cipherIndex]){
                    decryptedString[index] = cipherArray[0][cipherIndex];
                }
            }
        }
        return new String(decryptedString);
    }

    public static void printCipherArray(char[][] cipherArray){
        for(int index1 = 0; index1 < 2; index1++){
            for(int index2 = 0; index2 < 27; index2++){
                System.out.print(cipherArray[index1][index2]);
            }
            System.out.println();
        }
    }
}
