package br.com.management_restaurant.service;

import br.com.management_restaurant.entity.EmployeeEntity;
import br.com.management_restaurant.entity.RoleEntity;
import br.com.management_restaurant.enuns.RoleEnum;
import br.com.management_restaurant.exception.NotFoundException;
import br.com.management_restaurant.repository.EmployeeRepository;
import br.com.management_restaurant.utils.ValidationCreateEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final ValidationCreateEmployeeService validationCreateEmployeeService;

    @Transactional
    public EmployeeEntity createEmployee(EmployeeEntity employee){

        validationCreateEmployeeService.validationCpfAndEmail(employee);

        employee.getUserEntity().setPassword(passwordEncoder.encode(employee.getUserEntity().getPassword()));
        employee.getUserEntity().setRoles(Set.of(new RoleEntity(null, RoleEnum.USER)));

        return employeeRepository.save(employee);
    }

    public EmployeeEntity findByName(String username){
        return employeeRepository.findByEmail(username).orElseThrow(() -> {
            throw new NotFoundException("Usuario nao localizado na base de dados. USERNAME:" + username);
        });
    }
}
