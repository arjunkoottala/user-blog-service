package com.asajayan.user.domain.service;

import com.asajayan.user.domain.model.request.UserBO;
import com.asajayan.user.domain.model.response.UserDetailsBO;
import com.asajayan.user.domain.port.api.CustomUserDetailsService;
import com.asajayan.user.domain.port.spi.UserRepositoryClient;
import com.asajayan.user.infrastructure.model.request.UserEntity;
import com.asajayan.user.rest.error.ErrorCode;
import com.asajayan.user.rest.error.UserServiceBusinessException;
import com.asajayan.user.rest.error.UserServiceTechnicalException;
import com.asajayan.user.rest.model.response.UserDetailsBean;
import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;

@Service
@Slf4j
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {
    @Autowired
    Mapper mapper;
    @Autowired
    UserRepositoryClient userRepositoryClient;

    @Override
    public UserDetailsBean registerUser(UserBO userBO) {

        if(userRepositoryClient.isExistingUser(userBO.getUserName()))
            throw new UserServiceBusinessException(ErrorCode.USERID_PRESENT);
        if(userRepositoryClient.isExistingMail(userBO.getEmail()))
            throw new UserServiceBusinessException(ErrorCode.EMAIL_EXIST);
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(userBO.getPassword().getBytes());
            userBO.setPassword(new String(messageDigest.digest()));

            return mapper.map(userRepositoryClient.registerUser(
                    mapper.map(userBO, UserEntity.class)), UserDetailsBean.class);
        } catch (Exception ex) {
            log.error("Exception occurred at registerUser of CustomUserDetailsServiceImpl class ", ex);
            return null;
        }
    }

    @Override
    public UserDetailsBean fetchUserDetails(Long userId) {

        UserDetailsBO userDetailsBO = new UserDetailsBO();
        try {
            userDetailsBO = userRepositoryClient.fetchUserDetails(userId);
        }
        catch (UserServiceBusinessException ex){
            throw ex;
        }
        catch (Exception ex) {
            log.error("Exception occurred while fetching user details in fetchUserDetails of CustomUserDetailsServiceImpl class", ex);
            throw new UserServiceTechnicalException(ErrorCode.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
        return mapper.map(userDetailsBO, UserDetailsBean.class);

    }


}
