package com.asajayan.user.rest.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsBean {
    private Long id;
    private String userName;
    private String email;
    private String token;
}
