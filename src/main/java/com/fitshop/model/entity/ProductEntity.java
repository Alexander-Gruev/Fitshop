package com.fitshop.model.entity;

import com.fitshop.enums.ProductCategoryEnum;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "products")
public class ProductEntity extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private String brandName;

    @Enumerated(EnumType.STRING)
    private ProductCategoryEnum category;

    @Lob
    @Column(nullable = false, length = 1000)
    private String description;

    @Column(nullable = false)
    private String imageUrl;

    @Column
    private Instant created;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private OrderEntity order;

    private boolean isOrdered;

    @PrePersist
    public void beforeCreate() {
        this.created = Instant.now();
    }
}
