package com.nexdin.nexdinstore.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.nexdin.nexdinstore.domain.enums.EProductStatus;
import com.nexdin.nexdinstore.domain.enums.ESize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product_details")
public class ProductDetails {
    @Id
    private String productDetailsID;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @JsonBackReference
    private Products product;
    @Enumerated(EnumType.STRING)
    private ESize size;
    private String color;
    private int soldQuantity;
    private int stockQuantity;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime updatedAt;
    @Enumerated(EnumType.STRING)
    private EProductStatus status;
}
