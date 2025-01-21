package br.com.management_restaurant.controller;

import br.com.management_restaurant.dto.response.EmployeeNameResponse;
import br.com.management_restaurant.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final ObjectMapper objectMapper;

    @GetMapping("/username")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMINISTRADOR')")
    public EmployeeNameResponse employeeName(@RequestParam String username){
        var employee = employeeService.findByName(username);
        var user =  objectMapper.convertValue(employee, EmployeeNameResponse.class);
        return user;
    }
}
