package patch;

import java.util.Scanner;
import java.util.SplittableRandom;

public class Hurt {
    public static String deleteOlyx(String reading) {

        StringBuilder complete = new StringBuilder();
        String exceptions = "Олух! Олух, Олух. Олух? Олухи Олуха Олухов Олуху Олухам Олухом Олухами Олухе Олухах" +
                "Олухи, Олуха, Олухов, Олуху, Олухам, Олухом, Олухами, Олухе, Олухах," +
                "Олухи! Олуха! Олухов! Олуху! Олухам! Олухом! Олухами! Олухе! Олухах!" +
                "Олухи? Олуха? Олухов? Олуху? Олухам? Олухом? Олухами? Олухе? Олухах?" +
                "Олухи. Олуха. Олухов. Олуху. Олухам. Олухом. Олухами. Олухе. Олухах.";
        for (String word : reading.split(" ")) {
            if (!(exceptions.contains(word))){
                word = word.replaceAll("Олух","");
            }
            complete.append(" ").append(word);
        }
        return complete.toString();
    }

   public static String cut(String wordWithPunctuation){
        String wordWithoutPunctuation;
        wordWithPunctuation = wordWithPunctuation.toLowerCase();
        wordWithoutPunctuation = wordWithPunctuation;
       for(int i = wordWithPunctuation.length(); i >= 1; i--){
            if (punctuationChecker(wordWithoutPunctuation)){
                wordWithoutPunctuation = wordWithPunctuation.substring(0, i-1);
            }
        }
        return wordWithoutPunctuation;
   }

   public static boolean punctuationChecker(String wordWithPunctuation){
       boolean checker = false;
       String[] exception = new String[4];
       exception[0] = "!";
       exception[1] ="?";
       exception[2] =".";
       exception[3] =",";
        if ((wordWithPunctuation.contains(exception[0]) || wordWithPunctuation.contains(exception[1]) || wordWithPunctuation.contains(exception[2]) || wordWithPunctuation.contains(exception[3]))) {
            checker = true;
        }
        return checker;
   }

    public static boolean sentenseCkecker(char wordWithPunctuation, int schetchik, int uslovieSchetchika){
        boolean checker = false;
        char[] exception = new char[3];
        exception[0] = '!';
        exception[1] ='?';
        exception[2] ='.';
        if (((wordWithPunctuation == exception[0]) || (wordWithPunctuation == exception[1]) || (wordWithPunctuation==exception[2])) && (schetchik < uslovieSchetchika)) {
            checker = true;
        }
        return checker;
    }

    public static String punctuation(String wordWithPunctuation){
        String wordWithoutPunctuation;
        String symbols = "";
        wordWithoutPunctuation = wordWithPunctuation;
        for(int i = wordWithPunctuation.length(); i >= 1; i--){
            if (punctuationChecker(wordWithoutPunctuation)){
                symbols = wordWithPunctuation.substring(i-1) + symbols;
                wordWithoutPunctuation = wordWithPunctuation.substring(0, i-2);
            }
        }
        return symbols;
    }

    public static String replacerSlogov(String reading) {
        String complete = "";
        for (String word : reading.split(" ")) {
            String intermediateResult = "";
            String punct = punctuation(word);
            word = cut(word);
            for(int i = word.length(); i >= 2; i=i-2){
                intermediateResult += word.substring(i-2, i);
            }
            if (word.length() %2 != 0){
                intermediateResult += word.substring(0, 1);
            }
            complete = complete + " " + intermediateResult + punct;
        }
        complete = complete.substring(0, 2).toUpperCase() + complete.substring(2);
        for(int i = 0; i < complete.length(); i++){
            char letter = complete.charAt(i);
            if(sentenseCkecker(letter, i+3, complete.length())){
                complete = complete.substring(0, i+2) + complete.substring(i+2, i+3).toUpperCase() + complete.substring(i+3);
            }
        }
        return complete;
    }

    public static String chooser (String reading){
        String complete = "";
        if (reading.contains("Олух")){
            complete = deleteOlyx(reading);
        }
        else{
            complete = replacerSlogov(reading);
        }
        return complete;
    }
//ПСУ кАбАсО. пСу
}