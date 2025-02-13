package com.nexdin.nexdinstore.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class Products {
    @Id
    private String productID;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonBackReference
    private Categories category;
    @OneToMany(mappedBy = "product")
    @JsonManagedReference
    private List<ProductDetails> productDetails;
    private String productName;
    private String description;
    private String material;
    private int importPrice;
    private int sellingPrice;
    private String imageUrl;

    @OneToMany(mappedBy = "product")
    @JsonManagedReference
    private List<OrderItems> orderItems;
}
