package com.skillovilla.booksvillaa.service;

import java.awt.print.Book;
import java.lang.StackWalker.Option;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillovilla.booksvillaa.exception.BooksException;
import com.skillovilla.booksvillaa.exception.UserException;
import com.skillovilla.booksvillaa.model.Books;
import com.skillovilla.booksvillaa.model.User;
import com.skillovilla.booksvillaa.model.UserDto;
import com.skillovilla.booksvillaa.model.UserSession;
import com.skillovilla.booksvillaa.repository.BooksDao;
import com.skillovilla.booksvillaa.repository.UserDao;
import com.skillovilla.booksvillaa.repository.UserSessionDao;

import net.bytebuddy.utility.RandomString;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao uDao;

	@Autowired
	UserSessionDao uSDao;

	@Autowired
	BooksDao bDao;

	@Override
	public String loginUser(UserDto userDto) {
		User existingUser = uDao.findByEmailAndPassword(userDto.getEmail(), userDto.getPassword());

		if (existingUser != null) {

			UserSession session = new UserSession();
			session.setTime(LocalTime.now());
			session.setUniqueId(RandomString.make(8));
			return uSDao.save(session) + "";

		} else {
			throw new UserException("Wronng credentials");
		}
	}

	@Override
	public List<Books> ShowAllBooks(String uniqueId) {
		UserSession uSession = uSDao.findByUniqueId(uniqueId);
		if (uSession != null) {
			List<Books> books = bDao.findAll();

			if (books.size() != 0) {
				return books;
			} else {
				throw new BooksException("No books in database");
			}

		} else {
			throw new UserException("Login first");
		}

	}

	@Override
	public List<Books> SeachBookByName(String nameContains, String uniqueId) {
		UserSession uSession = uSDao.findByUniqueId(uniqueId);
		if (uSession != null) {
			List<Books> books = bDao.findBytitleContains(nameContains);

			if (books.size() != 0) {
				return books;
			} else {
				throw new BooksException("No books in database");
			}

		} else {
			throw new UserException("Login first");
		}
	}

	@Override
	public Books borrowOneBook(Integer bookId, String uniqueId) {
		UserSession uSession = uSDao.findByUniqueId(uniqueId);
		if (uSession != null) {

			Optional<Books> opt = bDao.findById(bookId);

			if (opt.isPresent()) {

				Books book = opt.get();

				if (book.getAvailablecopies() > 0) {

					User user = uDao.findById(uSession.getCustomerId()).get();

					int alredayBorrowed = user.getList().size();

					if (alredayBorrowed <= 4) {
						user.getList().add(book);
						int newAvailable = book.getAvailablecopies() - 1;
						book.setAvailablecopies(newAvailable);
						bDao.save(book);

						return book;
					} else {
						throw new BooksException("Already have 5 books borrowed");
					}

				} else {
					throw new BooksException("No copies of selected book is present in the library");
				}

			} else {
				throw new BooksException("No book found with this id");
			}

		} else {
			throw new UserException("Login first");
		}

	}

}
