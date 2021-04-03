package com.asajayan.user.infrastructure.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER_TBL")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Size(
            min = 5,
            message = "Username must be of minimum 5 characters"
    )
    private String userName;
    @NotNull
    @Size(
            min = 8,
            message = "Password must be of minimum 8 characters"
    )
    private String password;
    @NotNull
    private String email;
}
