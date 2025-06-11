package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Cart {
    private final List<CartItem> items = new ArrayList<>();
    private double discountPercent = 0.0;
    private boolean promo = false;


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

    public void applyPromo(double percent) {
        this.discountPercent = Math.min(percent, 100);
        this.promo = true;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscount(double percent) {
        if (!promo) {
            this.discountPercent = Math.min(percent, 100);
        }
        else {
            System.out.println("Промокод уже применен, скидка не действует");
        }
    }
}