package services;

import org.junit.Test;

import models.Customer.DigitalCustomer;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BinaryFileServiceTest {

    private final String filePath = "stores/customers.dat";
    private final String fileJsonPath = "stores/seed-customers.json";

    @Test
    public void testWriteBinaryFile() {

        List<DigitalCustomer> dataToTest = JsonFileService.readJsonFile(fileJsonPath, DigitalCustomer.class);

        BinaryFileService.writeBinaryFile(dataToTest, filePath);

        assertTrue(new File(filePath).exists());
    }

    @Test
    public void testReadBinaryFile() {
        List<DigitalCustomer> dataToTest = JsonFileService.readJsonFile(fileJsonPath, DigitalCustomer.class);

        BinaryFileService.writeBinaryFile(dataToTest, filePath);
        List<DigitalCustomer> dataRead = BinaryFileService.readBinaryFile(filePath);

        assertTrue(dataRead != null);
        assertEquals(dataToTest.size(), dataRead.size());
    }
}
