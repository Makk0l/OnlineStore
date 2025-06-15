package models;

public class PromoCode {
    private final String code;
    private final double discount;

    public PromoCode(String code, double discount) {
        this.code = code.toUpperCase();
        this.discount = Math.min(Math.max(discount, 0), 100);
    }

    public String getCode() {
        return code;
    }


    public double getDiscount() {
        return discount;
    }

}
