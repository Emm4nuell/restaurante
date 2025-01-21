package br.com.management_restaurant.utils;

import br.com.management_restaurant.entity.EmployeeEntity;
import br.com.management_restaurant.exception.ConflictException;
import br.com.management_restaurant.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidationCreateEmployeeService {

    private final EmployeeRepository employeeRepository;

    public void validationCpfAndEmail(EmployeeEntity employee){
        if (employeeRepository.existsByCpf(employee.getCpf())){
            throw new ConflictException("Cpf ja cadastrado na base de dados. CPF: " + employee.getCpf());
        }

        if (employeeRepository.existsByEmail(employee.getEmail())){
            throw new ConflictException("Email ja cadastrado na base de dados. EMAIL: " + employee.getEmail());
        }
    }
}
