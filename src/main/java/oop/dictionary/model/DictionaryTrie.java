package oop.dictionary.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 * dictionary prefix tree.
 * 
 */
public class DictionaryTrie {
    private final Node root;
    private static int count;
    private static ArrayList<Word> dictionary;
    public DictionaryTrie() {
        count = 0;
        root = new Node(count);
        dictionary = Dictionary.getInstance().getDictionary();
    }
    
    public void insert(Word newWord) {
        String word = newWord.getWordTarget();
        Node currentNode = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            Node node = currentNode.children.get(c);
            if (node == null) {
                node = new Node(count);
                currentNode.children.put(c, node);
            }
            currentNode = node;
        }
        count++;
    }
    
    public ArrayList<Word> search(String word) {
        ArrayList<Word> list = new ArrayList<Word>();
        Node currentNode = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            Node node = currentNode.children.get(c);
            if (node == null) {
                return null;
            }
            currentNode = node;
        }
        int start = currentNode.index;
        for (int i = start; i < Math.min(dictionary.size(), start + 30); i++) {
            list.add(dictionary.get(i));
        }
        return list;
    }
    
    
    private class Node {
        public Map<Character, Node> children;
        public int index;
        
        public Node(int idx) {
            children = new HashMap<Character, Node>();
            index = idx;
        }
    }
}
