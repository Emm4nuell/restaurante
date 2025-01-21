package br.com.management_restaurant.controller;

import br.com.management_restaurant.dto.response.StoreResponse;
import br.com.management_restaurant.service.StoreService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/store/")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;
    private final ObjectMapper objectMapper;

    @GetMapping("stores")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMINISTRADOR')")
    public List<StoreResponse> findAllStore(){
        var stores = storeService.findAllStore();
        var response = stores.stream().map(s -> objectMapper.convertValue(s, StoreResponse.class)).collect(Collectors.toList());
        return response;
    }
}
