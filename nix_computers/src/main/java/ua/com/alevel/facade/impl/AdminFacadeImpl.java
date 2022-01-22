package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.AdminFacade;
import ua.com.alevel.service.AdminService;
import ua.com.alevel.view.dto.request.AdminRequestDto;
import ua.com.alevel.view.dto.response.AdminResponseDto;
import ua.com.alevel.view.dto.response.PageData;

@Service
public class AdminFacadeImpl implements AdminFacade {

    private final AdminService adminService;

    public AdminFacadeImpl(AdminService adminService) {
        this.adminService = adminService;
    }


    @Override
    public Long findByName(String name) {
        return adminService.findByName(name);
    }

    @Override
    public void create(AdminRequestDto adminRequestDto) {

    }

    @Override
    public void update(AdminRequestDto adminRequestDto, Long id) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public AdminResponseDto findById(Long id) {
        return null;
    }

    @Override
    public PageData<AdminResponseDto> findAll(WebRequest request) {
        return null;
    }
}
