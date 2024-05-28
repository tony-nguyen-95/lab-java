package models.Bill;

import java.util.HashMap;
import java.util.Map;

import models.Fruit.Fruit;
import models.FruitShop.FruitShop;
import utils.GetCurrentDateTime;

public class Bill {
    private String nameOfCustomer = "";
    private Map<Integer, Fruit> idFruitToBuyMap = new HashMap<>();
    private double totalCost = 0.0;

    public Bill() {
    }

    public double getTotalCost() {
        return this.totalCost;
    }

    public void addFruitToBill(int fruitId, int quantityToBuy) {
        Fruit foundFruit = FruitShop.getFruitShopInstance().findFruitById(fruitId);

        if (foundFruit == null) {
            System.out.println("Trái cây với ID " + fruitId + " không tồn tại trong cửa hàng.");
            return;
        }

        if (idFruitToBuyMap.containsKey(fruitId)) {
            Fruit existingFruitInBill = idFruitToBuyMap.get(fruitId);
            existingFruitInBill.setQuantity(existingFruitInBill.getQuantity() + quantityToBuy);
        } else {
            String name = foundFruit.getName();
            double costPerUnit = foundFruit.getPrice();
            String origin = foundFruit.getOrigin();
            Fruit newFruitInBill = new Fruit(fruitId, name, costPerUnit, origin, quantityToBuy);
            idFruitToBuyMap.put(fruitId, newFruitInBill);
        }

        this.totalCost += quantityToBuy * foundFruit.getPrice();
    }

    public void printBill() {
        System.out.println(
                "\n  Khách hàng: " + nameOfCustomer + " | Ngày mua: " + GetCurrentDateTime.getDateTimeFormatted());
        System.out.println(" | STT | Sản phẩm | Số lượng |       Giá |   Số tiền |");
        int i = 1;
        for (Fruit fruit : idFruitToBuyMap.values()) {
            System.out.printf(" | %2d. | %8s | %8d | %8.2f$ | %8.2f$ |\n",
                    i++, fruit.getName(), fruit.getQuantity(), fruit.getPrice(),
                    fruit.getQuantity() * fruit.getPrice());
        }
        System.out.printf(" Tổng cộng: %.2f$\n", this.totalCost);
    }

    public void setNameOfCustomer(String nameOfCustomer) {
        this.nameOfCustomer = nameOfCustomer;
    }

    public Map<Integer, Fruit> getIdFruitToBuyMap() {
        return idFruitToBuyMap;
    }

    public String getNameOfCustomer() {
        return nameOfCustomer;
    }

}
