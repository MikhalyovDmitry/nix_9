package ua.com.alevel.view.controller;

import ua.com.alevel.persistence.entity.Model;
import ua.com.alevel.service.ModelService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ModelController {

    private final ModelService modelService = new ModelService();

    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("select your option");
        String position;
        try {
            runNavigation();
            while ((position = reader.readLine()) != null) {
                if (position.equals("0")) {
                    System.exit(0);
                }
                crud(position, reader);
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void runNavigation() {
        System.out.println();
        System.out.println("if you want create model, please enter 1");
        System.out.println("if you want update model, please enter 2");
        System.out.println("if you want delete model, please enter 3");
        System.out.println("if you want findById model, please enter 4");
        System.out.println("if you want findAll model, please enter 5");
        System.out.println("if you want exit, please enter 0");
        System.out.println();
    }

    private void crud(String position, BufferedReader reader) {
        switch (position) {
            case "1" -> create(reader);
            case "2" -> update(reader);
            case "3" -> delete(reader);
            case "4" -> findById(reader);
            case "5" -> findAll(reader);
        }
        runNavigation();
    }

    private void create(BufferedReader reader) {
        System.out.println("ModelController.create");
        try {
            System.out.println("Please, enter your color");
            String color = reader.readLine();

            System.out.println("Please, enter your size");
            String sizeString = reader.readLine();
            int size = Integer.parseInt(sizeString);

            Model model = new Model();
            model.setSize(size);
            model.setColor(color);

            modelService.create(model);
        } catch (NumberFormatException | IOException e) {
            System.out.println("Error: = " + e.getMessage());
        }
    }

    private void update(BufferedReader reader) {
        System.out.println("ModelController.update");
        try {
            System.out.println("Please, enter id");
            String id = reader.readLine();

            System.out.println("Please, enter your color");
            String color = reader.readLine();

            System.out.println("Please, enter your size");
            String sizeString = reader.readLine();
            int size = Integer.parseInt(sizeString);

            Model model = new Model();
            model.setId(id);
            model.setSize(size);
            model.setColor(color);

            modelService.update(model);
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error: = " + e.getMessage());
        }
    }

    private void delete(BufferedReader reader) {
        System.out.println("ModelController.delete");
        try {
            System.out.println("Please, enter id");
            String id = reader.readLine();

            boolean deleteResult = modelService.delete(id);
            if (!deleteResult) {
                System.out.println("model not found");
            }
        } catch (IOException e) {
            System.out.println("Error: = " + e.getMessage());
        }
    }

    private void findById(BufferedReader reader) {
        System.out.println("ModelController.findById");
        try {
            System.out.println("Please, enter id");
            String id = reader.readLine();

            Model model = modelService.findById(id);

            String message = model != null ? ("model = " + model) : "model not found ";
            System.out.println(message);
        } catch (IOException e) {
            System.out.println("Error: = " + e.getMessage());
        }
    }

    private void findAll(BufferedReader reader) {
        System.out.println("ModelController.findAll");
        Model[] models = modelService.findAll();
        if (models != null && models.length != 0) {
            for (Model model : models) {
                System.out.println("model = " + model);
            }
        } else {
            System.out.println("models empty");
        }
    }
}