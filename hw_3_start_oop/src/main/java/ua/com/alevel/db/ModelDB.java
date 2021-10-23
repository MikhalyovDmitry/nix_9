package ua.com.alevel.db;

import ua.com.alevel.entity.Model;

import java.util.Arrays;
import java.util.UUID;

public final class ModelDB {

    private Model[] models;
    private static ModelDB instance;

    int size = 0;

    private ModelDB() {
        models = new Model[size];
    }

    public static ModelDB getInstance() {
        if (instance == null) {
            instance = new ModelDB();
        }
        return instance;
    }

    public void create(Model model) {
        Model[] modelsImproved = new Model[size + 1];
        model.setId(generateId());
        if (size >= 0) System.arraycopy(models, 0, modelsImproved, 0, size);
        size++;
        modelsImproved[size - 1] = model;
        models = modelsImproved;

    }

    public void update(Model model) {
        Model current = findById(model.getId());
        current.setSize(model.getSize());
        current.setColor(model.getColor());
    }

    public boolean delete(String id) {
        for (int i = 0; i < size; i++) {
            if (models[i].getId().equals(id)) {
                deleteAt(i);
                return true;
            }
        }
        return false;
    }

    private void deleteAt(int index) {
        Model[] modelsImproved = new Model[size - 1];
        for (int i = 0, iD = 0; i < size; i++) {
            if (i != index) {
                modelsImproved[iD] = models[i];
                iD++;
            }

        }
        size--;
        models = modelsImproved;
    }

    public Model findById(String id) {
        return Arrays.stream(models)
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Model[] findAll() {
        return models;
    }

    private String generateId() {
        String id = UUID.randomUUID().toString();
        if (Arrays.stream(models).anyMatch(model -> model.getId().equals(id))) {
            return generateId();
        }
        return id;
    }
}
