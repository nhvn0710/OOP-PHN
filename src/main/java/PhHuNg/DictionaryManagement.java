package PhHuNg;

import java.util.Scanner;
import java.util.Stack;

public class DictionaryManagement {

    public static void main(String[] args) {
        /**
         * This is an implementation of a simple text editor from Hackerrank.
         * The editor initially contains an empty string, S. Perform  operations of the following  types:
         *
         * 1 + [string]: append(w) - Append string w to the end of S.
         * 2 + [int]: delete(k) - Delete the last k characters of S.
         * 3 + [int]: print(k) - Print the kth character of S.
         * 4: undo() - Undo the last (not previously undone) operation of type 1 or 2, reverting S to the state it was.
         */
        String sw = "";
        Stack<String> spre = new Stack<>();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            int num = sc.nextInt();
            switch (num) {
                case 1 -> {
                    String a1 = sc.nextLine();
                    a1 = a1.substring(1);
                    spre.push(sw);
                    sw = sw + a1;
                }
                case 2 -> {
                    int a2 = sc.nextInt();
                    spre.push(sw);
                    sw = sw.substring(0, sw.length() - a2);
                }
                case 3 -> {
                    int a3 = sc.nextInt();
                    System.out.println(sw.charAt(a3 - 1));
                }
                case 4 -> {
                    sw = spre.peek();
                    spre.pop();
                }
            }
        }
    }
}