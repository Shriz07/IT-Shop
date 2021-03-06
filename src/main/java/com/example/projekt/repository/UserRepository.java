package com.example.projekt.repository;

import com.example.projekt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long>
{
    @Query("SELECT u FROM User u WHERE u.email = ?1")
    User findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.id = ?1")
    User findById(Integer id);
}
