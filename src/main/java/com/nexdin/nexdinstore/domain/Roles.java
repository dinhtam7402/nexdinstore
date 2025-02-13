package com.nexdin.nexdinstore.domain;

import com.nexdin.nexdinstore.domain.enums.ERole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roles")
public class Roles {
    @Id
    private String roleID;
    @Enumerated(EnumType.STRING)
    private ERole name;
}
