package com.devsimiyu.expensetracker.security;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.devsimiyu.expensetracker.model.User;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityUserDetailsService {

    @PersistenceContext
    EntityManager entityManager;
    
    public SecurityUserDetails findByUsername(String username) {
        Object user = entityManager
            .createNativeQuery("""
                SELECT *
                FROM users 
                WHERE email = :email   
            """,
            User.class)
            .setParameter("email", username)
            .getSingleResult();

        if (user == null) throw new UsernameNotFoundException(
            String.format("User: %s, not found", username)
        );

        return new SecurityUserDetails((User) user);
    }
}
