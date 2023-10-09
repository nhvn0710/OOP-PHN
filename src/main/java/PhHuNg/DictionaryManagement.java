import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.io.*;

public class DictionaryManagement {

    private static Dictionary dict;

    public DictionaryManagement() {
        dict = new Dictionary();
    }

    public Dictionary getDictionary() {
        return dict;
    }

    public void insertFromFile(String path) {
        dict = new Dictionary();
        try {
            File inFile = new File(path);
            FileReader fileReader = new FileReader(inFile);

            BufferedReader reader = new BufferedReader(fileReader);
            String line = null;

            while ((line = reader.readLine()) != null) {
                String[] word = line.split("\t");
                dict.addWord(word[0], word[1]);
            }
            dict.sort();
            reader.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertFromCommandline() {
        Scanner input = new Scanner(System.in);
        int num = input.nextInt();
        for (int i = 0; i < num; i++) {
            Scanner str = new Scanner(System.in);
            String target = str.nextLine();
            String explain = str.nextLine();
            dict.addWord(target, explain);
        }
        input.close();
    }

    public void dictionaryLookup(String str) {
        Word word = dict.lookUp(str);
        System.out.println(word.getWordTarget() + "  " + word.getWordExplain());
    }

    public void addWord(String wordTarget, String wordExplain) {
        dict.addWord(wordTarget, wordExplain);
    }

    public void editWord(String wordTarget, String wordExplain) {
        Word newWord = dict.lookUp(wordTarget);
        newWord.setWordExplain(wordExplain);
    }

    public void deleteWord(String wordTarget) {
        dict.delete(wordTarget);
    }

    public void dictionaryExportToFile(String path) {
        try {
            FileWriter fileWriter = new FileWriter(path);

            ArrayList<Word> dictionary = dict.getDictionary();

            for (int i = 0; i < dictionary.size(); i++) {
                fileWriter.write(dictionary.get(i).getWordTarget() + "\t" + dictionary.get(i).getWordExplain() + "\n");
            }
            fileWriter.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchWord(String word) {
//        Pair p1 = dict.searchWord(word);
//        System.out.print(p1.getKey() + "  " + p1.getValue());
        ArrayList<Word> list = dict.searchWord(word);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + " " + list.get(i).getWordTarget() + " :  " + list.get(i).getWordExplain());
        }
    }

}
