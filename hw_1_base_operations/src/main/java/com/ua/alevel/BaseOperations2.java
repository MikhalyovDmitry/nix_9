package ua.com.alevel;

import java.util.Scanner;

public class BaseOperations2 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Input: ");
        String str = in.nextLine();
        sortLetters(str);
    }

    public static void sortLetters(String instr) {
        StringBuilder sb = new StringBuilder(instr);
        int i;
        int count;
        while (sb.length() > 0) {
            i = 0;
            count = 0;
            if (Character.isLetter(sb.charAt(0))) {
                System.out.print(sb.charAt(0) + " - ");
                count++;
                for (int j = 1; j < sb.length(); j++) {
                    if (sb.charAt(j) == sb.charAt(i)) {
                        count++;
                        sb.delete(j, j + 1);
                        j--;
                    }
                }
                System.out.println(count);
            }
            sb.delete(i, i + 1);
        }
    }
}
