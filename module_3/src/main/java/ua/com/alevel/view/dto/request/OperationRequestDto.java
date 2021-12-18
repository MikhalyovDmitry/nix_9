package ua.com.alevel.view.dto.request;

import ua.com.alevel.type.Category;
import ua.com.alevel.view.dto.RequestDto;

public class OperationRequestDto extends RequestDto {

    String value;
    String name;
    Category category;
    Long accountId;

    public OperationRequestDto () {}

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String  getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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

    @Override
    public String toString() {
        return "OperationRequestDto{" +
                "value=" + value +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", accountId=" + accountId +
                '}';
    }
}
