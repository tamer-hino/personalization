package com.personalization.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "product_metadata",schema = "public",indexes = {
        @Index(name = "idx_category_id", columnList = "category"),
        @Index(name = "idx_brand_updated", columnList = "brand")
})
@Data
@NoArgsConstructor
public class ProductMetadata {

    @Id
    @Column(name = "product_id", nullable = false)
    private String productId;

    @Column(name = "category")
    private String category;

    @Column(name = "brand")
    private String brand;

}

