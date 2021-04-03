package com.asajayan.user.rest.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBean {
    @NotNull
    @Size(
            min = 5
    )
    private String userName;
    @NotNull
    @Size(
            min = 8
    )
    private String password;
    @NotNull
    @Email
    private String email;
}
