package com.asajayan.user.domain.port.api;

import com.asajayan.user.domain.model.request.UserBO;
import com.asajayan.user.rest.model.response.UserDetailsBean;

public interface CustomUserDetailsService {

    UserDetailsBean registerUser(UserBO userBO);

    UserDetailsBean fetchUserDetails(Long userId);
}
