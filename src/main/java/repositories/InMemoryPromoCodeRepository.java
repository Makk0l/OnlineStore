package repositories;

import models.PromoCode;

import java.util.HashMap;
import java.util.Map;

public class InMemoryPromoCodeRepository implements PromoCodeRepository {
    private final Map<String, PromoCode> codes = new HashMap<>();

    public InMemoryPromoCodeRepository() {
        codes.put("WELCOME10", new PromoCode("WELCOME10", 10));
        codes.put("VIP50", new PromoCode("VIP50", 50));

    }

    @Override
    public PromoCode findByCode(String code) {
        return codes.get(code.toUpperCase());
    }
}
