package pl.mobiltek.dotpayexample.model;

import pl.mobiltek.paymentsmobile.dotpay.model.PaymentCardData;

public class ShopData {

    private static final String name = "Jan";
    private static final String lastName = "Janowy";
    private static final String email = "test@example.com";

    //    private  final String merchantId = "777777";
    private static final String merchantId = "855021";
    private static final double productPrice = 1759;

    private static final String description = "Test payment for Sony Xperia Z3";
    private static final String currency = "PLN";
    private static final String language = "pl";

    private static final PaymentCardData paymentCard = new PaymentCardData("4916 9715 6289 1025", "025", "Jan", "Nowak", "08", "2020");


    public static String getName() {
        return name;
    }

    public static String getLastName() {
        return lastName;
    }

    public static String getEmail() {
        return email;
    }

    public static String getMerchantId() {
        return merchantId;
    }

    public static double getProductPrice() {
        return productPrice;
    }

    public static String getDescription() {
        return description;
    }

    public static String getCurrency() {
        return currency;
    }

    public static String getLanguage() {
        return language;
    }

    public static PaymentCardData getPaymentCard() {
        return paymentCard;
    }
}
