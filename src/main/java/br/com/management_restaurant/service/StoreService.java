package br.com.management_restaurant.service;

import br.com.management_restaurant.entity.StoreEntity;
import br.com.management_restaurant.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    public List<StoreEntity> findAllStore(){
        return storeRepository.findAll();
    }
}
