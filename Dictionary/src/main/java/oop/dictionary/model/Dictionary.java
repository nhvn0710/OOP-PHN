package oop.dictionary.model;

import java.util.ArrayList;

public class Dictionary {
    private static Dictionary instance;
    private static ArrayList<Word> dictionary;
    private Dictionary() {
        dictionary = new ArrayList<>();
    }
    
    public static Dictionary getInstance() {
        if (instance == null) {
            instance = new Dictionary();
        }
        return instance;
    }

    public void delete(Word word) {
        dictionary.remove(word);
    }

    public void add(Word word) {
        dictionary.add(word);
    }

    public ArrayList<Word> getDictionary() {
        return dictionary;
    }
}
