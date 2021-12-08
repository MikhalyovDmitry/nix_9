package ua.com.alevel.service;

import ua.com.alevel.persistence.dao.ModelDao;
import ua.com.alevel.persistence.entity.Model;

public class ModelService {

    private final ModelDao modelDao = new ModelDao();

    public void create(Model model) {
        modelDao.create(model);
    }

    public void update(Model model) {
        modelDao.update(model);
    }

    public boolean delete(String id) {
        return modelDao.delete(id);
    }

    public Model findById(String id) {
        return modelDao.findById(id);
    }

    public Model[] findAll() {
        return modelDao.findAll();
    }
}
