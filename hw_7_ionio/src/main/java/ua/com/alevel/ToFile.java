package ua.com.alevel;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ToFile {

    public static void create(Object object) {

        String fileName = null;
        String entity = object.getClass().getSimpleName();
        if (entity.equals("Book")) {
            fileName = "book.csv";
            //System.out.println(fileName);
        }
        if (entity.equals("Author")) {
            fileName = "author.csv";
            //System.out.println(fileName);
        }
        if (entity.equals("BookAuthor")) {
            fileName = "bookauthor.csv";
            //System.out.println(fileName);
        }

        Field[] fields = object.getClass().getDeclaredFields();
        String[] fieldNames = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            fieldNames[i] = fields[i].getName();
        }
        //System.out.println(Arrays.toString(fieldNames));

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
                    objectValues.append(fieldValue.toString());
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

        //System.out.println(objectValues);
        List<String> input = readInputTxt(fileName);
        if (!input.isEmpty()) {
            System.out.println("Index of ln: " + input.get(input.size() - 1).indexOf("\n"));

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

        String className = clazz.getSimpleName();
        //System.out.println(className);

        String fileName = className.toLowerCase() + ".csv";
        //System.out.println(fileName);

        List<String> input = readInputTxt(fileName);

        StringBuilder output = new StringBuilder();
        for (int i = 0; i < input.size(); i++) {
            if (input.get(i).contains(id)) {
                input.remove(i);
                i--;
            } else {
                output.append(input.get(i));
                if (i != input.size() - 1) {
                    output.append("\n");
                }
            }
        }

        //System.out.println(input.toString());

        try {
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write(output.toString());
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void delete(String bookId, String authorId) {
        //System.out.println("Deleting by bookId and authorId...");
        List<String> input = readInputTxt("bookauthor.csv");

        StringBuilder output = new StringBuilder();
        for (int i = 0; i < input.size(); i++) {
            //System.out.println("Check "+ (i + 1) + " of " + input.size()  + input.get(i) + " contains " + bookId + " and " + authorId);
            if (input.get(i).contains(bookId) && input.get(i).contains(authorId)) {
                //System.out.println(input.get(i) + " contains id's and deleted!");
                input.remove(i);
                i--;
            } else {
                output.append(input.get(i));
                if (i != input.size() - 1) {
                    output.append("\n");
                }
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

        String className = object.getClass().getSimpleName();
        //System.out.println(className);

        String fileName = className.toLowerCase() + ".csv";
        //System.out.println(fileName);

        List<String> input = readInputTxt(fileName);

        String id = object.getClass().getField("id").get(object).toString();
        System.out.println(id);

        boolean isLastInList = false;
        for (int i = 0; i < input.size(); i++) {
            if (input.get(i).contains(id) && i == input.size() - 1) {
                isLastInList = true;
                break;
            }
        }

        delete(object.getClass(), id);
        if (!isLastInList) {
            try {
                FileWriter fileWriter = new FileWriter(fileName, true);
                fileWriter.write("\n");
                fileWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

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
        //System.out.println(fileName + ": " + input);
        return input;
    }

}
