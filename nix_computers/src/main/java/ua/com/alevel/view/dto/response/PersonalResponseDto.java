package ua.com.alevel.view.dto.response;

import ua.com.alevel.persistence.entity.user.Personal;
import ua.com.alevel.view.dto.ResponseDto;

public class PersonalResponseDto extends ResponseDto {

    private String email;
    private String firstName;
    private String lastName;

    public PersonalResponseDto(Personal personal) {
        if (personal != null) {
            this.email = personal.getEmail();
            this.firstName = personal.getFirstName();
            this.lastName = personal.getLastName();
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
