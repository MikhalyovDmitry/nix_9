package ua.com.alevel;

public class MathSetDB {
    public Numbers[] numbers;
    public MathSet[] mathSets;
    int numbersSize = 10;
    int mathSetsSize = 10;
    int numbersIndex = 0;
    int mathSetsIndex = 0;

    public void createNumbers(String name, Number[] numbers) {
        if (numbersIndex >= numbersSize)
            numbersSize = numbersSize * 2;
        Numbers[] buffer = new Numbers[numbersSize];
        buffer[numbersIndex] = new Numbers();
        buffer[numbersIndex].setName(name);
        buffer[numbersIndex].setNumbers(numbers);
        if (this.numbers != null)
            System.arraycopy(this.numbers, 0, buffer, 0, numbersIndex);
        numbersIndex++;
        this.numbers = buffer;
    }

    public Numbers get(String name) {
        for (Numbers number : numbers)
            if (number != null && number.getName().equals(name))
                return number;
        return null;
    }

    public void createMathSet(String name) {
        if (mathSetsIndex >= mathSetsSize)
            mathSetsSize = mathSetsSize * 2;
        MathSet[] buffer = new MathSet[mathSetsSize];
        buffer[mathSetsIndex] = new MathSet();
        buffer[mathSetsIndex].setName(name);
        if (this.mathSets != null)
            System.arraycopy(this.mathSets, 0, buffer, 0, mathSetsIndex);
        mathSetsIndex++;
        this.mathSets = buffer;
    }

    public MathSet getSet(String name) {
        for (MathSet mathSet : mathSets)
            if (mathSet != null && mathSet.getName().equals(name))
                return mathSet;
        return null;
    }
}
