package ua.com.alevel;

public class MathSet {
    private static final int DEFAULT_LENGTH = 0;
    private String name;
    private Number[] array;

    public MathSet() {
        this.array = new Number[DEFAULT_LENGTH];
    }

    public MathSet(int capacity) {
        this.array = new Number[capacity];
    }

    public MathSet(Number[] numbers) {
        MathSet mathSet = new MathSet();
        for (Number number : numbers) {
            mathSet.add(number);
        }
        this.array = mathSet.getArray();
    }

    public MathSet(Numbers[] numbers) {
        MathSet mathSet = new MathSet();
        for (Numbers number : numbers) {
            mathSet.add(number.getNumbers());
        }
        this.array = mathSet.getArray();
    }

    public MathSet(MathSet set) {
        MathSet mathSet = new MathSet();
        for (Number number : set.getArray()) {
            mathSet.add(number);
        }
        this.array = mathSet.getArray();
    }

    public MathSet(MathSet... sets) {
        MathSet mathSet = new MathSet();
        for (MathSet set : sets) {
            mathSet.add(set.getArray());
        }
        this.array = mathSet.getArray();
    }

    public int compare(Number number1, Number number2) {
        if (((Object) number2).getClass().equals(((Object) number1).getClass())) {
            if (number1 instanceof Comparable) {
                return ((Comparable) number1).compareTo(number2);
            }
        }
        return Double.compare(number1.doubleValue(), number2.doubleValue());
    }

    public Number[] getArray() {
        return array;
    }

    public void setArray(Number[] array) {
        this.array = array;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    void add(Number n) {
        MathSet mathSet = new MathSet(this.array);
        if (!mathSet.contains(n)) {
            int resultLength = this.array.length + 1;
            Number[] result = new Number[resultLength];
            System.arraycopy(this.array, 0, result, 0, resultLength - 1);
            result[resultLength - 1] = n;
            this.array = result;
            //arrayToArrayCapacityCheck(result);
        }
    }

    void add(Number[] numbers) {
        MathSet result = new MathSet(this.array);
        for (Number i : numbers) {
            result.add(i);
        }
        this.array = result.getArray();
    }

    void join(MathSet set) {
        MathSet mathSet = new MathSet(this.array);
        mathSet.add(set.getArray());
        this.array = mathSet.getArray();
    }

    void join(MathSet[] sets) {
        MathSet mathSet = new MathSet(this.array);
        for (MathSet set : sets) {
            mathSet.join(set);
        }
        this.array = mathSet.getArray();
    }

    void intersection(MathSet set) {
        MathSet mathSet = new MathSet(this.array);
        Number[] result = new Number[set.size()];
        int index = 0;
        for (int i = 0; i < mathSet.size(); i++) {
            if (set.contains(mathSet.get(i))) {
                result[index] = mathSet.get(i);
                index++;
            }
        }
        this.array = new Number[index];
        System.arraycopy(result, 0, this.array, 0, index);
    }

    void intersection(MathSet[] sets) {
        MathSet resultSet = new MathSet(this.array);
        for (MathSet set : sets) {
            resultSet.intersection(set);
        }
        this.array = resultSet.getArray();
    }


    private void swap(Number[] numbers, int indexOne, int indexTwo) {
        Number buffer = numbers[indexOne];
        numbers[indexOne] = numbers[indexTwo];
        numbers[indexTwo] = buffer;
    }

    void sortAsc() {
        MathSet resultSet = new MathSet(this.array);
        boolean needIteration = true;
        while (needIteration) {
            needIteration = false;
            for (int i = 1; i < resultSet.size(); i++) {
                if (compare(resultSet.get(i), resultSet.get(i - 1)) == -1) {
                    swap(resultSet.array, i, i - 1);
                    needIteration = true;
                }
            }
        }
        this.array = resultSet.getArray();
    }

    void sortDesc() {
        MathSet resultSet = new MathSet(this.array);
        boolean needIteration = true;
        while (needIteration) {
            needIteration = false;
            for (int i = 1; i < resultSet.size(); i++) {
                if (compare(resultSet.get(i), resultSet.get(i - 1)) == 1) {
                    swap(resultSet.array, i, i - 1);
                    needIteration = true;
                }
            }
        }
        this.array = resultSet.getArray();
    }

    void sortDesc(int firstIndex, int lastIndex) {
        MathSet result = new MathSet(this.array);
        result.cut(0, firstIndex - 1);
        MathSet right = new MathSet(this.array);
        right.cut(lastIndex, this.array.length - 1);
        MathSet toSort = new MathSet(this.array);
        toSort.cut(firstIndex, lastIndex);
        toSort.sortDesc();
        result.join(toSort);
        result.join(right);
        this.array = result.getArray();
    }

    void sortAsc(int firstIndex, int lastIndex) {
        MathSet result = new MathSet(this.array);
        result.cut(0, firstIndex - 1);
        MathSet right = new MathSet(this.array);
        right.cut(lastIndex, this.array.length - 1);
        MathSet toSort = new MathSet(this.array);
        toSort.cut(firstIndex, lastIndex);
        toSort.sortAsc();
        result.join(toSort);
        result.join(right);
        this.array = result.getArray();
    }

    void sortAsc(Number value) {
        MathSet mathSet = new MathSet(this.array);
        int index = mathSet.indexOf(value);
        if (index == -1) {
            System.out.println("Элемент со значением " + value + " не найден!");
        } else {
            MathSet result = new MathSet(this.array);
            result.cut(0, index - 1);
            MathSet toSort = new MathSet(this.array);
            toSort.cut(index, this.array.length - 1);
            toSort.sortAsc();
            result.join(toSort);
            this.array = result.getArray();
        }
    }

    void sortDesc(Number value) {
        MathSet mathSet = new MathSet(this.array);
        int index = mathSet.indexOf(value);
        if (index == -1) {
            System.out.println("Элемент со значением " + value + " не найден!");
        } else {
            MathSet result = new MathSet(this.array);
            result.cut(0, index - 1);
            MathSet toSort = new MathSet(this.array);
            toSort.cut(index, this.array.length - 1);
            toSort.sortDesc();
            result.join(toSort);
            this.array = result.getArray();
        }
    }

    void clear() {
        this.array = new MathSet().getArray();
    }

    void clear(Number[] numbers) {
        MathSet mathSet = new MathSet(this.array);
        for (Number number : numbers) {
            if (mathSet.contains(number)) {
                mathSet.clear(number);
            }
        }
        this.array = mathSet.getArray();
    }

    void clear(Number number) {
        MathSet mathSet = new MathSet(this.array);
        MathSet left = new MathSet(this.array);
        MathSet right = new MathSet(this.array);
        if (mathSet.contains(number)) {
            left.cut(0, indexOf(number) - 1);
            right.cut(indexOf(number) + 1, this.array.length - 1);
            left.join(right);
            this.array = left.getArray();
        }
    }

    int indexOf(Number value) {
        for (int i = 0; i < this.array.length; i++) {
            if (value.equals(this.array[i])) {
                return i;
            }
        }
        return -1;
    }

    void cut(int firstIndex, int lastIndex) {
        MathSet mathSet = new MathSet(this.array);
        MathSet cutMathSet = new MathSet();
        for (int i = firstIndex; i < lastIndex + 1; i++) {
            cutMathSet.add(mathSet.get(i));
        }
        this.array = cutMathSet.getArray();
    }

    boolean contains(Number number) {
        for (Number value : this.array) {
            if (value.equals(number)) return true;
        }
        return false;
    }

    public Number get(int index) {
        return this.array[index];
    }

    public Number getMax() {
        Number max = this.array[0];
        for (int i = 1; i < this.array.length; i++) {
            if (compare(this.array[i], max) == 1) {
                max = this.array[i];
            }
        }
        return max;
    }

    public Number getMin() {
        Number min = this.array[0];
        for (int i = 1; i < this.array.length; i++) {
            if (compare(this.array[i], min) == -1) {
                min = this.array[i];
            }
        }
        return min;
    }

    public Number getAverage() {
        double average = 0;
        for (Number number : this.array) {
            average += Double.parseDouble(number.toString());
        }
        return average / this.array.length;
    }

    public Number getMedian() {
        Number median;
        MathSet set = new MathSet(this.array);
        set.sortAsc();
        if (this.array.length % 2 != 0) {
            median = Double.parseDouble(this.array[this.array.length / 2].toString());
        } else {
            median = (Double.parseDouble(this.array[this.array.length / 2].toString()) +
                    Double.parseDouble(this.array[this.array.length / 2 - 1].toString())) / 2;
        }
        return median;
    }

    public Number[] getArray(int firstIndex, int lastIndex) {
        MathSet mathSet = new MathSet(this.array);
        MathSet resultMathSet = new MathSet();
        for (int i = firstIndex; i < lastIndex + 1; i++) {
            resultMathSet.add(mathSet.get(i));
        }
        return resultMathSet.getArray();
    }

    public int size() {
        return this.array.length;
    }

    @Override
    public String toString() {
        StringBuilder toString = new StringBuilder();
        toString.append("< ");

        for (Number i : array) {
            if (i == null) {
                toString.append("null ");
            } else {
                toString.append(i).append(" ");
            }
        }
        toString.append(">");
        return toString.toString();
    }
}

