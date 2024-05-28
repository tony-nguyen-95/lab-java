package models.FruitShop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import data.MapIdFruitData;
import models.Bill.Bill;
import models.Fruit.Fruit;
import utils.PrintDividerUtil;

public class FruitShop {
    private static FruitShop instance;
    private Map<Integer, Fruit> idFruitMap = new HashMap<>();
    private Map<String, ArrayList<Bill>> nameBillListMap = new HashMap<>();

    private FruitShop() {
        this.idFruitMap = MapIdFruitData.getInstance().getRootIdFruitMap();
    }

    public static FruitShop getFruitShopInstance() {
        if (instance == null) {
            instance = new FruitShop();
        }
        return instance;
    }

    public void showFruitsInShop() {
        if (this.idFruitMap.size() == 0) {
            System.out.println("Hiện chưa có trái cây nào trong cửa hàng!");
            return;
        }

        System.out.println("Danh sách trái cây hiện có");

        System.out.println("| ++ Mục++ | ++ Tên trái cây ++    | ++ Xuất xứ++ | ++ Giá++ | ++ Số lượng còn++ |");
        int index = 1;
        for (Fruit fruit : this.idFruitMap.values()) {
            if (fruit.getQuantity() > 0) {
                System.out.printf("   %d            %-22s %-16s %-12s %-12d\n",
                        index++, fruit.getName(), fruit.getOrigin(), fruit.getPrice() + "$", fruit.getQuantity());
            }
        }
    }

    public Fruit addFruit(String name, String origin, double price, int quantity) {
        int newId = idFruitMap.size() + 1;
        Fruit newFruit = new Fruit(newId, name, price, origin, quantity);
        idFruitMap.put(newId, newFruit);

        return newFruit;
    }

    public static FruitShop getInstance() {
        return instance;
    }

    public Map<Integer, Fruit> getIdFruitMap() {
        return idFruitMap;
    }

    public boolean processBill(Bill bill) {
        for (Map.Entry<Integer, Fruit> entry : bill.getIdFruitToBuyMap().entrySet()) {
            int fruitId = entry.getKey();
            Fruit fruitInBill = entry.getValue();
            if (idFruitMap.containsKey(fruitId)) {
                Fruit fruitInShop = idFruitMap.get(fruitId);
                if (fruitInShop.getQuantity() >= fruitInBill.getQuantity()) {
                    fruitInShop.setQuantity(fruitInShop.getQuantity() - fruitInBill.getQuantity());
                } else {
                    System.out.println("Số lượng " + fruitInBill.getName() + " không đủ trong cửa hàng.");
                    return false;
                }
            } else {
                System.out.println("Trái cây với ID " + fruitId + " không tồn tại trong cửa hàng.");
                return false;
            }
        }

        String customerName = bill.getNameOfCustomer();
        if (!nameBillListMap.containsKey(customerName)) {
            nameBillListMap.put(customerName, new ArrayList<>());
        }
        nameBillListMap.get(customerName).add(bill);
        return true;
    }

    public void displayNameBillListMap() {
        PrintDividerUtil.printLine();

        if (nameBillListMap.size() == 0) {
            System.out.println("Chưa có hóa đơn nào.");
            return;
        }

        System.out.println("Danh sách hóa đơn:");

        for (Map.Entry<String, ArrayList<Bill>> entry : nameBillListMap.entrySet()) {
            PrintDividerUtil.printLineShort();
            System.out.println("\nTên khách hàng: " + entry.getKey());
            for (Bill bill : entry.getValue()) {
                bill.printBill();
            }
        }
    }

    public Fruit findFruitById(int id) {
        return idFruitMap.get(id);
    }
}
