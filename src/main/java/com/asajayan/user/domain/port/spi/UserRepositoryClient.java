package com.asajayan.user.domain.port.spi;

import com.asajayan.user.domain.model.response.UserDetailsBO;
import com.asajayan.user.infrastructure.model.request.UserEntity;

public interface UserRepositoryClient {
    Object registerUser(UserEntity userEntity);

    UserDetailsBO fetchUserDetails(Long userId);

    boolean isExistingUser(String userName);

    boolean isExistingMail(String email);
}
