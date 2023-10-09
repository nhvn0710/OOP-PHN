public class Word {
    private String wordTarget;
    private String wordExplain;
    private static int wordTargetLength = 7;
    public Word(String wordTarget, String wordExplain) {
        this.wordTarget = wordTarget;
        this.wordExplain = wordExplain;
        wordTargetLength = Math.max(wordTargetLength, wordTarget.length());
    }

    public String getWordTarget() {
        return wordTarget;
    }

    public void setWordExplain(String wordExplain) {
        this.wordExplain = wordExplain;
    }

    public String getWordExplain() {
        return wordExplain;
    }

    public static int getTargetLength() {
        return wordTargetLength;
    }

    /*
    * show word theo dinh dang "wordtarget  | wordexplain".
    */
    public String showWord() {
        String s = wordTarget;
        for (int i = 1; i <= wordTargetLength - wordTarget.length() + 1; i++) {
            s += " ";
        }
        s += "|  ";
        s += wordExplain;
        return s;
    }
}
