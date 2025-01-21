package br.com.management_restaurant.repository;

import br.com.management_restaurant.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    Optional<EmployeeEntity> findByEmail(String email);

    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);
}
