package ua.com.alevel.view.dto.request;

import ua.com.alevel.view.dto.RequestDto;

import java.util.Date;

public class AccountRequestDto extends RequestDto {

    private Long balance;
    private Date created;
    private Date updated;
    private Long userId;

    public AccountRequestDto() {

    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

}
