package encryptor;

import java.util.Random;

public final class RandomEncryption {
    private static final Random RANDOM = new Random();
    private static final int MAX_BOOB_FOR_WORD_NUMBER = 3;
    private static final String BOOB = "Олух";

    public static String encrypt(String text) {
        int decision = RANDOM.nextInt(2);

        if (decision == 0) {
            return encryptWithBoobCypher(text);
        } else  {
            return encryptWithSyllableCypher(text);
        }
    }

    private static String encryptWithBoobCypher(String text) {
        StringBuilder result = new StringBuilder();

        for (String word : text.split(" ")) {
            insertBoobs(result, word);

            result.append(" ");
        }

        return result.toString();
    }

    private static void insertBoobs(StringBuilder result, String word) {
        int boobNumber = 0;
        int currentIndex = 0;
        int targetIndex;

        while (boobNumber < MAX_BOOB_FOR_WORD_NUMBER && currentIndex < word.length()) {
            targetIndex = chooseRandomBoobIndex(word, currentIndex);

            result.append(word.substring(currentIndex, targetIndex));
            result.append(BOOB);

            currentIndex = targetIndex;
            boobNumber++;
        }

        appendWordEnd(result, word, currentIndex);
    }

    private static int chooseRandomBoobIndex(String word, int currentIndex) {
        return currentIndex + RANDOM.nextInt(word.length() - currentIndex);
    }

    private static void appendWordEnd(StringBuilder result, String word, int currentIndex) {
        if (currentIndex < word.length()) {
            result.append(word.substring(currentIndex));
        }
    }

    private static String encryptWithSyllableCypher(String text) {
        StringBuilder result = new StringBuilder();

        for (String word : text.split(" ")) {
            spoilWordSyllables(result, word);
        }

        return result.toString().trim();
    }

    private static void spoilWordSyllables(StringBuilder result, String word) {
        String endingPunctuations = "";

        int firstFromEndLetterIndex = getFirstFromEndLetterIndex(word);

        if (firstFromEndLetterIndex != -1) {
            endingPunctuations = getEndingPunctuations(word, firstFromEndLetterIndex);
            word = deleteEndingPunctuations(word, firstFromEndLetterIndex);
        }

        String[] syllables = new String[getSyllablesNumberWithOneExtra(word)];

        if (word.length() % 2 != 0) {
            word = cutLastLetter(syllables, word);
        }

        splitWordOnSyllables(syllables, word);

        mixSyllables(syllables);

        makeLadderByLetterCases(result, syllables);

        result.append(endingPunctuations);
        result.append(" ");
    }

    private static int getFirstFromEndLetterIndex(String word) {
        String punctuations = ",.!?";

        for (int index = word.length() - 1; index >= 0; index--) {
            if (!punctuations.contains("" + word.charAt(index))) {
                return index;
            }
        }

        return -1;
    }

    private static String getEndingPunctuations(String word, int firstFromEndLetterIndex) {
        return word.substring(firstFromEndLetterIndex + 1);
    }

    private static String deleteEndingPunctuations(String word, int firstFromEndLetterIndex) {
        return word.substring(0, firstFromEndLetterIndex + 1);
    }

    private static int getSyllablesNumberWithOneExtra(String word) {
        return word.length() / 2 + 1;
    }

    private static String cutLastLetter(String[] syllables, String word) {
        syllables[word.length() / 2] = word.substring(word.length() - 1);
        return word.substring(0, word.length() - 1);
    }

    private static void splitWordOnSyllables(String[] syllables, String word) {
        int currentSyllableIndex = 0;

        while (word.length() > 0) {
            syllables[currentSyllableIndex] = word.substring(0, 2);
            word = word.substring(2);
            currentSyllableIndex++;
        }
    }

    private static void mixSyllables(String[] syllables) {
        for (int index = syllables.length - 1; index >= syllables.length / 2; index--) {
            String temp = syllables[syllables.length - 1 - index];
            syllables[syllables.length - 1 - index] = syllables[index];
            syllables[index] = temp;
        }
    }

    private static void makeLadderByLetterCases(StringBuilder result, String[] syllables) {
        for (String syllable : syllables) {
            if (syllable != null) {
                result.append(syllable.substring(0, 1).toLowerCase());
                result.append(syllable.substring(1).toUpperCase());
            }
        }
    }
}
