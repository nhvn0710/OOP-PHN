import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Stack<String> st = new Stack<>();
        sc.nextLine();
        String s="";
 
        for (int i = 0; i < n; i++) {
            String sr = sc.nextLine();
            String[] str = sr.split(" ");
            String op = str[0];
            
            if (op.equals("1")) {
                s += str[1];
                System.out.println(s);
                st.push(s);
            }
            else if (op.equals("2")) {
                int k = Integer.parseInt(str[1]);
                String p = st.peek();
                System.out.println(p);
                s = p.substring(0, p.length() - k);
                System.out.println(s);
                st.push(s);
                p=st.peek();
                System.out.println(p);
            }
            else if (op.equals("3")) {
                int k = Integer.parseInt(str[1]);
                String p = st.peek();
                System.out.println(p);
                System.out.println(p.charAt(k-1));
            } 
            else {
                st.pop();
                if (!st.empty()) {
                    s = st.peek();
                    System.out.println(s);
                } else {
                    s = "";
                }
            }
        }
    }
}
