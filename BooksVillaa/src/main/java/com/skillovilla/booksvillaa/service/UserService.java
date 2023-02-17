package com.skillovilla.booksvillaa.service;

import java.awt.print.Book;
import java.util.List;

import org.springframework.stereotype.Service;

import com.skillovilla.booksvillaa.model.Books;
import com.skillovilla.booksvillaa.model.User;
import com.skillovilla.booksvillaa.model.UserDto;

@Service
public interface UserService {
	public String loginUser(UserDto userDto);

	public List<Books> ShowAllBooks(String uniqueId);

	public List<Books> SeachBookByName(String nameContains, String uniqueId);

	public Books borrowOneBook(Integer bookId, String uniqueId);

}
