package br.com.management_restaurant.dto.request;

import java.util.Date;

public record EmployeeRequest(
        String name,
        String email,
        String cpf,
        String contact,
        Date datebirth,
        UserRequest user
) {
}
