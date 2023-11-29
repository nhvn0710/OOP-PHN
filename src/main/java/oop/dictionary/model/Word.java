package oop.dictionary.model;

/**
 * Word model
 */
public class Word {
    private String wordTarget;
    private String wordExplain;

    public Word(String wordTarget, String wordExplain) {
        this.wordTarget = wordTarget;
        this.wordExplain = wordExplain;
    }

    public void setWordExplain(String wordExplain) {
        this.wordExplain = wordExplain;
    }

    public String getWordTarget() {
        return wordTarget;
    }

    public String getWordExplain() {
        return wordExplain;
    }

    public String getOnlyExplain() {
        if (wordExplain.contains("\"<br />\"")) {
        return wordExplain.split("<br />",2)[1] + "<br />";} else {
            return wordExplain;
        }
    }

    public String toString() {
        return getWordTarget();
    }

}