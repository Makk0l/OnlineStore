package services;

import models.PromoCode;
import repositories.PromoCodeRepository;

import java.util.HashMap;
import java.util.Map;

public class PromoCodeService {
    PromoCodeRepository promoCodeRepository;

    PromoCodeService(PromoCodeRepository promoCodeRepository) {
        this.promoCodeRepository = promoCodeRepository;
    }

    public PromoCode validate(String code) {
        return promoCodeRepository.findByCode(code);
    }


}
