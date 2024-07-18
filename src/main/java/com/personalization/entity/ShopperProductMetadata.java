package com.personalization.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "shopper_product_metadata",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"shopper_id", "product_id"})},schema = "public")
@Data
public class ShopperProductMetadata {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "shopper_id", nullable = false)
    private Shopper shopper;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductMetadata productMetadata;

    @Column(name = "relevancy_score")
    private Double relevancyScore;


}
