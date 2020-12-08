package com.example.demo.user.repository;

import com.example.demo.user.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findByFirstName(String firstName);
}
