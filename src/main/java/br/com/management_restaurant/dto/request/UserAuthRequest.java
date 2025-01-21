package br.com.management_restaurant.dto.request;

public record UserAuthRequest(
        String username,
        String password
) {
}
