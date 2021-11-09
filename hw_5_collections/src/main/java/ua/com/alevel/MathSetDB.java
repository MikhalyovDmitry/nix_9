package ua.com.alevel;

import java.util.Arrays;

public class MathSetDB {

    public Numbers[] numbers;
    public MathSet[] mathSets;
    int numbersSize = 0;
    int mathSetsSize = 0;

    public void createNumbers(String name, Number[] numbers) {
        Numbers[] buffer = new Numbers[numbersSize + 1];
        buffer[numbersSize] = new Numbers();
        buffer[numbersSize].setName(name);
        buffer[numbersSize].setNumbers(numbers);
        if (numbersSize > 0)
            System.arraycopy(this.numbers, 0, buffer, 0, numbersSize);
        numbersSize++;
        this.numbers = buffer;
    }

    public Numbers get(String name) {
        return Arrays.stream(numbers).filter(number -> number.getName().equals(name)).findFirst().orElse(null);
    }

    public void createMathSet(String name) {
        MathSet[] buffer = new MathSet[mathSetsSize + 1];
        buffer[mathSetsSize] = new MathSet();
        buffer[mathSetsSize].setName(name);
        if (mathSetsSize > 0)
            System.arraycopy(this.mathSets, 0, buffer, 0, mathSetsSize);
        mathSetsSize++;
        this.mathSets = buffer;
    }

    public MathSet getSet(String name) {
        return Arrays.stream(mathSets).filter(set -> set.getName().equals(name)).findFirst().orElse(null);
    }

}
