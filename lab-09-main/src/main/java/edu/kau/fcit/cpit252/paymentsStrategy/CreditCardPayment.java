package edu.kau.fcit.cpit252.paymentsStrategy;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class CreditCardPayment implements Payment {
    private String transactionId;
    private String name;
    private String cardNumber;
    private String cvv;
    private String monthYearExpiration;
    private Date date;


    public CreditCardPayment(String name, String cardNumber, String cvv,
                             String monthYearExpiration) {
        this.transactionId = UUID.randomUUID().toString();
        this.name = name;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.monthYearExpiration = monthYearExpiration;
        this.date = new Date();
    }

    public void pay(double amount){
        System.out.println("Payment using credit card");
    }

    @Override
    public String toString() {
        String datePattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern);
        return "Credit Card Payment " +
                "\n\tTransaction Id: " + this.transactionId +
                "\n\tDate: " + simpleDateFormat.format(this.date) +
                "\n\tCard Number: ****" +
                this.cardNumber.substring(this.cardNumber.length() - 5, this.cardNumber.length() - 1);
    }
}
