package PhHuNg;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Dictionary {
    private static ArrayList<Word> dictionary;
    public Dictionary() {
        dictionary = new ArrayList<>();
    }

    private int shiftLeft (int left, int right, char target, int index) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            String str = dictionary.get(mid).getWordTarget();
            if (index >= str.length()) {
                left = mid + 1;
            }
            else {
                if (target > str.charAt(index)) {
                    left = mid + 1;
                }
                if (index < dictionary.get(left).getWordTarget().length() && target == dictionary.get(left).getWordTarget().charAt(index)) {
                    return left;
                }
                if (target == str.charAt(index)) {
                    if (right == mid)
                        return right;
                    right = mid;
                }
            }
        }
        return left;
    }

    private int shiftRight (int left, int right, char target, int index) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            String str = dictionary.get(mid).getWordTarget();
            if (target < str.charAt(index)) {
                right = mid - 1;
            }
            if (target == dictionary.get(right).getWordTarget().charAt(index)) {
                return right;
            }
            if (target == str.charAt(index)) {
                if (left == mid)
                    return left;
                left = mid;
            }
        }
        return right;
    }

    private Pair<Integer, Integer> binarySearch (int left, int right, char target, int index) {
        Pair p1 = new Pair<>(left, right);
        while (left <= right) {
            int mid = left + (right - left) / 2;
            String str = dictionary.get(mid).getWordTarget();
            if (index >= str.length()) {
                left = mid + 1;
            }
            else {
                char c = str.charAt(index);
                if (target > c) {
                    left = mid + 1;
                }
                if (target < c) {
                    right = mid - 1;
                }
                if (target == c) {
                    // shift left
                    left = shiftLeft(left, mid, target, index);
                    // shift right
                    right = shiftRight(mid, right, target, index);
                    p1 = new Pair<>(left, right);
                    return p1;
                }
            }
        }
        return p1;
    }

    public ArrayList<Word> searchWord(String word) {
        ArrayList list = new ArrayList<>();
        int left = 0;
        int right = dictionary.size() - 1;
        for (int index = 0; index < word.length(); index++) {
            left = binarySearch(left, right, word.charAt(index), index).getKey();
            right = binarySearch(left, right, word.charAt(index), index).getValue();
        }
        for (int index = left; index <= right; index++) {
            list.add(dictionary.get(index));
        }
        return list;
    }

    public void addWord(String wordTarget, String wordExplain) {
        Word w = new Word(wordTarget, wordExplain);
        dictionary.add(w);
    }

    public void sort() {
        Collections.sort(dictionary, new Comparator<Word>() {
            @Override
            public int compare(Word o1, Word o2) {
                String s1 = o1.getWordTarget();
                String s2 = o2.getWordTarget();
                return s1.compareToIgnoreCase(s2);
            }
        });
    }

    public ArrayList<Word> getDictionary() {
        return dictionary;
    }

    public void showWords() {
        String numWords = Integer.toString(dictionary.size());
        System.out.print("No");
        for (int j = 0; j < numWords.length(); j++) {
            System.out.print(" ");
        }
        System.out.print("|  English");
        int targetLength = Word.getTargetLength();
        for (int i = 1; i <= targetLength - 6; i++) {
            System.out.print(" ");
        }
        System.out.println("|  Vietnamese");
        for (int i = 0; i < dictionary.size(); i++) {
            String iSize = Integer.toString(i + 1);
            String dSize = Integer.toString(dictionary.size());
            System.out.print(i + 1);
            for (int j = 0; j < dSize.length() - iSize.length() + 2; j++) {
                System.out.print(" ");
            }
            System.out.print("|  ");
            System.out.println(dictionary.get(i).showWord());
        }
    }

    /*
    * toi uu = binary search
    * */
    public Word lookUp(String word) {
        for (int i = 0; i < dictionary.size(); i++) {
            if (dictionary.get(i).getWordTarget().equals(word))
                return dictionary.get(i);
        }
        return null;
    }

    public void delete(String word) {
        Word removeWord = lookUp(word);
        dictionary.remove(removeWord);
    }
}
