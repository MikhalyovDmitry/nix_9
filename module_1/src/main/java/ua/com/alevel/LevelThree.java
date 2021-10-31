package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class LevelThree {

    public static void run() {
        menu();
    }

    public static void menu() {
        int m;
        int n;
        System.out.println("Введите размеры m x n для игры Жизнь: ");
        do {
            System.out.println("m:");
            m = parseIntWithoutExceptions(readLineWithoutExceptions());
            if (m > 9 || m < 1) System.out.println("Целое число от 1 до 9, пожалуйста");
        }
        while (m > 9 || m < 1);
        do {
            System.out.println("n:");
            n = parseIntWithoutExceptions(readLineWithoutExceptions());
            if (n > 9 || n < 1) System.out.println("Целое число от 1 до 9, пожалуйста");
        }
        while (n > 9 || n < 1);

        String choice;
        int aliveCell;
        ArrayList<Integer> aliveCells = new ArrayList<>();
        do {
            System.out.println("1 Добавить живую клетку");
            System.out.println("0 Показать текущее поколение клеток");
            choice = readLineWithoutExceptions();
            switch (choice) {
                case "1" -> {
                    aliveCell = inputCell(m, n);
                    aliveCells.add(aliveCell);
                }
                case "0" -> {
                    draw(aliveCells, m, n);
                    String nextOrQuit;
                    do {
                        System.out.println("1 Следующее поколение");
                        System.out.println("0 Выход");
                        nextOrQuit = readLineWithoutExceptions();
                        switch (nextOrQuit) {
                            case "1": {
                                aliveCells = nextGeneration(aliveCells, m, n);
                                draw(aliveCells, m, n);
                                break;
                            }
                            case "0":
                                return;
                            default:
                                System.out.println("Выберите из списка, пожалуйста");
                        }
                    }
                    while (true);
                }
                default -> System.out.println("Выберите из списка, пожалуйста");
            }
        }
        while(true);
    }

    public static int inputCell(int m, int n) {
        int resultM;
        int resultN;
        do {
            System.out.println("Введите m:");
            resultM = parseIntWithoutExceptions(readLineWithoutExceptions());
            if (resultM < 1 || resultM > m) System.out.println("От 1 до " + m +", пожалуйста");
        }
        while (resultM < 1 || resultM > m);
        do {
            System.out.println("Введите n:");
            resultN = parseIntWithoutExceptions(readLineWithoutExceptions());
            if (resultN < 1 || resultN > n) System.out.println("От 1 до " + n +", пожалуйста");
        }
        while (resultN < 1 || resultN > n);
        return (resultN - 1)*m + resultM;
    }

    public static void draw(ArrayList<Integer> cells, int m, int n) {
        String[] cellToString = new String[m * n + 1];
        for (int i = 1; i < cellToString.length; i++) {
            cellToString[i] = "░░";
        }
        for (Integer cell : cells) {
            cellToString[cell] = "▓▓";
        }
        System.out.print("  ");
        for (int i = 1; i <= m; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 1; i <= n ; i++) {
            System.out.print(i);
            for (int j = 1 + (i - 1) * m; j <= m + (i - 1) * m; j++) {
                System.out.print(cellToString[j]);
            }
            System.out.println();
        }
    }

    public static ArrayList<Integer> neighborCells(int cell, int m, int n) {
        ArrayList<Integer> cells = new ArrayList<>();
        if (cell % m != 0) {                               //right neighbour
            cells.add(cell + 1);
        }
        if (cell % m != 1) {                               //left neighbour
            cells.add(cell - 1);
        }
        if (cell > m) {                                    //up neighbour
            cells.add(cell - m);
        }
        if (cell <=  m * (n - 1)) {                        //down neighbour
            cells.add(cell + m);
        }
        if (cell % m != 0 && cell > m) {                   //right up neighbour
            cells.add(cell - (m - 1));
        }
        if (cell % m != 0 && cell <=  m * (n - 1)) {       //right down neighbour
            cells.add(cell + (m + 1));
        }
        if (cell % m != 1 && cell > m) {                   //left up neighbour
            cells.add(cell - (m + 1));
        }
        if (cell % m != 1 && cell <=  m * (n - 1)) {       //left down neighbour
            cells.add(cell + (m - 1));
        }
        return cells;
    }

    public static ArrayList<Integer> nextGeneration(ArrayList<Integer> cells, int m, int n) {
        ArrayList<Integer> result = new ArrayList<>();
        ArrayList<Integer> neighbours;
        int aliveNeighbours = 0;
        for (int i = 1; i < m * n + 1; i++) {
            neighbours = neighborCells(i, m, n);
            for (Integer neighbour : neighbours) {
                if (isAlive(cells, neighbour)) {
                    aliveNeighbours++;
                    //System.out.println(neighbours.get(j) + " is an alive neighbour of " + i);
                }
            }
            if (aliveNeighbours == 2 && isAlive(cells, i) || aliveNeighbours == 3) {
                result.add(i);
                //System.out.println(i + "lives because has " + aliveNeighbours + " neighbours");
            }
            aliveNeighbours = 0;
        }
        return result;
    }

    public static boolean isAlive(ArrayList<Integer> aliveCells, int cell) {
        boolean result = false;
        for (Integer aliveCell : aliveCells) {
            if (cell == aliveCell) {
                result = true;
                break;
            }
        }
        return result;
    }

    public static int parseIntWithoutExceptions(String number) {
        int defaultValue = 99999;
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static String readLineWithoutExceptions() {
        String line = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            line = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }
}
