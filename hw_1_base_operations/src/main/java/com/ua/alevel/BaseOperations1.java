package ua.com.alevel;
import java.util.Scanner;

public class BaseOperations1 {

public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.print("Введите строку: ");
        String str = in.nextLine();
        System.out.println(sumOfInts(str));
}

public static int sumOfInts(String instr) {
        int result = 0;
        if (instr.length() == 0) return 0;
        for (int i = 0; i < instr.length(); i++) {
                if (Character.isDigit(instr.charAt(i))) {
                        result += Character.getNumericValue(instr.charAt(i));
                }
        }
        return result;
}

}
