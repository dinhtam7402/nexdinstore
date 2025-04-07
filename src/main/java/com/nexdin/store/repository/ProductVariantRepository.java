package com.nexdin.store.repository;

import com.nexdin.store.entity.ProductVariant;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductVariantRepository extends JpaRepository<ProductVariant, Integer> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT pv FROM ProductVariant pv WHERE pv.id = :productId")
    ProductVariant findAndLockById(@Param("productId") Integer productId);
}
