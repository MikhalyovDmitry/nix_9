package ua.com.alevel.view.dto.request;

import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.view.dto.RequestDto;

import java.util.List;
import java.util.Set;

public class UserRequestDto extends RequestDto {

    private String name;
    private String password;
    private String phone;
    private List<Account> accounts;

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
