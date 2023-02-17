package com.skillovilla.booksvillaa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.skillovilla.booksvillaa.model.Books;
import com.skillovilla.booksvillaa.model.UserDto;
import com.skillovilla.booksvillaa.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService uService;

	@PostMapping("/login")
	public ResponseEntity<String> userLoginControll(@RequestBody UserDto uDto) {

		String result = uService.loginUser(uDto);

		return new ResponseEntity<String>(result, HttpStatus.OK);

	}

	@GetMapping("/allBooks/{UniqueId}")
	public ResponseEntity<List<Books>> userLoginControll(@PathVariable String UniqueId) {

		List<Books> result = uService.ShowAllBooks(UniqueId);

		return new ResponseEntity<List<Books>>(result, HttpStatus.OK);

	}

	@GetMapping("/searchByTitle/{UniqueId}/{nameContains}")
	public ResponseEntity<List<Books>> searchByTitle(@PathVariable String UniqueId, @PathVariable String nameContains) {

		List<Books> result = uService.SeachBookByName(nameContains, UniqueId);

		return new ResponseEntity<List<Books>>(result, HttpStatus.OK);

	}

}
