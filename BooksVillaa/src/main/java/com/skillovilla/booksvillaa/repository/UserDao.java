package com.skillovilla.booksvillaa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillovilla.booksvillaa.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
	public User findByEmailAndPassword(String email, String password);
}
