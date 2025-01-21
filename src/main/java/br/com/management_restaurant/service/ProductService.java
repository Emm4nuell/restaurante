package br.com.management_restaurant.service;

import br.com.management_restaurant.entity.ProductEntity;
import br.com.management_restaurant.exception.NotFoundException;
import br.com.management_restaurant.repository.EmployeeRepository;
import br.com.management_restaurant.repository.ProductRepository;
import br.com.management_restaurant.security.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final EmployeeRepository employeeRepository;

    @Transactional
    public ProductEntity createProduct(ProductEntity productEntity){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userDetails = (User) authentication.getPrincipal();

        Long userId = userDetails.getId();

        var employee = employeeRepository.findById(userId).orElseThrow(() ->
                new NotFoundException("Funcionario nao localizado na base de dados. ID: " + userId));

        productEntity.setEmployee(employee);

        return productRepository.save(productEntity);
    }
}
