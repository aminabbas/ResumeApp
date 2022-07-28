package com.mycompany.dao.impl;

import com.mycompany.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByName(String name);
//    @Query(value = "select * from user where email = ?",nativeQuery = true)
    @Query("select u from User u where u.email = :email")
    User findByEmail(@Param("email") String email);
}
