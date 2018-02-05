import java.io.*;
import java.util.Scanner;
import java.util.Set;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;


public class PuzzleGame{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        boolean finished = false;
        while(!finished){
            System.out.print("Enter your list of words: ");
            String words = in.nextLine();

            if(words.equals("quit")){
                finished = true;
            } else {
                ArrayList<String> wordList = readWordList(words);

                if (isWordChain(wordList)){
                    System.out.println("Valid chain of words from Lewis Carroll's word-links game.");
                }
                else {
                    System.out.println("Not a valid chain of words from Lewis Carroll's word-links game.");
                }
            }
        }
    }

    public static ArrayList<String> readDictionary(BufferedReader dictionary){
        ArrayList<String> dictionaryList = new ArrayList<String>();
        try {
	        String nextWord = dictionary.readLine();
	        while(nextWord != null){
	            dictionaryList.add(nextWord);
	            nextWord = dictionary.readLine();
	        }
        } catch (Exception e) {
        	System.out.println(e);
        }
        return dictionaryList;
    }

    public static ArrayList<String> readWordList(String words){
        ArrayList<String> wordList = new ArrayList<String>();
        Scanner seperator = new Scanner(words);
        seperator.useDelimiter(", ");
        while(seperator.hasNext()){
            wordList.add(seperator.next());
        }
        return wordList;
    }

    public static boolean isUniqueList(ArrayList<String> wordList){
        Set<String> set = new HashSet<String>(wordList);
        if(set.size() < wordList.size()) return false;
        else return true;
    }

    public static boolean isEnglishWord(String word, ArrayList<String> dictionaryList){
        String[] dictionaryArray = dictionaryList.toArray(new String[dictionaryList.size()]);
        if(Arrays.binarySearch(dictionaryArray, word) >= 0) return true;
    	else return false;
    }

    public static boolean isDifferentByOne(String wordOne, String wordTwo){
        if(wordOne.length() == wordTwo.length()){
            boolean oneDiff =  false;
            for(int i = 0; i < wordOne.length(); i++){
                if(wordOne.charAt(i) != wordTwo.charAt(i)) {
                    if(oneDiff)
                        return false;
                    else
                        oneDiff = true;
                }
            }
            return true;
        }
        return false;
    }

    public static boolean isWordChain(ArrayList<String> wordList){
        try{
            FileReader fileReader = new FileReader("dictionary.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            ArrayList<String> dictionary = readDictionary(bufferedReader);

            if(isUniqueList(wordList)){
	            for(int i = 0; i < wordList.size()-1; i++){
	                if(isEnglishWord(wordList.get(i), dictionary) && isEnglishWord(wordList.get(i+1), dictionary)){
	                    if(!isDifferentByOne(wordList.get(i), wordList.get(i+1))) return false;
	                } else {
	                    return false;
	                }
	            }
            } else {
                return false;
            }
            return true;
        } catch(Exception e) {
            System.out.println(e);
        }
		return false;
    }
}
