package edu.kau.fcit.cpit252;

import edu.kau.fcit.cpit252.receiptStrategy.EmailReceipt;
import edu.kau.fcit.cpit252.receiptStrategy.PdfReceipt;
import edu.kau.fcit.cpit252.receiptStrategy.PrintReceipt;
import edu.kau.fcit.cpit252.receiptStrategy.Receipt;
import edu.kau.fcit.cpit252.shopping.ShoppingCart;
import edu.kau.fcit.cpit252.shopping.Product;
import edu.kau.fcit.cpit252.paymentsStrategy.*;
import java.util.Scanner;  

public class App {
    public static void main(String[] args){
        ShoppingCart cart = new ShoppingCart();
		
		Product item1 = new Product("Body wash", "549032", 10.99);
		Product item2 = new Product("Shampoo", "872150",14.99);
		
		cart.addItem(item1);
		cart.addItem(item2);
		
        System.out.println("Shopping cart:\n"+ cart.toString());
        System.out.println("Your total is: $" + cart.calculateTotal());
        System.out.println("\nPlease select your payment method:\n" +
                "1. Credit Card\n2. PayPal\n");
		Scanner scannerPayment = new Scanner(System.in);
        int paymentMethod = scannerPayment.nextInt();
        scannerPayment.nextLine();
        if (paymentMethod == 1){
            cart.pay(new CreditCardPayment("Ali Omar", "4111111111111111", "123", "12/23"));
        }
        else if(paymentMethod == 2){
            cart.pay(new PayPalPayment("email@example.com", "Our neighborhood market"));
        }

        String subject = "Your receipt from your neighborhood market";
        System.out.println("\nPlease select your receipt delivery method:\n" +
                "1. Email Receipt\n2. Download a PDF Receipt\n3. Print Receipt");
        Scanner scannerReceipt = new Scanner(System.in);
        int receiptMethod = scannerReceipt.nextInt();
        scannerReceipt.nextLine();
        Receipt receipt = null;
        if (receiptMethod == 1){
            System.out.println("Please enter the recipient's email address:");
            String recipientEmail = scannerReceipt.nextLine();
            receipt = new EmailReceipt(subject, recipientEmail, cart.getProducts());
        }
        else if(receiptMethod == 2){
            System.out.println("Please enter a path on your computer to download the PDF file at :");
            String path = scannerReceipt.nextLine();
            receipt = new PdfReceipt(subject, path,cart.getProducts());
        }
        else if(receiptMethod == 3){
            receipt = new PrintReceipt(subject, cart.getProducts());
        }
        else{
            System.err.println("Invalid receipt choice.");
            return;
        }
        simulateNetworkLatency();
        receipt.generate();

    }

    private static void simulateNetworkLatency() {
        try {
            System.out.println();
            for (int i=0; i < 10; i++) {
                System.out.print(".");
                Thread.sleep(500);
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

}
