package models.Customer;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import exceptions.CustomerIdNotValidException;
import services.CheckIdService;

public abstract class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("name")
    private String name;

    @JsonProperty("customerId")
    private String customerId;

    public User() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustomerId() {
        return customerId;
    }

    public boolean setCustomerId(String customerId) throws CustomerIdNotValidException {
        if (!CheckIdService.getInstance().checkCustomerId(customerId))
            return false;

        this.customerId = customerId;
        return true;
    }

}