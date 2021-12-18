package ua.com.alevel.view.dto.response;

import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.User;
import ua.com.alevel.view.dto.ResponseDto;
import java.util.List;

public class UserResponseDto extends ResponseDto {

    private String name;
    private String password;
    private String phone;
    private List<Account> accounts;

    public UserResponseDto() {
    }

    public UserResponseDto(User user) {
        super.setId(user.getId());
        this.name = user.getName();
        this.password = user.getPassword();
        this.phone = user.getPhone();
        this.accounts = user.getAccounts();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
