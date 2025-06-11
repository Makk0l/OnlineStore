package services;

import models.Cart;
import models.CartItem;
import models.Product;

import java.util.List;

public class StoreService {
    private final List<Product> catalog;
    private final Cart cart;
    private PromoCodeService promoCodeService = new PromoCodeService();

    public StoreService(List<Product> catalog) {
        this.catalog = catalog;
        this.cart = new Cart();
    }

    public void showCatalog() {
        for (Product p : catalog) {
            System.out.println(p.name() + " - " + p.price() + " руб.");
        }
    }

    public void addProductToCart(String name, int quantity) {
        for (Product p : catalog) {
            if (p.name().equalsIgnoreCase(name)) {
                cart.addItem(p, quantity);
                System.out.println("Добавлено: " + name + " x" + quantity);
                return;
            }
        }
        System.out.println("Товар не найден: " + name);
    }

    public void applyDiscount(double percent) {
        if (percent < 0 || percent > 100) {
            System.out.println("Скидка не может быть меньше нуля или больше 100");
        }
        cart.setDiscount(percent);
    }

    public void printCart() {
        for (CartItem item : cart.getItems()) {
            System.out.println(item.getProduct().name() + " x" + item.getQuantity() + " = " + item.getTotalPrice());
        }
        System.out.println("Итого со скидкой: " + calculateTotal());
    }
    public void applyPromoCode(String promo){
        Double discount = promoCodeService.validate(promo);
        if (discount != null){
            cart.applyPromo(discount);
            System.out.println("Промокод " + discount + " применен");
        }
    }

    public double calculateTotal() {
        double total = 0;
        for (CartItem item : cart.getItems()) {
            total += item.getTotalPrice();
        }
        double discountAmount = total * cart.getDiscountPercent() / 100;
        return total - discountAmount;
    }
}