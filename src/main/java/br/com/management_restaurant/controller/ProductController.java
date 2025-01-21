package br.com.management_restaurant.controller;

import br.com.management_restaurant.dto.request.ProductRequest;
import br.com.management_restaurant.entity.ProductEntity;
import br.com.management_restaurant.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ObjectMapper objectMapper;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMINISTRADOR')")
    public URI createProduct(@RequestBody ProductRequest request){

        var product = productService.createProduct(objectMapper.convertValue(request, ProductEntity.class));

        return ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(product.getId()).toUri();
    }
}
