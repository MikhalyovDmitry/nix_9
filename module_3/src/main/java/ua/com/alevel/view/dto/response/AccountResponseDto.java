package ua.com.alevel.view.dto.response;

import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.User;
import ua.com.alevel.view.dto.ResponseDto;

import java.math.BigDecimal;
import java.util.Date;

public class AccountResponseDto extends ResponseDto {

    private BigDecimal balance;
    private Date created;
    private Date updated;
    private String user;

    public AccountResponseDto() {}

    public AccountResponseDto(Account account) {
        super.setId(account.getId());
        this.balance = BigDecimal.valueOf((double) account.getBalance() / 100).setScale(2);
        this.created = account.getCreated();
        this.updated = account.getUpdated();
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "AccountResponseDto{" +
                "balance=" + balance +
                ", created=" + created +
                ", updated=" + updated +
                ", user='" + user + '\'' +
                '}';
    }
}
