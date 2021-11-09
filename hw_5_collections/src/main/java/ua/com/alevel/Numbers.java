package ua.com.alevel;

public class Numbers {
    private static final int DEFAULT_LENGTH = 0;
    private String name;
    private Number[] numbers;

    public Numbers() {
        this.numbers = new Number[DEFAULT_LENGTH];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Number[] getNumbers() {
        return numbers;
    }

    public void setNumbers(Number[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public String toString() {
        StringBuilder toString = new StringBuilder();
        toString.append("[ ");

        for (Number i : numbers) {
            if (i == null) {
                toString.append("null ");
            } else {
                toString.append(i).append(" ");
            }
        }
        toString.append("]");
        return toString.toString();
    }

}
