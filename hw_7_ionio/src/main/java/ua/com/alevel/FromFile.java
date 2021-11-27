package ua.com.alevel;

import ua.com.alevel.entity.Book;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FromFile {


    public static <T> T getObject(Class<T> clazz, String id) throws InstantiationException, IllegalAccessException, NoSuchFieldException, ClassNotFoundException {

        String className = clazz.getSimpleName();
        //System.out.println(className);

        String fileName = className.toLowerCase() + ".csv";
        //System.out.println(fileName);

        List<String> input = readInputTxt(fileName);

        if (input.isEmpty()) return null;

        Field[] fields = clazz.getDeclaredFields();
        String[] fieldNames = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            fieldNames[i] = fields[i].getName();
        }
        //System.out.println(Arrays.toString(fieldNames));

        String[] values = null;
        for (String s : input) {
            if (s.contains(id)) {
                values = s.split(",");
            }
        }
        if (values == null) return null;

        T object = clazz.newInstance();
        for (int i = 0; i < values.length; i++) {
            String type = object.getClass().getField(fieldNames[i]).getType().getTypeName();
            //System.out.println(type);
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
        //System.out.println(object.toString());

        return (T) object;
    }

    public static <T> List<T> getAllObjects(Class<T> clazz) throws InstantiationException, IllegalAccessException, NoSuchFieldException {

        String className = clazz.getSimpleName();
        //System.out.println(className);

        String fileName = className.toLowerCase() + ".csv";
        //System.out.println(fileName);

        List<String> input = readInputTxt(fileName);

        Field[] fields = clazz.getDeclaredFields();
        String[] fieldNames = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            fieldNames[i] = fields[i].getName();
        }
        //System.out.println(Arrays.toString(fieldNames));

        List<T> result = new ArrayList<>();

        String[] values;
        for (String s : input) {
            values = s.split(",");
            //System.out.println(Arrays.toString(values));
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
            //System.out.println(object);
            result.add(object);
        }
        return result;
    }

    public static <T> List<T> findRelation(Class<T> clazz, String id) throws NoSuchFieldException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        List<String> inputRelation = readInputTxt("bookauthor.csv");

        List<String> idList = new ArrayList<>();
        for (int i = 0; i < inputRelation.size(); i++) {
            if (inputRelation.get(i).contains(id)) {
                //System.out.println("contains id");
                String[] relationArray = inputRelation.get(i).split(",");
                for (int j = 0; j < relationArray.length; j++) {
                    if (!relationArray[j].equals(id)) {
                        //System.out.println("added: " + relationArray[j]);
                        idList.add(relationArray[j]);
                    }
                }
            }
        }
        //System.out.println("idList: " + idList.toString());

        List<T> result = new ArrayList<>();
        for (int i = 0; i < idList.size(); i++) {
            if (getObject(clazz, idList.get(i)) != null) {
                result.add(getObject(clazz, idList.get(i)));
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
        //System.out.println(fileName + ": " + input);
        return input;
    }
}
