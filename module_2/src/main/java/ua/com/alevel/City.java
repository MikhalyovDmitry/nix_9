package ua.com.alevel;

import java.io.OutputStream;
import java.util.*;

public class City {
    int index;
    String name;
    Map<Integer, Integer> cities;

    public static int citiesSize(List<String> input) {
        return Integer.parseInt(input.get(0));
    }

    public static int[][] citiesToMatrix(List<City> cities, List<String> input) {
        int INF = 1000000000;
        int size = citiesSize(input);
        int[][] matrix = new int[size][size];
        int city;
        for (City value : cities) {
            city = value.getIndex();
            Set<Integer> a = value.getCities().keySet();
            for (Integer key : a) {
                matrix[key][city] = value.getCities().get(key);
            }
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i != j && matrix[i][j] == 0) matrix[i][j] = INF;
            }
        }
        return matrix;
    }

    public static void run(List<String> input) {
        System.out.println(input);
        List<City> cities = City.parse(input);
        if (cities == null) {
            System.out.println("Sorry, input data is invalid! System exit");
            System.exit(0);
        }
        int size = City.citiesSize(input);
        int[][] matrix = citiesToMatrix(cities, input);
//        for (int i = 0; i < size; i++) {
//            for (int j = 0; j < size; j++) {
//                if (matrix[i][j] == 1000000000) {
//                    System.out.print("n ");
//                } else {
//                    System.out.print(matrix[i][j] + " ");
//                }
//            }
//            System.out.println();
//        }
        List<String> paths = City.pathsToFind(input);
        String output = City.findPaths(paths, cities, matrix, size);
        Output.run(output);
    }

    public static List<String> pathsToFind(List<String> input) {
        List<String> result = new ArrayList<>();
        int index = 2;
        for (int i = 0; i < citiesSize(input); i++) {
            index = index + Integer.parseInt(input.get(index)) + 2;
        }
        for (int i = index; i < input.size(); i++) {
            result.add(input.get(i));
        }
        return result;
    }

    public static String findPaths(List<String> paths, List<City> cities, int[][] matrix, int size) {
        FindShortestPaths.run(matrix, size);
        StringBuilder result = new StringBuilder();
        for (String path : paths) {
            String[] s = path.split(" ");
            int cityOne = getCity(cities, s[0]).getIndex();
            int cityTwo = getCity(cities, s[1]).getIndex();
            result.append("Shortest way from ")
                    .append(s[0])
                    .append(" to ")
                    .append(s[1])
                    .append(" = ")
                    .append(matrix[cityOne][cityTwo]).append("\n");
        }
        return result.toString();
    }

    public static City getCity(List<City> cities, String name) {
        for (City city : cities) {
            if (city.getName().equals(name))
                return city;
        }
        return null;
    }

    public static List<City> parse(List<String> input) {
        List<City> result = new ArrayList<>();
        Map<Integer, Integer> map;
        int index = 0;
        int near;
        String[] cities;
        City city;
        int cityCount = 1;
        int i = 1;
        try {
            do {
                city = new City();
                map = new HashMap<>();
                city.setName(input.get(i));
                city.setIndex(index);
                i++;
                if (i >= input.size()) return null;
                near = Integer.parseInt(input.get(i));
                i++;
                for (int j = 0; j < near; j++) {
                    cities = input.get(i + j).split(" ");
                    map.put(Integer.parseInt(cities[0]) - 1, Integer.parseInt(cities[1]));
                }
                city.setCities(map);
                result.add(city);
                index++;
                i = i + near;
                cityCount++;
            }
            while (cityCount <= citiesSize(input));
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Integer, Integer> getCities() {
        return cities;
    }

    public void setCities(Map<Integer, Integer> cities) {
        this.cities = cities;
    }

    @Override
    public String toString() {
        return "\nCity{" +
                "index=" + index +
                ", name='" + name + '\'' +
                ", cities=" + cities +
                '}';
    }
}
