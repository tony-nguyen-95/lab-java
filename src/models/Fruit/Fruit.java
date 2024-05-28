package models.Fruit;

public class Fruit {
    private Integer ID;
    private String name;
    private Double price;
    private String origin;
    private Integer quantity;

    public Fruit(Integer iD, String name, double price, String origin, Integer quantity) {
        ID = iD;
        this.name = name;
        this.price = price;
        this.origin = origin;
        this.quantity = quantity;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer iD) {
        ID = iD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public double getTotalValue() {
        return this.quantity * this.price;
    }

}
