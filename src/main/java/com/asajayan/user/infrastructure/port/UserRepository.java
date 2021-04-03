package com.asajayan.user.infrastructure.port;

import com.asajayan.user.infrastructure.model.request.UserEntity;
import com.asajayan.user.infrastructure.utils.Queries;
import org.bouncycastle.cms.RecipientInformationStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = Queries.findUserbyIdQuery, nativeQuery = true)
    UserEntity findByUserId(Long userId);


    @Query(value = Queries.findByMailIdQuery, nativeQuery = true)
    List<UserEntity> findByMailId(String email);


    @Query(value = Queries.findByUserNameQuery, nativeQuery = true)
    List<UserEntity> findByUserName(String userName);
}
