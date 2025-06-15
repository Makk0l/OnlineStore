package repositories;

import models.PromoCode;

public interface PromoCodeRepository {
    PromoCode findByCode(String code);
}
