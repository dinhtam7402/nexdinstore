package com.nexdin.store.entity;

import com.nexdin.store.entity.enums.ShippingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Shipping extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "provider_id", nullable = false)
    private Provider provider;

    @Column(nullable = false)
    private String trackingNumber;

    private Integer fee;

    @Enumerated(EnumType.STRING)
    private ShippingStatus status;
}
