package com.hossam.elabda3.retrofit;

public class Const {

    public static final String BASE_URL = "http://gng.invatomarket.com/api/";
    public static final String COD = "COD";
    public static final String DB_NAME = "com.retrytech.veginew";
    public static final String DEVKEY = "123";
    public static final String EXPET_HOLD_PENDING_ORDER_COUNT = "expetholdpending";
    public static final String IMAGE_URL = "http://gng.invatomarket.com/public/storage/";
    public static final String IS_LOGIN = "islogin";
    public static final int LIMIT = 10;
    public static long MAX_QUANTITY = 10;
    public static final String NOTIFICATION_TOKEN = "notificationtoken";
    public static final String PREF_NAME = "com.retrytech.veginew";
    public static final String PRODUCT_DATA = "product_data";
    public static final int PRODUCT_RESULT = 512;
    public static final String P_ID = "productID";
    public static final String SETTING = "setting";
    public static final String STRIPE = "STRIPE";
    public static final String STRIPE_CURRENCY = "INR";
    public static final String STRIPE_PUBLISHABLE_KEY = "pk_test_51HzgB2JjHQfv8kkX8JoFKOHb5w6fTwnitc4ncpDfoArG6AUhU7K8wINadgoIujVlATubDonK1EbUMaxhqr3xcFYD00KkJYywkX";
    public static final String STRIPE_SECRET_KEY = "sk_test_51HzgB2JjHQfv8kkXw0cumkzYyjRaL0CAVxKkW4d5R9FXwZF469iAObtXCaK3LIaHsHa4tEMM5wrIehetMB7HskSI00fDsgwInS";
    public static final String STR_CID = "cid";
    public static final String STR_CNAME = "cname";
    public static final String USER = "user";
    public static final String USER_IMAGE = "userimgurl";
    public static final String VEGI = "Vegi";
    public static final String WISHLIST = "wishlist";
    private static String currency = "$";

    public static long getMaxQuantity() {
        return MAX_QUANTITY;
    }

    public static void setMaxQuantity(long maxQuantity) {
        MAX_QUANTITY = maxQuantity;
    }

    public static String getCurrency() {
        return currency;
    }

    public static void setCurrency(String currency2) {
        currency = currency2;
    }
}
