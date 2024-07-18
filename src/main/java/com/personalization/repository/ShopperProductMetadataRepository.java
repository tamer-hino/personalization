package com.personalization.repository;

import com.personalization.entity.ShopperProductMetadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.CompletableFuture;

public interface ShopperProductMetadataRepository extends JpaRepository<ShopperProductMetadata, Long>, JpaSpecificationExecutor<ShopperProductMetadata> {
    @Async
    @Query("SELECT spm FROM ShopperProductMetadata spm WHERE spm.productMetadata.productId = :productId")
    CompletableFuture<ShopperProductMetadata> findByProductId(@Param("productId") String productId);
}
