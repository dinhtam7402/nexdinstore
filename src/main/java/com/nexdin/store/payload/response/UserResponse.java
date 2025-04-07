package com.nexdin.store.payload.response;

import com.nexdin.store.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserResponse extends BaseEntity {
    private String username;
    private String password;
}
