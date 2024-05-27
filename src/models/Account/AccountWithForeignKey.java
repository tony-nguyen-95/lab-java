package models.Account;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountWithForeignKey extends Account {
    @JsonProperty("customerId")
    private String customerId;

    public AccountWithForeignKey() {
        super();
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

}
