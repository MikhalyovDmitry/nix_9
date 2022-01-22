package ua.com.alevel.view.dto.response;

import ua.com.alevel.persistence.entity.user.Admin;
import ua.com.alevel.persistence.entity.user.Personal;
import ua.com.alevel.view.dto.ResponseDto;

public class AdminResponseDto extends ResponseDto {

    private String email;

    public AdminResponseDto(Admin admin) {
        if (admin != null) {
            this.email = admin.getEmail();
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
