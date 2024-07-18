package com.personalization.repository;

import com.personalization.entity.Shopper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ShopperRepository extends JpaRepository<Shopper, Long> {
    @Query("SELECT spm FROM Shopper spm WHERE spm.shopperId = :shopperId")
    Shopper findByShopperId(@Param("shopperId") String shopperId);
}
