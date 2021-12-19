package ua.com.alevel.view.dto.response;

import ua.com.alevel.persistence.entity.Operation;
import ua.com.alevel.type.Category;
import ua.com.alevel.view.dto.ResponseDto;

import java.math.BigDecimal;
import java.util.Date;

public class OperationResponseDto extends ResponseDto {


    BigDecimal value;
    String name;
    Category category;
    Long accountId;
    Date created;

    public OperationResponseDto () {}

    public OperationResponseDto (Operation operation) {
        super.setId(operation.getId());
        this.value = BigDecimal.valueOf((double) operation.getValue() / 100).setScale(2);
        this.name = operation.getName();
        this.category = operation.getCategory();
        this.accountId = operation.getAccountId();
        this.created = operation.getCreated();
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = BigDecimal.valueOf(value);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
