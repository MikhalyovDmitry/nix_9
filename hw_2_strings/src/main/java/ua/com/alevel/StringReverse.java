package ua.com.alevel;

public class StringReverse {

    public static String simple (String src) {
        String result = "";
        for (int i = src.length() - 1; i >= 0; i--) {
            result = result + src.charAt(i);
        }
        return result;
    }

    public static String byWords (String src) {
        String result = "";
        int beginIndex = 0;
        for (int i = 0; i < src.length(); i++) {
            if (src.charAt(i) == ' ') {
                result += simple(src.substring(beginIndex, i)) + " ";
                beginIndex = i + 1;
            }
            if (i == src.length() - 1) {
                result += simple(src.substring(beginIndex, i + 1));
            }
        }
        return result;
    }

    public static String bySubstring (String src, String dest) {
        String result;
        int index = src.indexOf(dest);
        if (index == -1) {
            return "Введенная подстрока не является частью строки!";
        }
        result = src.substring(0, index) + simple(dest) + src.substring(index + dest.length());
        return result;
    }

    public static String byIndexes(String src, int firstIndex, int lastIndex) {
        if (firstIndex == 99999 || lastIndex == 99999) {
            return "Индекс подстроки введен неверно! Введите число, пожалуйста.";
        }
        if (firstIndex > src.length() || lastIndex > src.length()) {
            return "Ошибка! Индексы подстроки не должны выходить за рамки исходной строки!!";
        }
        if (firstIndex > lastIndex) {
            return "Ошибка! Начальный индекс не должен быть больше конечного!";
        }
        String result = src.substring(0, firstIndex) + simple(src.substring(firstIndex, lastIndex)) + src.substring(lastIndex);
        return result;
    }
}
