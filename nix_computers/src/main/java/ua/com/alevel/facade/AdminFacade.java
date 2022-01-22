package ua.com.alevel.facade;


import ua.com.alevel.view.dto.request.AdminRequestDto;
import ua.com.alevel.view.dto.response.AdminResponseDto;

public interface AdminFacade extends BaseFacade<AdminRequestDto, AdminResponseDto>{

    Long findByName(String name);
}
