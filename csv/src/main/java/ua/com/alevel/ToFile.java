package ua.com.alevel;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ToFile {

    public static void create(Object object) {
        String fileName = object.getClass().getSimpleName().toLowerCase() + ".csv";

        Field[] fields = object.getClass().getDeclaredFields();
        String[] fieldNames = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            fieldNames[i] = fields[i].getName();
        }

        StringBuilder objectValues = new StringBuilder();
        Class<?> x = object.getClass();
        Field field = null;
        for (int j = 0; j < fieldNames.length; j++) {
            String fieldName = fieldNames[j];
            try {
                field = x.getField(fieldName);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
            try {
                Object fieldValue = field.get(object);
                if (fieldValue != null) {
                    objectValues.append(fieldValue);
                }
                if (j == fieldNames.length - 1) {
                    objectValues.append("\n");
                } else {
                    objectValues.append(",");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        try {
            FileWriter fileWriter = new FileWriter(fileName, true);
            fileWriter.write(objectValues.toString());
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> void delete(Class<T> clazz, String id) throws InstantiationException, IllegalAccessException {
        String fileName = clazz.getSimpleName().toLowerCase() + ".csv";
        List<String> input = readInputTxt(fileName);
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < input.size(); i++) {
            if (input.get(i).contains(id) ) {
                input.remove(i);
                i--;
            } else {
                output.append(input.get(i));
                output.append("\n");
            }
        }
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write(output.toString());
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void delete(String bookId, String authorId) {
        List<String> input = readInputTxt("bookauthor.csv");
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < input.size(); i++) {
            if (input.get(i).contains(bookId) && input.get(i).contains(authorId)) {
                input.remove(i);
                i--;
            } else {
                output.append(input.get(i));
                output.append("\n");
            }
        }
        try {
            FileWriter fileWriter = new FileWriter("bookauthor.csv");
            fileWriter.write(output.toString());
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void update(Object object) throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        String id = object.getClass().getField("id").get(object).toString();
        delete(object.getClass(), id);
        create(object);
    }

    public static List<String> readInputTxt(String fileName) {
        List<String> input = new ArrayList<>();
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            if (e != null) System.out.println("File not found");
            return null;
        }
        try {
            while (bufferedReader.ready()) {
                input.add(bufferedReader.readLine());
            }
        } catch (IOException e) {
            if (e != null) System.out.println(e);
        }
        return input;
    }
}
