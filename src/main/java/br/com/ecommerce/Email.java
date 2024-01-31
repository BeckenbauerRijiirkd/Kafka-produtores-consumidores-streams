package br.com.ecommerce;

public class Email {

    private final String sunject, orderId;


    public Email(String sunject, String orderId) {
        this.sunject = sunject;
        this.orderId = orderId;
    }
}
