package ua.com.alevel.facade;

import ua.com.alevel.view.dto.request.register.AuthDto;

public interface RegistrationFacade {

    void registration(AuthDto dto);
}
