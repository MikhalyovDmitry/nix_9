package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.PersonalFacade;
import ua.com.alevel.service.PersonalService;
import ua.com.alevel.view.dto.request.PersonalRequestDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.PersonalResponseDto;

@Service
public class PersonalFacadeImpl implements PersonalFacade {

    private final PersonalService personalService;

    public PersonalFacadeImpl(PersonalService personalService) {
        this.personalService = personalService;
    }

    @Override
    public Long findByName(String name) {
        return personalService.findByName(name);
    }

    @Override
    public void create(PersonalRequestDto personalRequestDto) {

    }

    @Override
    public void update(PersonalRequestDto personalRequestDto, Long id) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public PersonalResponseDto findById(Long id) {
        return null;
    }

    @Override
    public PageData<PersonalResponseDto> findAll(WebRequest request) {
        return null;
    }
}
