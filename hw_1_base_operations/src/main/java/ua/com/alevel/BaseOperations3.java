package main.java.ua.com.alevel;

import java.util.Scanner;

public class BaseOperations3 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Input number of lesson from 1 to 10: ");
        int num = in.nextInt();
        lessonEnds(num);
    }

    public static void lessonEnds(int l) {
        int hours;
        int minutes;
        int time;
        time = 540 + l * 45 + l / 2 * 5 + (l - 1) / 2 * 15;
        hours = time / 60;
        minutes = time % 60;
        System.out.println(hours + " " + minutes);
    }

}

