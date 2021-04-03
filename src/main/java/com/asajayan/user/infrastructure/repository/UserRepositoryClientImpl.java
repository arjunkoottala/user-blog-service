package com.asajayan.user.infrastructure.repository;

import com.asajayan.user.domain.model.request.UserBO;
import com.asajayan.user.domain.model.response.UserDetailsBO;
import com.asajayan.user.domain.port.spi.UserRepositoryClient;
import com.asajayan.user.infrastructure.model.request.UserEntity;
import com.asajayan.user.infrastructure.model.response.UserDetailsEntity;
import com.asajayan.user.infrastructure.port.UserRepository;
import com.asajayan.user.rest.error.ErrorCode;
import com.asajayan.user.rest.error.UserServiceBusinessException;
import com.asajayan.user.rest.error.UserServiceTechnicalException;
import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class UserRepositoryClientImpl implements UserRepositoryClient {

    @Autowired
    Mapper mapper;

    @Autowired
    UserRepository userRepository;


    @Override
    public Object registerUser(UserEntity userEntity) {
        try {
            userEntity = userRepository.save(userEntity);
            return mapper.map(userEntity, UserBO.class);
        }catch (Exception e){
            log.error("Unable to register a user", e);
            throw new UserServiceTechnicalException(ErrorCode.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @Override
    public UserDetailsBO fetchUserDetails(Long userId) {
        UserEntity userEntity = userRepository.findByUserId(userId);
        UserDetailsEntity userDetailsEntity = new UserDetailsEntity();
        if(userDetailsEntity.getUserName()==null)
            throw new UserServiceBusinessException(ErrorCode.INVALID_USER_ID);
        userDetailsEntity.setUserName(userEntity.getUserName());
        userDetailsEntity.setEmail(userEntity.getEmail());
        userDetailsEntity.setId(userEntity.getId());
        return mapper.map(userDetailsEntity, UserDetailsBO.class);
    }

    @Override
    public boolean isExistingMail(String email) {
        try {
            log.info("Inside isExistingMail function");
            boolean bol = userRepository.findByMailId(email).size()>0;
            return bol;
        }catch (Exception e){
            log.error("Exception while fetching user info", e);
            return false;
        }
    }

    @Override
    public boolean isExistingUser(String userName) {
        try {
            log.info("Inside isExistingMail function");
            boolean bol = userRepository.findByUserName(userName).size()>0;
            return bol;
        }catch (Exception e){
            log.error("Exception while fetching user info", e);
            return false;
        }
    }
}
