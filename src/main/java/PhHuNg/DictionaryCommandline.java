package PhHuNg;

import java.util.Scanner;

public class DictionaryCommandline {
    private static DictionaryManagement dictManager;

    public DictionaryCommandline() {
        dictManager = new DictionaryManagement();
    }

    public DictionaryCommandline(DictionaryManagement dictManager) {
        this.dictManager = dictManager;
    }

    public void showAllWords() {
        dictManager.getDictionary().showWords();
    }

    public void dictionaryBasic() {
        dictManager.insertFromCommandline();
        showAllWords();
    }

    public void insertFromFile(String path) {
        dictManager.insertFromFile(path);
    }

    public void saveDict(String path) {
        dictManager.dictionaryExportToFile(path);
    }

    public void showMenu() {
        System.out.println("Welcome to My Application!");
        System.out.println("[0] Exit");
        System.out.println("[1] Add");
        System.out.println("[2] Remove");
        System.out.println("[3] Update");
        System.out.println("[4] Display");
        System.out.println("[5] Lookup");
        System.out.println("[6] Import from file");
        System.out.println("[7] Export to file");
        System.out.println("[8] Search word");
        System.out.print("Your action: ");
    }

    public void dictionaryAdvanced() {
        showMenu();
        Scanner input = new Scanner(System.in);
        String wordTarget;
        String wordExplain;
        String path;
        int choice = input.nextInt();
        while (choice != 0) {
            input.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Word target: ");
                    wordTarget = input.nextLine();
                    System.out.print("Word explain: ");
                    wordExplain = input.nextLine();
                    dictManager.addWord(wordTarget, wordExplain);
                    choice = input.nextInt();
                    break;
                case 2:
                    System.out.print("Remove word target: ");
                    wordTarget = input.nextLine();
                    dictManager.deleteWord(wordTarget);
                    choice = input.nextInt();
                    break;
                case 3:
                    System.out.print("Word target: ");
                    wordTarget = input.nextLine();
                    System.out.print("Word explain: ");
                    wordExplain = input.nextLine();
                    dictManager.editWord(wordTarget, wordExplain);
                    choice = input.nextInt();
                    break;
                case 4:
                    dictManager.getDictionary().showWords();
                    choice = input.nextInt();
                    break;
                case 5:
                    System.out.print("Word target: ");
                    wordTarget = input.nextLine();
                    dictManager.dictionaryLookup(wordTarget);
                    choice = input.nextInt();
                    break;
                case 6:
                    System.out.print("Path: ");
                    path = input.nextLine();
                    dictManager.insertFromFile(path);
                    System.out.println("Done");
                    choice = input.nextInt();
                    break;
                case 7:
                    System.out.print("Path: ");
                    path = input.nextLine();
                    dictManager.dictionaryExportToFile(path);
                    System.out.println("Done");
                    choice = input.nextInt();
                    break;
                case 8:
                    System.out.print("Word target: ");
                    wordTarget = input.nextLine();
                    dictManager.searchWord(wordTarget);
                    choice = input.nextInt();
                    break;
            }
        }
        return;
    }

}
