package com.asajayan.user.infrastructure.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsEntity {
    private Long id;
    private String userName;
    private String email;
}
