package br.com.management_restaurant.controller;

import br.com.management_restaurant.dto.request.EmployeeRequest;
import br.com.management_restaurant.dto.request.UserAuthRequest;
import br.com.management_restaurant.dto.response.UserTokenResponse;
import br.com.management_restaurant.entity.EmployeeEntity;
import br.com.management_restaurant.entity.UserEntity;
import br.com.management_restaurant.service.EmployeeService;
import br.com.management_restaurant.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/auth/")
@RequiredArgsConstructor
public class UserController {

    private final EmployeeService employeeService;
    private final UserService userService;
    private final ObjectMapper objectMapper;

    @PostMapping("signin")
    @ResponseStatus(HttpStatus.OK)
    public UserTokenResponse auth(@RequestBody UserAuthRequest userAuth){
        return userService.signin(userAuth);
    }

    @PostMapping("signup")
    @ResponseStatus(HttpStatus.CREATED)
    public URI createUser(@RequestBody EmployeeRequest request){
        var employee = objectMapper.convertValue(request, EmployeeEntity.class);
        var user = objectMapper.convertValue(request.user(), UserEntity.class);
        employee.setUserEntity(user);
        var response = employeeService.createEmployee(employee);
        return ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
    }
}
