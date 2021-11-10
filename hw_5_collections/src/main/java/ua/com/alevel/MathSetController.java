package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MathSetController {

    private final static MathSetDB db = new MathSetDB();

    public static void numbersForExample() {
        db.createNumbers("1", new Number[]{1, 2, 3, 4});
        db.createNumbers("2", new Number[]{3, 4, 5, 6});
        db.createNumbers("3", new Number[]{5, 6, 7, 8});
        db.createNumbers("4", new Number[]{7, 8, 9000, 10000});
        db.createNumbers("5", new Number[]{1, 3, 5, 7});
        db.createNumbers("6", new Number[]{3, 5, 7, 9});
        db.createNumbers("7", new Number[]{0.5, 1, 1.5, 2});
        db.createNumbers("8", new Number[]{1.5, 2, 2.5, 3});
        db.createNumbers("9", new Number[]{-1.5, -1, -0.5, 0});
    }

    public static void mainMenu() {
        numbersForExample();
        do {
            line();
            System.out.println("  Главное меню");
            line();
            System.out.println("1 Создать MathSet");
            System.out.println("2 Изменить MathSet");
            System.out.println("3 Получить данные MathSet");
            System.out.println("4 Создать Number[]");
            System.out.println("0 Exit");
            line();
            switch (readLineWithoutExceptions()) {
                case "1" -> mathSetInput();
                case "2" -> updateMenu();
                case "3" -> outputMenu();
                case "4" -> numbersInput();
                case "0" -> System.exit(0);
                default -> System.out.println("  " + "Ошибка! \n  Выберите из предложенных вариантов");
            }
        }
        while (true);
    }

    public static void mathSetInput() {
        System.out.println("Input MathSet name");
        String name = readLineWithoutExceptions();
        if (!isMathSetNull(name)) {
            System.out.println("Невозможно создать, MathSet с таким именем уже существует!");
            return;
        }
        MathSet result = null;
        do {
            line();
            System.out.println("  Выберите способ инициализации MathSet:");
            line();
            System.out.println("1 MathSet()");
            System.out.println("2 MathSet(int capacity)");
            System.out.println("3 MathSet(Number[] numbers)");
            System.out.println("4 MathSet(Number[]... numbers)");
            System.out.println("5 MathSet(MathSet set)");
            System.out.println("6 MathSet(MathSet... set)");
            System.out.println("0 Назад");
            line();
            switch (readLineWithoutExceptions()) {
                case "1": {
                    result = emptyConstructor();
                    break;
                }
                case "2": {
                    result = capacityConstructor();
                    break;
                }
                case "3": {
                    result = numbersConstructor();
                    break;
                }
                case "4": {
                    result = varargsNumbersConstructor();
                    break;
                }
                case "5": {
                    result = mathSetConstructor();
                    if (result == null) {
                        System.out.println("MathSet не был создан");
                        return;
                    }
                    break;
                }
                case "6": {
                    result = varargsMathSetConstructor();
                    if (result == null) {
                        System.out.println("MathSet не был создан");
                        return;
                    }
                    break;
                }
                case "0":
                    return;
                default:
                    System.out.println("  Ошибка! \n  Выберите из предложенных вариантов");
            }
            if (result != null) {
                db.createMathSet(name);
                db.getSet(name).setArray(result.getArray());
                db.getSet(name).setCapacity(result.getCapacity());
                System.out.println("Created MathSet: \nname = " + name + "\n" + result);
                return;
            } else {
                System.out.println("MathSet не был создан");
            }
        }
        while (true);
    }

    public static void updateMenu() {
        String name = chooseMathSetNameFromList();
        do {
            line();
            System.out.println("  Изменить MathSet " + db.getSet(name));
            line();
            System.out.println("1 Add");
            System.out.println("2 Join ");
            System.out.println("3 Intersection");
            System.out.println("4 Sort");
            System.out.println("5 Cut");
            System.out.println("6 Clear");
            System.out.println("0 Назад");
            line();
            switch (readLineWithoutExceptions()) {
                case "1" -> addMenu(name);
                case "2" -> joinMenu(name);
                case "3" -> intersectionMenu(name);
                case "4" -> sortMenu(name);
                case "5" -> cutMenu(name);
                case "6" -> clearMenu(name);
                case "0" -> {
                    return;
                }
                default -> System.out.println("  " +
                        "Ошибка! \n  Выберите из предложенных вариантов");
            }
        }
        while (true);
    }

    public static void outputMenu() {
        String name = chooseMathSetNameFromList();
        do {
            line();
            System.out.println("  " + db.getSet(name) + ".");
            line();
            System.out.println("1 Number get(int index)");
            System.out.println("2 Number getMax()");
            System.out.println("3 Number getMin()");
            System.out.println("4 Number getAverage()");
            System.out.println("5 Number getMedian()");
            System.out.println("6 Number[] toArray()");
            System.out.println("7 Number[] toArray(int firstIndex, int LastIndex)");
            System.out.println("0 Назад");
            line();
            switch (readLineWithoutExceptions()) {
                case "1" -> {
                    System.out.println("Укажите index");
                    int index;
                    do {
                        index = inputInt();
                        if (index < 0 || index > db.getSet(name).size()) {
                            System.out.println("Index не должен быть больше размера MathSet!");
                        }
                    }
                    while (index > db.getSet(name).size());
                    System.out.println("get(" + index + ") " + db.getSet(name) +
                            " = " + db.getSet(name).get(index));
                }
                case "2" -> System.out.println("getMax " + db.getSet(name) +
                        " = " + db.getSet(name).getMax());
                case "3" -> System.out.println("getMin " + db.getSet(name) +
                        " = " + db.getSet(name).getMin());
                case "4" -> System.out.println("getAverage " + db.getSet(name) +
                        " = " + db.getSet(name).getAverage());
                case "5" -> System.out.println("getMedian " + db.getSet(name) +
                        " = " + db.getSet(name).getMedian());
                case "6" -> System.out.println("toArray " + db.getSet(name) +
                        " = " + Arrays.toString(db.getSet(name).getArray()));
                case "7" -> {
                    int[] index = inputFirstAndLastIndex(name);
                    System.out.println("toArray(" + index[0] + ", " + index[1] + ") " +
                            db.getSet(name) + " = " +
                            Arrays.toString(db.getSet(name).getArray(index[0], index[1])));
                }
                case "0" -> {
                    return;
                }
                default -> System.out.println("  " +
                        "Ошибка! \n  Выберите из предложенных вариантов");
            }
        }
        while (true);
    }

    public static String numbersInput() {
        System.out.println("Введите имя массива Number[]");
        String name = readLineWithoutExceptions();
        if (!isNumbersNull(name)) {
            System.out.println("Невозможно создать, Number[] с таким именем уже существует!");
            return null;
        }
        boolean numberOk;
        Number[] numbers;
        do {
            System.out.println("Введите массив Number[] через пробел: ");
            numberOk = true;
            int i = 0;
            String[] strArr = readLineWithoutExceptions().split(" ");
            numbers = new Number[strArr.length];
            for (String str : strArr) {
                if (isNotNumber(str)) {
                    System.out.println("Только числа, пожалуйста!");
                    numberOk = false;
                    continue;
                }
                numbers[i] = stringToNumber(str);
                i++;
            }
        }
        while (!numberOk);
        db.createNumbers(name, numbers);
        System.out.println("Created Number[] \nname = " + name + "\n" + db.get(name));
        return name;
    }

    public static MathSet emptyConstructor() {
        return new MathSet();
    }

    public static MathSet capacityConstructor() {
        System.out.println("Введите длину MathSet");
        int capacity = inputInt();
        return new MathSet(capacity);
    }

    public static MathSet numbersConstructor() {
        String inputNumbers = numbersChoice();
        if (isNumbersNull(inputNumbers)) {
            return null;
        }
        return new MathSet(db.get(inputNumbers).getNumbers());
    }

    public static MathSet varargsNumbersConstructor() {
        if (db.numbers != null) {
            for (Numbers i : db.numbers) {
                if (i != null) {
                    System.out.println("name = " + i.getName() + " " + i);
                }
            }
        } else {
            System.out.println("Number[] не найдены!");
        }
        return new MathSet(stringToNumbersArray());
    }

    public static MathSet mathSetConstructor() {
        String name = chooseMathSetNameFromList();
        if (name == null) {
            return null;
        } else {
            return new MathSet(db.getSet(name));
        }
    }

    public static MathSet varargsMathSetConstructor() {
        outMathSetList();
        MathSet[] sets = stringToMathSetArray();
        return new MathSet(sets);
    }

    public static void addMenu(String name) {
        do {
            line();
            System.out.println("  " + db.getSet(name) + ".");
            line();
            System.out.println("1 add(Number n)");
            System.out.println("2 add(Number... n)");
            System.out.println("0 Назад");
            line();
            switch (readLineWithoutExceptions()) {
                case "1" -> {
                    System.out.println("Укажите Number");
                    db.getSet(name).add(inputNumber());
                    System.out.println("Updated MathSet: \nname = " + name + "\n" + db.getSet(name));
                }
                case "2" -> {
                    db.getSet(name).add(stringToNumberArray());
                    System.out.println("Updated MathSet: \nname = " + name + "\n" + db.getSet(name));
                }
                case "0" -> {
                    return;
                }
                default -> System.out.println("  " +
                        "Ошибка! \n  Выберите из предложенных вариантов");
            }
        }
        while (true);
    }

    public static void joinMenu(String name) {
        do {
            line();
            System.out.println("  " + db.getSet(name) + ".");
            line();
            System.out.println("1 join(MathSet mathSet)");
            System.out.println("2 join(MathSet... mathSets)");
            System.out.println("0 Назад");
            line();
            String nameToJoin;
            switch (readLineWithoutExceptions()) {
                case "1" -> {
                    outMathSetList();
                    System.out.println("Теперь выберите имя MathSet для join");
                    nameToJoin = inputMathSetName();
                    db.getSet(name).join(db.getSet(nameToJoin));
                    System.out.println("Updated MathSet: \nname = " + name + "\n" + db.getSet(name));
                }
                case "2" -> {
                    outMathSetList();
                    System.out.println("Теперь выберите несколько MathSet для join");
                    MathSet[] mathSets = stringToMathSetArray();
                    db.getSet(name).join(mathSets);
                    System.out.println("Updated MathSet: \nname = " + name + "\n" + db.getSet(name));
                }
                case "0" -> {
                    return;
                }
                default -> System.out.println("  " +
                        "Ошибка! \n  Выберите из предложенных вариантов");
            }
        }
        while (true);
    }

    public static void intersectionMenu(String name) {
        do {
            line();
            System.out.println("  " + db.getSet(name) + ".");
            line();
            System.out.println("1 intersection(MathSet mathSet)");
            System.out.println("2 intersection(MathSet... mathSets)");
            System.out.println("0 Назад");
            line();
            switch (readLineWithoutExceptions()) {
                case "1" -> {
                    outMathSetList();
                    System.out.println("Теперь выберите имя MathSet для intersection");
                    String nameToJoin = inputMathSetName();
                    db.getSet(name).intersection(db.getSet(nameToJoin));
                    System.out.println("Updated MathSet: \nname = " + name + "\n" + db.getSet(name));
                }
                case "2" -> {
                    outMathSetList();
                    System.out.println("Теперь выберите несколько MathSet для intersection");
                    MathSet[] mathSets = stringToMathSetArray();
                    db.getSet(name).intersection(mathSets);
                    System.out.println("Updated MathSet: \nname = " + name + "\n" + db.getSet(name));
                }
                case "0" -> {
                    return;
                }
                default -> System.out.println("  " +
                        "Ошибка! \n  Выберите из предложенных вариантов");
            }
        }
        while (true);
    }

    public static void sortMenu(String name) {
        Number value;
        do {
            line();
            System.out.println("  " + db.getSet(name) + ".");
            line();
            System.out.println("1 sortDesc()");
            System.out.println("2 sortDesc(int firstIndex, int lastIndex)");
            System.out.println("3 sortDesc(Number value)");
            System.out.println("4 sortAsc()");
            System.out.println("5 sortAsc(int firstIndex, int lastIndex)");
            System.out.println("6 sortAsc(Number value)");
            System.out.println("0 Назад");
            line();
            switch (readLineWithoutExceptions()) {
                case "1" -> {
                    db.getSet(name).sortDesc();
                    System.out.println("Updated MathSet: \nname = " + name + "\n" + db.getSet(name));
                }
                case "2" -> {
                    int[] index = inputFirstAndLastIndex(name);
                    db.getSet(name).sortDesc(index[0], index[1]);
                    System.out.println("Updated MathSet: \nname = " + name + "\n" + db.getSet(name));
                }
                case "3" -> {
                    System.out.println("Укажите Value");
                    value = inputNumber();
                    db.getSet(name).sortDesc(value);
                    System.out.println("Updated MathSet: \nname = " + name + "\n" + db.getSet(name));
                }
                case "4" -> {
                    db.getSet(name).sortAsc();
                    System.out.println("Updated MathSet: \nname = " + name + "\n" + db.getSet(name));
                }
                case "5" -> {
                    int[] index = inputFirstAndLastIndex(name);
                    db.getSet(name).sortAsc(index[0], index[1]);
                    System.out.println("Updated MathSet: \nname = " + name + "\n" + db.getSet(name));
                }
                case "6" -> {
                    System.out.println("Укажите Value");
                    value = inputNumber();
                    db.getSet(name).sortAsc(value);
                    System.out.println("Updated MathSet: \nname = " + name + "\n" + db.getSet(name));
                }
                case "0" -> {
                    return;
                }
                default -> System.out.println("  " +
                        "Ошибка! \n  Выберите из предложенных вариантов");
            }
        }
        while (true);
    }

    public static void cutMenu(String name) {
        int[] index = inputFirstAndLastIndex(name);
        db.getSet(name).cut(index[0], index[1]);
        System.out.println("Updated MathSet: \nname = " + name + "\n" + db.getSet(name));
    }

    public static void clearMenu(String name) {
        String numbersName;
        do {
            line();
            System.out.println("  " + db.getSet(name) + ".");
            line();
            System.out.println("1 clear()");
            System.out.println("2 clear(Number[] numbers)");
            System.out.println("0 Назад");
            line();
            switch (readLineWithoutExceptions()) {
                case "1" -> {
                    db.getSet(name).clear();
                    System.out.println("Updated MathSet: \nname = " + name + "\n" + db.getSet(name));
                }
                case "2" -> {
                    numbersName = chooseNumbersNameFromList();
                    db.getSet(name).clear(db.get(numbersName).getNumbers());
                    System.out.println("Updated MathSet: \nname = " + name + "\n" + db.getSet(name));
                }
                case "0" -> {
                    return;
                }
                default -> System.out.println("  " +
                        "Ошибка! \n  Выберите из предложенных вариантов");
            }
        }
        while (true);
    }

    public static String numbersChoice() {
        line();
        System.out.println("  Выберите, какой Number[] использовать");
        line();
        System.out.println("1 Ввести существующий Number[]");
        System.out.println("2 Создать новый Number[]");
        System.out.println("0 Назад");
        String choice;
        do {
            choice = readLineWithoutExceptions();
            switch (choice) {
                case "1" -> {
                    return chooseNumbersNameFromList();
                }
                case "2" -> {
                    return numbersInput();
                }
                case "0" -> {
                    System.out.println("Не был введен Number[]");
                    return null;
                }
                default -> System.out.println("Ошибка! Выберите из предложенных вариантов!");
            }
        }
        while (choice.equals("1") || choice.equals("2"));
        return null;
    }

    public static String chooseNumbersNameFromList() {
        if (db.numbers != null) {
            for (Numbers i : db.numbers) {
                if (i != null) {
                    System.out.println("name = " + i.getName() + " " + i);
                }
            }
        } else {
            System.out.println("Number[] не найдены!");
        }
        System.out.println("Укажите имя Number[] из указанных выше");
        return inputNumbersName();
    }

    public static String chooseMathSetNameFromList() {
        outMathSetList();
        System.out.println("Выберите имя MathSet из указанных выше");
        return inputMathSetName();
    }

    public static void outMathSetList() {
        if (db.mathSets != null) {
            for (MathSet i : db.mathSets) {
                if (i != null) {
                    System.out.println(i.getName() + " " + i);
                }
            }
        } else {
            System.out.println("MathSet не найдены!");
            System.out.println("Создайте хотя бы один MathSet");
            mathSetInput();
        }
    }

    public static int[] inputFirstAndLastIndex(String mathSetName) {
        int firstIndex;
        int lastIndex;
        do {
            System.out.println("Выберите firstIndex");
            do {
                firstIndex = inputInt();
                if (firstIndex < 0 || firstIndex > db.getSet(mathSetName).size() - 1) {
                    System.out.println("firstIndex должен быть в рамках длины MathSet");
                }
            }
            while (firstIndex < 0 || firstIndex > db.getSet(mathSetName).size() - 1);
            System.out.println("Выберите lastIndex");
            do {
                lastIndex = inputInt();
                if (lastIndex < 0 || lastIndex > db.getSet(mathSetName).size() - 1) {
                    System.out.println("lastIndex должен быть в рамках длины MathSet");
                }
            }
            while (lastIndex < 0 || lastIndex > db.getSet(mathSetName).size() - 1);
            if (firstIndex > lastIndex) {
                System.out.println("firstIndex не может быть больше lastIndex");
            }
        }
        while (firstIndex > lastIndex);
        return new int[]{firstIndex, lastIndex};
    }

    public static MathSet[] stringToMathSetArray() {
        MathSet[] sets;
        do {
            System.out.println("Введите несколько MathSet через пробел");
            String[] mathSetNames = readLineWithoutExceptions().split(" ");
            sets = new MathSet[mathSetNames.length];
            int index = 0;
            for (String mathSet : mathSetNames) {
                if (isMathSetNull(mathSet)) {
                    System.out.println("Ошибка! Несуществующие имена MathSet");
                    sets = null;
                    continue;
                }
                sets[index] = db.getSet(mathSet);
                index++;
            }
        }
        while (sets == null);
        return sets;
    }

    public static Numbers[] stringToNumbersArray() {
        Numbers[] numbersArray;
        String[] inputStringArray;
        boolean wrongInput;
        do {
            System.out.println("Введите несколько имен Number[] через пробел");
            wrongInput = false;
            inputStringArray = readLineWithoutExceptions().split(" ");
            numbersArray = new Numbers[inputStringArray.length];
            int index = 0;
            for (String numberArray : inputStringArray) {
                if (isNumbersNull(numberArray)) {
                    wrongInput = true;
                    System.out.println("Ошибка! Несуществующие имена Number[]");
                    continue;
                }
                numbersArray[index] = db.get(numberArray);
                index++;
            }
        }
        while (wrongInput || numbersArray == null);
        return numbersArray;
    }

    public static boolean isMathSetNull(String name) {
        if (db.mathSets == null) return true;
        return db.getSet(name) == null;
    }

    public static boolean isNumbersNull(String name) {
        if (db.numbers == null) return true;
        return db.get(name) == null;
    }

    public static Number[] stringToNumberArray() {
        System.out.println("Введите несколько Number через пробел");
        Number[] numbers;
        String[] inputStringArray;
        boolean wrongInput;
        do {
            wrongInput = false;
            inputStringArray = readLineWithoutExceptions().split(" ");
            numbers = new Number[inputStringArray.length];
            int index = 0;
            for (String number : inputStringArray) {
                if (isNotNumber(number)) {
                    wrongInput = true;
                    continue;
                }
                numbers[index] = stringToNumber(number);
                index++;
            }
        }
        while (wrongInput || numbers == null);
        return numbers;
    }

    public static String inputMathSetName() {
        String name;
        do {
            name = readLineWithoutExceptions();
            if (isMathSetNull(name))
                System.out.println("Не найден MathSet с указанным именем");
        }
        while (isMathSetNull(name));
        return name;
    }

    public static String inputNumbersName() {
        String name;
        do {
            name = readLineWithoutExceptions();
            if (isNumbersNull(name))
                System.out.println("Не найден Number[] с указанным именем");
        }
        while (isNumbersNull(name));
        return name;
    }

    // Всё что ниже, можно вынести в библиотеку

    public static Number inputNumber() {
        Number number;
        do {
            number = stringToNumber(readLineWithoutExceptions());
        }
        while (number == null);
        return number;
    }

    public static int inputInt() {
        int result;
        do {
            result = parseIntWithoutExceptions(readLineWithoutExceptions());
            if (result != Integer.MAX_VALUE) {
                return result;
            } else {
                System.out.println("Ошибка! Только целые числа, пожалуйста!");
            }
        } while (result == Integer.MAX_VALUE);
        return 0;
    }

    public static Integer redundantZeros(String str) {
        int pointIndex = str.indexOf(".");
        boolean redundantZeros = true;
        for (int i = pointIndex + 1; i < str.length(); i++) {
            if (str.charAt(i) != '0') {
                redundantZeros = false;
                break;
            }
        }
        if (redundantZeros) {
            return Integer.parseInt(str.substring(0, pointIndex));
        }
        return null;
    }

    public static int parseIntWithoutExceptions(String number) {
        int defaultValue = Integer.MAX_VALUE;
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
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

    public static boolean isNotNumber(String input) {
        char[] array = input.toCharArray();
        String allowed = "0123456789-.";
        boolean result = false;
        for (char c : array) {
            if (allowed.indexOf(c) == -1) {
                System.out.println("Ошибка! Содержатся недопустимые символы (" + input + ")");
                result = true;
                break;
            }
        }
        if (input.isEmpty()) {
            System.out.println("Ошибка! Два пробела подряд");
            result = true;
        }
        if (input.indexOf(".") == 0) {
            System.out.println("Ошибка! Неверная запись десятичного числа (" + input + ")");
            result = true;
        }
        if (input.indexOf("-") != 0 && input.contains("-")) {
            System.out.println("Ошибка! Неверная запись отрицательного числа (" + input + ")");
            result = true;
        }
        return result;
    }

    public static boolean isDecimal(String input) {
        char[] array = input.toCharArray();
        for (char c : array) {
            if (c == '.') return true;
        }
        return false;
    }

    public static Number stringToNumber(String str) {
        Number number;
        if (isNotNumber(str)) {
            System.out.println("Ошибка! Только числа, пожалуйста");
            return null;
        }
        if (isDecimal(str)) {
            number = redundantZeros(str);
            if (number == null)
                number = Double.parseDouble(str);
        } else {
            number = Integer.parseInt(str);
        }
        return number;
    }

    public static void line() {
        System.out.println("========================================");
    }
}
