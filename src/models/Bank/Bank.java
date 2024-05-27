package models.Bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import DAOs.CustomerDao;
import models.Account.Account;
import models.Customer.Customer;
import models.Customer.DigitalCustomer;

public class Bank {
    private static Bank instance;
    private final String id;
    private Map<String, Customer> idCustomersMap;

    protected Bank() {
        this.idCustomersMap = new HashMap<>();
        this.id = String.valueOf(UUID.randomUUID());
    }

    // Singleton pattern
    public static Bank getBankInstance() {
        if (instance == null) {
            instance = new Bank();
        }
        return instance;
    }

    public Map<String, Customer> getIdCustomersMap() {
        return idCustomersMap;
    }

    public Customer addCustomer(Customer customer) {
        if (isCustomerExisted(customer.getCustomerId())) {
            return null;
        }

        idCustomersMap.put(customer.getCustomerId(), customer);

        return customer;
    }

    public boolean isCustomerExisted(String customerId) {
        List<DigitalCustomer> customers = CustomerDao.getList();
        return customers.stream().anyMatch(customer -> customer.getCustomerId().equals(customerId));
    }

    public Account addAccount(String customerId, Account account) {
        Customer foundCustomer = searchCustomerByCCCD(customerId);

        if (foundCustomer == null) {
            return null;
        }

        return foundCustomer.addAccount(account);
    }

    public List<Customer> searchCustomerByName(String keyword) {
        String _keyword = keyword.toLowerCase();
        List<Customer> result = new ArrayList<>();

        result = idCustomersMap.values().stream()
                .filter(customer -> customer.getName().toLowerCase().contains(_keyword)).toList();

        return result;
    }

    public Customer searchCustomerByCCCD(String customerId) {
        return idCustomersMap.get(customerId);
    }

}
