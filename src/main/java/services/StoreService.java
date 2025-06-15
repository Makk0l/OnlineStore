package services;

import models.Cart;
import models.CartItem;
import models.Product;
import models.PromoCode;
import repositories.PromoCodeRepository;

import java.util.List;

public class StoreService {
    private final List<Product> catalog;
    private final Cart cart;
    private final PromoCodeService promoCodeService;

    public StoreService(List<Product> catalog, PromoCodeRepository promoCodeRepository) {
        this.catalog = catalog;
        this.cart = new Cart();
        promoCodeService = new PromoCodeService(promoCodeRepository);
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

    public void applyManualDiscount(double percent) {
        if (cart.isPromoCodeApplied() || cart.isDiscount()) {
            System.out.println("Сначала удалите текущую скидку или промокод.");
            return;
        }
        double valid = Math.min(percent, 100);
        cart.applyPromo(new PromoCode("MANUAL", valid));
        System.out.println("Скидка в " + valid + "% применена");
    }


    public void applyPromoCode(String promo) {
        PromoCode promoCode = promoCodeService.validate(promo);
        if (promoCode != null && !cart.isPromoCodeApplied()) {
            cart.applyPromo(promoCode);
            System.out.println("Промокод применен");
        } else {
            System.out.println("Промокод недействителен");
        }
    }

    public List<CartItem> getCartItems() {
        return cart.getItems();
    }

    public double calculateTotal() {
        double total = 0;
        for (CartItem item : cart.getItems()) {
            total += item.getTotalPrice();
        }
        double discountAmount = total * cart.getDiscountPercent() / 100;
        return Math.max(0, total - discountAmount);
    }
}