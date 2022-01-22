package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.user.Admin;

public interface AdminService extends BaseService<Admin>{

    Long findByName(String name);
}
