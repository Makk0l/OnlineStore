package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Cart {
    private final List<CartItem> items = new ArrayList<>();
    private PromoCode appliedCode;
    private boolean isDiscount = false;
    private boolean isPromoCodeApplied = false;


    public void addItem(Product product, int quantity) {
        if (quantity <= 0) {
            System.out.println("Количество товаров должно быть больше нуля");
        }
        for (CartItem item : items) {
            if (item.getProduct().name().equalsIgnoreCase(product.name())) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        items.add(new CartItem(product, quantity));
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void applyPromo(PromoCode appliedCode) {
        isPromoCodeApplied = true;
        isDiscount = true;
        this.appliedCode = appliedCode;
    }

    public boolean isDiscount() {
        return isDiscount;
    }

    public boolean isPromoCodeApplied() {
        return isPromoCodeApplied;
    }

    public double getDiscountPercent() {
        return appliedCode != null ? appliedCode.getDiscount() : 0;
    }
}