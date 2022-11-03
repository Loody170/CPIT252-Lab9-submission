package edu.kau.fcit.cpit252.paymentsStrategy;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class PayPalPayment implements Payment  {
    private String transactionId;
    private String email;
    public String merchant;
    public Date date;

    public PayPalPayment(String email, String merchant) {
        this.email = email;
        this.merchant = merchant;
        this.date = new Date();
        this.transactionId = UUID.randomUUID().toString();
    }
    public void pay(double amount){
        System.out.println("Payment using PayPal");
    }

    @Override
    public String toString() {
        String datePattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern);
        return String.format("PayPal Payment:" +
                "\n\tTransaction Id " + this.transactionId +
                "\n\tDate: " + simpleDateFormat.format(this.date) +
                "\n\tMerchant: " + this.merchant +
                "\n\t" + "Email: " + this.email + "\n");
    }
}
