package ua.com.alevel.view.dto.request;

import ua.com.alevel.view.dto.RequestDto;

public class PersonalRequestDto extends RequestDto {

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
