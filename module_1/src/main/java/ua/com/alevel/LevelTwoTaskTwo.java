package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Node {
    int value;
    Node left, right;

    Node(int x) {
        value = x;
        left = right = null;
    }

    public int getValue() {
        return value;
    }
}

class BinaryTree {
    Node root;

    int maxDepth(Node node) {
        if (node == null)
            return 0;
        else {
            int left = maxDepth(node.left);
            int right = maxDepth(node.right);
            return Math.max(left, right) + 1;
        }
    }
}

class LevelTwoTaskTwo {

    public static void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BinaryTree tree = makeRoot(reader);
        nodeMenu(reader, tree);
    }

    public static void nodeMenu(BufferedReader reader, BinaryTree tree) {
        String choice;
        Node node = tree.root;
        do {
            try {
                System.out.println("Нажмите 1. Вставить новый узел");
                System.out.println("Нажмите 2: Найти максимальную глубину");
                System.out.println("нажмите 0: Выход");
                choice = reader.readLine();

                switch (choice) {
                    case "1" -> {
                        do {
                            try {
                                System.out.print("Введите значение нового узла: ");
                                node = insert(node, parseIntWithoutExceptions(reader.readLine()));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } while (node.getValue() == 99999);
                    }
                    case "2" -> System.out.println("Максимальная глубина дерева: " + tree.maxDepth(tree.root));
                    case "0" -> {
                        return;
                    }
                    default -> System.out.println("Ошибка! Попробуйте выбрать из предложенных вариантов!");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        while (true);
    }

    public static BinaryTree makeRoot(BufferedReader reader) {
        BinaryTree tree = new BinaryTree();
        do {
            try {
                System.out.println("Введите значение корневого узла дерева: ");
                tree.root = new Node(parseIntWithoutExceptions(reader.readLine()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        while (tree.root.getValue() == 99999);
        return tree;
    }

    public static Node insert(Node node, int value) {
        Node result = null;
        if (value < node.getValue()) {
            if (node.left == null) {
                node.left = new Node(value);
                result = node;
                System.out.println("Создан новый узел " + node.left.getValue() + " слева от " + node.getValue());
            } else {
                node.left.left = new Node(value);
                System.out.println("Создан новый узел " + node.left.left.getValue() + " слева от " + node.left.getValue());
                result = node.left;
            }
        }
        if (value >= node.getValue()) {
            if (node.right == null) {
                node.right = new Node(value);
                result = node;
                System.out.println("Создан новый узел " + node.right.getValue() + " справа от " + node.getValue());
            } else {
                node.right.right = new Node(value);
                System.out.println("Создан новый узел " + node.right.right.getValue() + " справа от " + node.right.getValue());
                result = node.right;
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
}