package edu.kau.fcit.cpit252.shopping;

public class Product {
    private String sku;
    private String name;
    private double price;

    public Product(String name, String sku, double price) {
        this.name = name;
        this.sku = sku;
        this.price = price;
    }

    public String getSKU() {
        return this.sku;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public String toString(){
        return this.name + "\t" + "SKU: " + this.sku + 
        "\t price: $" + this.price;
    }
}