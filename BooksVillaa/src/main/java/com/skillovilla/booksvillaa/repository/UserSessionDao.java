package com.skillovilla.booksvillaa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillovilla.booksvillaa.model.UserSession;

@Repository
public interface UserSessionDao extends JpaRepository<UserSession, Integer> {
	public UserSession findByUniqueId(String uniqueId);
}
