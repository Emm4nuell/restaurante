package br.com.management_restaurant.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long cod;
    private String nameproduct;
    private String descricao;
    private String category;
    private BigDecimal price;
    private int amount;
    private boolean status;

    @ManyToOne()
    @JoinColumn(name = "employe_id")
    private EmployeeEntity employee;
}
