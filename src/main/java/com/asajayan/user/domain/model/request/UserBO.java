package com.asajayan.user.domain.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBO {
    private int id;
    private String userName;
    private String password;
    private String email;
}
