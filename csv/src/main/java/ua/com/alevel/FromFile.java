package ua.com.alevel;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class FromFile {

    public static <T> T getObject(Class<T> clazz, String id) throws InstantiationException, IllegalAccessException, NoSuchFieldException {
        String fileName = clazz.getSimpleName().toLowerCase() + ".csv";
        List<String> input = readInputTxt(fileName);
        if (input.isEmpty()) return null;

        Field[] fields = clazz.getDeclaredFields();
        String[] fieldNames = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            fieldNames[i] = fields[i].getName();
        }

        String[] values;
        String[] forOutput = null;
        boolean matches = false;
        for (String s : input) {
            values = s.split(",");
            for (String value : values) {
                if (value.equals(id)) {
                    matches = true;
                    forOutput = values;
                    break;
                }
            }
        }
        if (!matches) {
            values = null;
        } else {
            values = forOutput;
        }
        if (values == null) return null;

        T object = clazz.newInstance();
        for (int i = 0; i < values.length; i++) {
            String type = object.getClass().getField(fieldNames[i]).getType().getTypeName();
            if (type.equals("java.lang.String")) {
                object.getClass().getField(fieldNames[i]).set(object, values[i]);
            }
            if (type.equals("int")) {
                object.getClass().getField(fieldNames[i]).setInt(object, Integer.parseInt(values[i]));
            }
            if (type.equals("boolean")) {
                object.getClass().getField(fieldNames[i]).setBoolean(object, Boolean.parseBoolean(values[i]));
            }
        }
        return object;
    }

    public static <T> List<T> getAllObjects(Class<T> clazz) throws InstantiationException, IllegalAccessException, NoSuchFieldException {
        String className = clazz.getSimpleName();
        String fileName = className.toLowerCase() + ".csv";
        List<String> input = readInputTxt(fileName);

        Field[] fields = clazz.getDeclaredFields();
        String[] fieldNames = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            fieldNames[i] = fields[i].getName();
        }

        List<T> result = new ArrayList<>();
        String[] values;
        for (String s : input) {
            values = s.split(",");
            T object = clazz.newInstance();
            for (int i = 0; i < values.length; i++) {
                String type = object.getClass().getField(fieldNames[i]).getType().getTypeName();
                if (type.equals("java.lang.String")) {
                    object.getClass().getField(fieldNames[i]).set(object, values[i]);
                }
                if (type.equals("int")) {
                    object.getClass().getField(fieldNames[i]).setInt(object, Integer.parseInt(values[i]));
                }
                if (type.equals("boolean")) {
                    object.getClass().getField(fieldNames[i]).setBoolean(object, Boolean.parseBoolean(values[i]));
                }
            }
            result.add(object);
        }
        return result;
    }

    public static <T> List<T> findRelation(Class<T> clazz, String id) throws NoSuchFieldException, InstantiationException, IllegalAccessException {
        List<String> inputRelation = readInputTxt("bookauthor.csv");
        List<String> idList = new ArrayList<>();
        for (String s : inputRelation) {
            if (s.contains(id)) {
                String[] relationArray = s.split(",");
                for (String value : relationArray) {
                    if (!value.equals(id)) {
                        idList.add(value);
                    }
                }
            }
        }
        List<T> result = new ArrayList<>();
        for (String s : idList) {
            if (getObject(clazz, s) != null) {
                result.add(getObject(clazz, s));
            }

        }
        return result;
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
