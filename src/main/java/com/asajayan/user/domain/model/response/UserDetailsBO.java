package com.asajayan.user.domain.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsBO {
    private Long id;
    private String userName;
    private String password;
    private String email;
}
