package ua.com.alevel.service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.user.Admin;
import ua.com.alevel.persistence.repository.user.AdminRepository;
import ua.com.alevel.service.AdminService;

import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AdminRepository adminRepository;
    private final CrudRepositoryHelper<Admin, AdminRepository> crudRepositoryHelper;

    public AdminServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder,
                            AdminRepository adminRepository,
                            CrudRepositoryHelper<Admin, AdminRepository> crudRepositoryHelper) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.adminRepository = adminRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
    }

    @Override
    public void create(Admin entity) {

    }

    @Override
    public void update(Admin entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Optional<Admin> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public DataTableResponse<Admin> findAll(DataTableRequest dataTableRequest) {
        return null;
    }


    @Override
    public Long findByName(String name) {
        if (adminRepository.findByEmail(name) != null) {
            return adminRepository.findByEmail(name).getId();
        } else {
            return null;
        }
    }
}
