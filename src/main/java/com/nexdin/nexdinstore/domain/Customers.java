package com.nexdin.nexdinstore.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Table(name = "customers")
public class Customers {
    @Id
    private String customerID;
    private String fullName;
    private String email;
    private String phone;
    private String address;

    @OneToMany(mappedBy = "customer")
    @JsonManagedReference
    private List<Orders> orders;
}
