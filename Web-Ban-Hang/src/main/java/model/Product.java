package model;

public class Product {
    private  int id;
    private  String name ;
    private  double price;
    private String description;
    private long quantity;
    private String img;
    private int categoryId;
    private double orderPrice;

    public Product() {
    }

    public Product(int id, String name, double price, String description, long quantity,String img) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
        this.img = img;
    }

    public Product(int id, String name, double price, long quantity,String img ,double orderPrice) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.orderPrice = orderPrice;
    }

    public Product(int id, String name, double price, String description, long quantity, String img, int categoryId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
        this.img = img;
        this.categoryId = categoryId;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
