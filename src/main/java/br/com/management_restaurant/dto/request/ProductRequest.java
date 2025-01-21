package br.com.management_restaurant.dto.request;

import java.math.BigDecimal;

public record ProductRequest(
        String nameproduct,
        Long cod,
        String nameprocuct,
        String descricao,
        String category,
        BigDecimal price,
        int amount,
        boolean status
) {
}
