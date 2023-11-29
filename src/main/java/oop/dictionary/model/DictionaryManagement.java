package oop.dictionary.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DictionaryManagement {
    
    private static DictionaryManagement instance;
    private static Dictionary dict;
    private static ArrayList<Word> listWord;
    private static DictionaryTrie dictionaryTrie;
    ConnectJDBC connectJDBC;
    Connection conn;


    
    private DictionaryManagement() {
        dict = Dictionary.getInstance();
        listWord = dict.getDictionary();
        dictionaryTrie = new DictionaryTrie();

        insertFromFile("file.txt");

//        connectJDBC = new ConnectJDBC();
//        insertData();
    }

    public void delete(Word word) {
        dict.delete(word);
    }

    public void add(Word word) {
        dict.add(word);
        dictionaryTrie.insert(word);
    }

    public static DictionaryManagement getInstance() {
        if (instance == null) {
            instance = new DictionaryManagement();
        }
        return instance;
    }

    private void insertData() {
        conn = connectJDBC.connect();
        
        String query = "SELECT * FROM tbl_edict";
        
        Statement stm = null;
        try {
            stm = conn.createStatement();

            ResultSet rs = stm.executeQuery(query);

            while (rs.next()){
                String wordString = rs.getString("word");
                String detailString = rs.getString("detail");
                dictionaryTrie.insert(new Word(wordString, wordString));
                listWord.add(new Word(wordString, detailString));
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void insertFromFile(String path) {
        try {
            File inFile = new File(path);
            FileReader fileReader = new FileReader(inFile);

            BufferedReader reader = new BufferedReader(fileReader);
            String line = null;

            while ((line = reader.readLine()) != null) {
                String[] word = line.split("\t");
                dictionaryTrie.insert(new Word(word[0], word[1]));
                listWord.add(new Word(word[0], word[1]));
            }
            reader.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
//    private void sort() {
//        Collections.sort(listWord, new Comparator<Word>() {
//            @Override
//            public int compare(Word o1, Word o2) {
//                String s1 = o1.getWordTarget();
//                String s2 = o2.getWordTarget();
//                return s1.compareToIgnoreCase(s2);
//            }
//        });
//    }

    public void dictionaryExportToFile() {
        try {
            FileWriter fileWriter = new FileWriter("file.txt");

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

    public ArrayList<Word> getArrayWords() {
        return listWord;
    }
    
    public ArrayList<Word> searchWord(String word) {
        return dictionaryTrie.search(word);
    }
}