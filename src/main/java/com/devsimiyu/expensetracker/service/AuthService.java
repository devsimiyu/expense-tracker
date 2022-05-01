package com.devsimiyu.expensetracker.service;

import com.devsimiyu.expensetracker.model.User;
import com.devsimiyu.expensetracker.repository.AuthRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    AuthRepo authRepo;
    
    public User findUserByEmail(String email) {
        return authRepo.findUserByEmail(email);
    }
}
