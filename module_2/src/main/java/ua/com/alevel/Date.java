package ua.com.alevel;

import java.util.List;

public class Date {

    public static void run(List<String> input) {
        StringBuilder output = new StringBuilder();
        for (String s : input) {
            if (s.contains("/")) {
                String[] inputString = s.split("/");
                output.append(dateOutput(inputString)).append("\n");
            }
            if (s.contains("-")) {
                String[] inputString = s.split("-");
                output.append(dateOutput(inputString)).append("\n");
            }
        }
        Output.run(output.toString());
    }

    public static String dateOutput(String[] input) {
        int[] date = new int[3];
        int year = 0;
        int month = 0;
        int day = 0;
        if (input.length != 3) return "ignored";
        for (int i = 0; i < input.length; i++) {
            try {
                date[i] = Integer.parseInt(input[i]);
            } catch (NumberFormatException e) {
                if (e != null) return "ignored";
            }
        }
        for (int i : date) {
            if (i > 31) {
                year = i;
                continue;
            }
            if (i > 12 && i < 31) {
                day = i;
            } else {
                if (month == 0) {
                    month = i;
                } else {
                    day = i;
                }
            }
        }
        if (isCorrect(year, month, day)) {
            return year + format(month) + format(day);
        } else {
            return "ignored";
        }
    }

    public static String format(int input) {
        if (input < 10) {
            return "0" + input;
        } else {
            return String.valueOf(input);
        }
    }

    public static boolean isCorrect(int year, int month, int day) {
        if (year == 0 || month == 0 || day == 0) return false;
        if (year > 9999) return false;
        if (month > 12) return false;
        if ((month == 1 && day > 31) ||
                (month == 2 && day > 28 && year % 4 != 0) ||
                (month == 2 && day > 29 && year % 4 == 0) ||
                (month == 3 && day > 31) ||
                (month == 4 && day > 30) ||
                (month == 5 && day > 31) ||
                (month == 6 && day > 30) ||
                (month == 7 && day > 31) ||
                (month == 8 && day > 31) ||
                (month == 9 && day > 30) ||
                (month == 10 && day > 31) ||
                (month == 11 && day > 30) ||
                (month == 12 && day > 31)) return false;
        return true;
    }
}
