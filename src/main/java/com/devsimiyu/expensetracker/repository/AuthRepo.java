package com.devsimiyu.expensetracker.repository;

import com.devsimiyu.expensetracker.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepo extends JpaRepository<User, Integer> {
    
    @Query(value = "SELECT * FROM userfindbyemail(:email)", nativeQuery = true)
    User findUserByEmail(@Param("email") String email);
}
