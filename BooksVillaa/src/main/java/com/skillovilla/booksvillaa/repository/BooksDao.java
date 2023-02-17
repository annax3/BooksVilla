package com.skillovilla.booksvillaa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillovilla.booksvillaa.model.Books;
@Repository
public interface BooksDao  extends JpaRepository<Books, Integer>{
	public List<Books> findBytitleContains(String nameContains);
	
}
