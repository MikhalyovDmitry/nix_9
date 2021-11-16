package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Time {
    static int jan = 31;
    static int feb = 28;
    static int mar = 31;
    static int apr = 30;
    static int may = 31;
    static int jun = 30;
    static int jul = 31;
    static int aug = 31;
    static int sep = 30;
    static int oct = 31;
    static int nov = 30;

    public static double inputTime(int year, int month, int day, int hour, int minute, int second, int millis) {
        double days = 0;
        for (int i = 1; i <= year; i++) {
            days += 365;
            if (leap(i - 1)) days++;
        }
        switch (month) {
            case 2 -> days += jan;
            case 3 -> {
                days += jan + feb;
                if (leap(year)) days++;
            }
            case 4 -> {
                days += jan + feb + mar;
                if (leap(year)) days++;
            }
            case 5 -> {
                days += jan + feb + mar + apr;
                if (leap(year)) days++;
            }
            case 6 -> {
                days += jan + feb + mar + apr + may;
                if (leap(year)) days++;
            }
            case 7 -> {
                days += jan + feb + mar + apr + may + jun;
                if (leap(year)) days++;
            }
            case 8 -> {
                days += jan + feb + mar + apr + may + jun + jul;
                if (leap(year)) days++;
            }
            case 9 -> {
                days += jan + feb + mar + apr + may + jun + jul + aug;
                if (leap(year)) days++;
            }
            case 10 -> {
                days += jan + feb + mar + apr + may + jun + jul + aug + sep;
                if (leap(year)) days++;
            }
            case 11 -> {
                days += jan + feb + mar + apr + may + jun + jul + aug + sep + oct;
                if (leap(year)) days++;
            }
            case 12 -> {
                days += jan + feb + mar + apr + may + jun + jul + aug + sep + oct + nov;
                if (leap(year)) days++;
            }
        }
        days += day;
        double hours = days * 24 + hour;
        double minutes = hours * 60 + minute;
        double seconds = minutes * 60 + second;
        return seconds * 1000 + millis;
    }

    public static String format(int value) {
        String result;
        if (value < 10) {
            result = "0" + value;
        } else {
            result = String.valueOf(value);
        }
        return result;
    }

    public static String formatMillis(int value) {
        String result;
        result = String.valueOf(value);
        if (value < 100) result = "0" + value;
        if (value < 10) result = "00" + value;
        if (value == 0) result = "000";
        return result;
    }

    public static String yearYy(int year) {
        String yy;
        year = year % 100;
        if (year < 10) {
            yy = "0" + year;
        } else {
            yy = String.valueOf(year);
        }
        return yy;
    }

    public static String monthMmm(int month) {
        String mmm = null;
        switch (month) {
            case 1 -> mmm = "Январь";
            case 2 -> mmm = "Февраль";
            case 3 -> mmm = "Март";
            case 4 -> mmm = "Апрель";
            case 5 -> mmm = "Май";
            case 6 -> mmm = "Июнь";
            case 7 -> mmm = "Июль";
            case 8 -> mmm = "Август";
            case 9 -> mmm = "Сентябрь";
            case 10 -> mmm = "Октябрь";
            case 11 -> mmm = "Ноябрь";
            case 12 -> mmm = "Декабрь";
        }
        return mmm;
    }

    public static int monthMmm(String month) {
        int mmm = 0;
        switch (month) {
            case "Январь" -> mmm = 1;
            case "Февраль" -> mmm = 2;
            case "Март" -> mmm = 3;
            case "Апрель" -> mmm = 4;
            case "Май" -> mmm = 5;
            case "Июнь" -> mmm = 6;
            case "Июль" -> mmm = 7;
            case "Август" -> mmm = 8;
            case "Сентябрь" -> mmm = 9;
            case "Октябрь" -> mmm = 10;
            case "Ноябрь" -> mmm = 11;
            case "Декабрь" -> mmm = 12;
        }
        return mmm;
    }

    public static int millis(double time) {
        return (int) Math.floor(time % 1000);
    }

    public static int second(double time) {
        return (int) Math.floor((time / 1000) % 60);
    }

    public static int minute(double time) {
        return (int) Math.floor((time / 1000 / 60) % 60);
    }

    public static int hour(double time) {
        return (int) Math.floor((time / 1000 / 60 / 60) % 24);
    }

    public static int year(double time) {
        double seconds = time / 1000;
        double minutes = seconds / 60;
        double hours = minutes / 60;
        double days = hours / 24;
        int year = 0;
        int day = 0;
        for (int i = 1; i <= days; i++) {
            day++;
            if (!leap(year) && day == 366) {
                year++;
                day = 1;
            }
            if (leap(year) && day == 367) {
                year++;
                day = 1;
            }
        }
        return year;
    }

    public static int month(double time) {
        double seconds = time / 1000;
        double minutes = seconds / 60;
        double hours = minutes / 60;
        double days = hours / 24;
        int year = 0;
        int day = 0;
        for (int i = 1; i <= days; i++) {
            day++;
            if (!leap(year) && day == 366) {
                year++;
                day = 1;
            }
            if (leap(year) && day == 367) {
                year++;
                day = 1;
            }
        }
        int month = 0;
        if (day <= jan)
            month = 1;
        if (day > jan)
            month = 2;
        if (day > jan + feb && !leap(year)
                || day > jan + feb + 1 && leap(year))
            month = 3;
        if (day > jan + feb + mar && !leap(year)
                || day > jan + feb + mar + 1 && leap(year))
            month = 4;
        if (day > jan + feb + mar + apr && !leap(year)
                || day > jan + feb + mar + apr + 1 && leap(year))
            month = 5;
        if (day > jan + feb + mar + apr + may && !leap(year)
                || day > jan + feb + mar + apr + may + 1 && leap(year))
            month = 6;
        if (day > jan + feb + mar + apr + may + jun && !leap(year)
                || day > jan + feb + mar + apr + may + jun + 1 && leap(year))
            month = 7;
        if (day > jan + feb + mar + apr + may + jun + jul && !leap(year)
                || day > jan + feb + mar + apr + may + jun + jul + 1 && leap(year))
            month = 8;
        if (day > jan + feb + mar + apr + may + jun + jul + aug && !leap(year)
                || day > jan + feb + mar + apr + may + jun + jul + aug + 1 && leap(year))
            month = 9;
        if (day > jan + feb + mar + apr + may + jun + jul + aug + sep && !leap(year)
                || day > jan + feb + mar + apr + may + jun + jul + aug + sep + 1 && leap(year))
            month = 10;
        if (day > jan + feb + mar + apr + may + jun + jul + aug + sep + oct && !leap(year)
                || day > jan + feb + mar + apr + may + jun + jul + aug + sep + oct + 1 && leap(year))
            month = 11;
        if (day > jan + feb + mar + apr + may + jun + jul + aug + sep + oct + nov && !leap(year)
                || day > jan + feb + mar + apr + may + jun + jul + aug + sep + oct + nov + 1 && leap(year))
            month = 12;

        return month;
    }

    public static int day(double time) {
        double seconds = time / 1000;
        double minutes = seconds / 60;
        double hours = minutes / 60;
        double days = hours / 24;
        int year = 0;
        int day = 0;
        for (int i = 1; i <= days; i++) {
            day++;
            if (!leap(year) && day == 366) {
                year++;
                day = 1;
            }
            if (leap(year) && day == 367) {
                year++;
                day = 1;
            }
        }
        int d = 0;
        if (day <= jan) {
            d = day;
        }
        if (day > jan) {
            d = day - jan;
        }
        if (day > jan + feb && !leap(year)) {
            d = day - (jan + feb);
        }
        if (day > jan + feb + 1 && leap(year)) {
            d = day - (jan + feb + 1);
        }
        if (day > jan + feb + mar && !leap(year)) {
            d = day - (jan + feb + mar);
        }
        if (day > jan + feb + mar + 1 && leap(year)) {
            d = day - (jan + feb + mar + 1);
        }
        if (day > jan + feb + mar + apr && !leap(year)) {
            d = day - (jan + feb + mar + apr);
        }
        if (day > jan + feb + mar + apr + 1 && leap(year)) {
            d = day - (jan + feb + mar + apr + 1);
        }
        if (day > jan + feb + mar + apr + may && !leap(year)) {
            d = day - (jan + feb + mar + apr + may);
        }
        if (day > jan + feb + mar + apr + may + 1 && leap(year)) {
            d = day - (jan + feb + mar + apr + may + 1);
        }
        if (day > jan + feb + mar + apr + may + jun && !leap(year)) {
            d = day - (jan + feb + mar + apr + may + jun);
        }
        if (day > jan + feb + mar + apr + may + jun + 1 && leap(year)) {
            d = day - (jan + feb + mar + apr + may + jun + 1);
        }
        if (day > jan + feb + mar + apr + may + jun + jul && !leap(year)) {
            d = day - (jan + feb + mar + apr + may + jun + jul);
        }
        if (day > jan + feb + mar + apr + may + jun + jul + 1 && leap(year)) {
            d = day - (jan + feb + mar + apr + may + jun + jul + 1);
        }
        if (day > jan + feb + mar + apr + may + jun + jul + aug && !leap(year)) {
            d = day - (jan + feb + mar + apr + may + jun + jul + aug);
        }
        if (day > jan + feb + mar + apr + may + jun + jul + aug + 1 && leap(year)) {
            d = day - (jan + feb + mar + apr + may + jun + jul + aug + 1);
        }
        if (day > jan + feb + mar + apr + may + jun + jul + aug + sep && !leap(year)) {
            d = day - (jan + feb + mar + apr + may + jun + jul + aug + sep);
        }
        if (day > jan + feb + mar + apr + may + jun + jul + aug + sep + 1 && leap(year)) {
            d = day - (jan + feb + mar + apr + may + jun + jul + aug + sep + 1);
        }
        if (day > jan + feb + mar + apr + may + jun + jul + aug + sep + oct && !leap(year)) {
            d = day - (jan + feb + mar + apr + may + jun + jul + aug + sep + oct);
        }
        if (day > jan + feb + mar + apr + may + jun + jul + aug + sep + oct + 1 && leap(year)) {
            d = day - (jan + feb + mar + apr + may + jun + jul + aug + sep + oct + 1);
        }
        if (day > jan + feb + mar + apr + may + jun + jul + aug + sep + oct + nov && !leap(year)) {
            d = day - (jan + feb + mar + apr + may + jun + jul + aug + sep + oct + nov);
        }
        if (day > jan + feb + mar + apr + may + jun + jul + aug + sep + oct + nov + 1 && leap(year)) {
            d = day - (jan + feb + mar + apr + may + jun + jul + aug + sep + oct + nov + 1);
        }
        return d;
    }

    public static boolean leap(int year) {
        return year % 4 == 0;
    }

    public static String readLineWithoutExceptions() {
        String line = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        do {
            try {
                line = br.readLine();
                if (line.isEmpty()) {
                    System.out.println("Хотя бы один символ, пожалуйста!");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        while (line.isEmpty());
        return line;
    }

    public static int parseIntWithoutExceptions(String number) {
        int defaultValue = Integer.MAX_VALUE;
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
