package com.devsimiyu.expensetracker.controller;

import javax.validation.Valid;
import com.devsimiyu.expensetracker.model.LoginForm;
import com.devsimiyu.expensetracker.model.User;
import com.devsimiyu.expensetracker.security.SecurityUserDetails;
import com.devsimiyu.expensetracker.service.AuthService;
import com.devsimiyu.expensetracker.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtil jwtUtil;
    
    @PostMapping(
        value = "/login",
        consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<String> login(
        @Valid
        @RequestBody
        LoginForm loginForm
    ) {
        User user = authService.findUserByEmail(loginForm.getEmail());

        if (user == null) {
            return new ResponseEntity<String>(
                "No user found with matching email", 
                HttpStatus.NOT_FOUND
            );

        } else if (!passwordEncoder.matches(loginForm.getPassword(), user.getPassword())) {
            return new ResponseEntity<String>(
                "No user found with matching password", 
                HttpStatus.NOT_FOUND
            );
        }

        SecurityUserDetails securityUserDetails = new SecurityUserDetails(user);
        String token = jwtUtil.generateToken(securityUserDetails);

        return ResponseEntity.ok(token);
    }
}
