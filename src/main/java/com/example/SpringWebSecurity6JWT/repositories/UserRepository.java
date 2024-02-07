package com.example.SpringWebSecurity6JWT.repositories;

import com.example.SpringWebSecurity6JWT.models.Role;
import com.example.SpringWebSecurity6JWT.models.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, Long> {
    Optional<User> findByEmail(String email);
    User findByRole (Role role);
}
