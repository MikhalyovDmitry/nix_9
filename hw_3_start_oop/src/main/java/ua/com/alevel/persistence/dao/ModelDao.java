package ua.com.alevel.persistence.dao;

import ua.com.alevel.db.ModelDB;
import ua.com.alevel.persistence.entity.Model;

public class ModelDao {

    public void create(Model model) {
        ModelDB.getInstance().create(model);
    }

    public void update(Model model) {
        ModelDB.getInstance().update(model);
    }

    public boolean delete(String id) {
        return ModelDB.getInstance().delete(id);
    }

    public Model findById(String id) {
        return ModelDB.getInstance().findById(id);
    }

    public Model[] findAll() {
        return ModelDB.getInstance().findAll();
    }
}
