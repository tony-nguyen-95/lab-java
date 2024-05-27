package DAOs;

import java.util.List;

import models.Customer.Customer;
import models.Customer.DigitalCustomer;
import services.BinaryFileService;

public class CustomerDao {
    private final static long serialVersionUID = 1;

    private final static String FILE_PATH = "stores/customers.dat";

    public static List<DigitalCustomer> getList() {
        return BinaryFileService.readBinaryFile(FILE_PATH);
    }

    public static void save(List<DigitalCustomer> customers) {
        BinaryFileService.writeBinaryFile(customers, FILE_PATH);
    }

    public static void updateSingle(Customer customer) {
        // TODO
    }

}
