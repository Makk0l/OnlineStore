package services;

import java.util.HashMap;
import java.util.Map;

public class PromoCodeService {
   private final Map<String, Double> promoCodes = new HashMap<>();
   private boolean used = false;

   PromoCodeService(){
       promoCodes.put("WELCOME10", 10.0);
       promoCodes.put("VIP50", 50.0);
   }
   public Double validate (String promo){
       if (used){
           System.out.println("Промокод уже использован");
           return null;
       }
       Double discount = promoCodes.get(promo.toUpperCase());
       if (discount != null){
           used = true;
           System.out.println("Промокод ".concat(promo.toUpperCase()).concat(" использован"));
           return discount;
       }
       System.out.println("Промокод недействителен");
       return null;
   }
}
