package ua.com.alevel.view.dto.response;

import ua.com.alevel.persistence.entity.Operation;
import ua.com.alevel.type.Category;
import ua.com.alevel.view.dto.ResponseDto;

import java.math.BigDecimal;

public class OperationResponseDto extends ResponseDto {


    BigDecimal value;
    String name;
    Category category;
    Long accountId;

    public OperationResponseDto () {}

    public OperationResponseDto (Operation operation) {
        super.setId(operation.getId());
        this.value = BigDecimal.valueOf((double) operation.getValue() / 100).setScale(2);
        this.name = operation.getName();
        this.category = operation.getCategory();
        this.accountId = operation.getAccountId();
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
