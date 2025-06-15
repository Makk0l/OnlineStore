package cli;

import enums.Commands;
import models.CartItem;
import models.Product;
import repositories.InMemoryPromoCodeRepository;
import repositories.PromoCodeRepository;
import services.StoreService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StoreApp {
    public static void main(String[] args) {
        List<Product> catalog = new ArrayList<>();
        PromoCodeRepository promoCodeRepository = new InMemoryPromoCodeRepository();
        catalog.add(new Product("Футболка", 1000));
        catalog.add(new Product("Книга", 500));
        catalog.add(new Product("Кружка", 300));

        StoreService store = new StoreService(catalog, promoCodeRepository);
        Scanner scanner = new Scanner(System.in);

        boolean running = true;
        while (running) {
            try {
                System.out.println("""
                        \n
                        Пиши \'catalog\' что бы посмотреть каталог
                        Пиши \'add\' что бы добавить товар в корзину
                        Пиши \'discount\' что бы применить скидку
                        Пиши \'list\' что бы показать корзину
                        Пиши \'promo\' что использовать промокод
                        Пиши \'exit\' что бы выйти
                        """);
                String choice = scanner.nextLine();
                switch (Commands.valueOf(choice.toUpperCase())) {
                    case Commands.CATALOG -> store.showCatalog();
                    case Commands.ADD -> {
                        System.out.print("Введите название товара: ");
                        String name = scanner.nextLine();
                        System.out.print("Введите количество: ");
                        int quantity = Integer.parseInt(scanner.nextLine());
                        store.addProductToCart(name, quantity);
                    }
                    case Commands.DISCOUNT -> {
                        System.out.print("Введите процент скидки: ");
                        double percent = Double.parseDouble(scanner.nextLine());
                        store.applyManualDiscount(percent);
                    }
                    case PROMO -> {
                        System.out.println("Введите промокод");
                        String percent = scanner.nextLine();
                        store.applyPromoCode(percent);
                    }
                    case Commands.LIST -> {
                        List<CartItem> cart = store.getCartItems();
                        for (CartItem item : cart) {
                            System.out.println(item.getProduct().name() + " x" + item.getQuantity() + " = " + item.getTotalPrice());
                        }
                        System.out.println("Итого со скидкой: " + store.calculateTotal());
                    }
                    case Commands.EXIT -> running = false;
                    default -> System.out.println("Неверный ввод.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Необработанная ошибка");
            }

        }
        scanner.close();
        System.out.println("Спасибо за покупки!");
    }
}