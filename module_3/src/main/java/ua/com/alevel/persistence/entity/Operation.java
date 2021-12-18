package ua.com.alevel.persistence.entity;

import ua.com.alevel.type.Category;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "operations")
public class Operation extends BaseEntity {

    Long value;
    String name;

    @Enumerated(EnumType.STRING)
    Category category;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Column(name = "account_id")
    Long accountId;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(String value) {
        double doubleValue = Double.parseDouble(value);
        this.value = (long) (doubleValue * 100);
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
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
