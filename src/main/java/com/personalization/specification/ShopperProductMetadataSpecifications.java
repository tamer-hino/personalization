package com.personalization.specification;

import com.personalization.entity.ShopperProductMetadata;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class ShopperProductMetadataSpecifications {

    public static Specification<ShopperProductMetadata> hasShopperId(String shopperId) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isEmpty(shopperId)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("shopper").get("shopperId"), shopperId);
        };
    }

    public static Specification<ShopperProductMetadata> hasBrand(String brand) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isEmpty(brand)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("productMetadata").get("brand"), brand);
        };
    }

    public static Specification<ShopperProductMetadata> hasCategory(String category) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isEmpty(category)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("productMetadata").get("category"), category);
        };
    }

    public static Specification<ShopperProductMetadata> buildSpecification(String shopperId, String brand, String category) {
        return Specification.where(hasShopperId(shopperId))
                .and(hasBrand(brand))
                .and(hasCategory(category));
    }
}
