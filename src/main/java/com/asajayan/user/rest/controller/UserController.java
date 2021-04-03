package com.asajayan.user.rest.controller;

import com.asajayan.user.domain.model.request.UserBO;
import com.asajayan.user.domain.port.api.CustomUserDetailsService;
import com.asajayan.user.rest.model.request.UserBean;
import com.asajayan.user.rest.model.response.UserDetailsBean;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    Mapper mapper;


    @Value("${jwt.secret.key}")
    private String secret;

    @PostMapping("/register")
    public ResponseEntity<UserDetailsBean> registerUser(@RequestBody @Valid UserBean userBean) {
        UserDetailsBean userDetailsBean = customUserDetailsService.registerUser(mapper.map(userBean, UserBO.class));
        userDetailsBean.setToken(generateToken(userBean.getUserName()));
        return new ResponseEntity<>(userDetailsBean, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDetailsBean> fetchUserDetails(@PathVariable("id") Long userId) {
        log.info("Inside fetchUserDetails function");
        UserDetailsBean userDetailsBean = customUserDetailsService.fetchUserDetails(userId);
        userDetailsBean.setToken(generateToken(userDetailsBean.getUserName()));
        return new ResponseEntity<>(userDetailsBean, HttpStatus.OK);
    }
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }


}
