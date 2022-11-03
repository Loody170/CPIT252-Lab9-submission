package edu.kau.fcit.cpit252.receiptStrategy;

import edu.kau.fcit.cpit252.paymentsStrategy.Payment;
import edu.kau.fcit.cpit252.shopping.Product;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public abstract class Receipt {
    private String id;
    private List<Product> products;
    private String issueDate;

    public String getId() {
        return id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public Receipt(List<Product> products){
        this.id = UUID.randomUUID().toString();
        this.products = products;
        String datePattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern);
        this.issueDate = simpleDateFormat.format(new Date());
    }
    public abstract void generate();

}
