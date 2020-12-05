package com.vermeg.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vermeg.bookstore.dao.BookDAOInterface;
import com.vermeg.bookstore.model.Book;

@Service
public class BookService implements BookServiceInterface {

	@Autowired
	BookDAOInterface bookDAO;
	
	@Transactional
	public List<Book> getAllBooks() {
		return bookDAO.getAllBooks();
	}
	
	@Transactional
	public Book getBookByISBN(long ISBN) {
		return bookDAO.getBookByISBN(ISBN);
	}
	
	@Transactional
	public boolean add(Book book) {
		bookDAO.add(book);
		return true;
	}
	
	@Transactional
	public boolean update(Book book) {
		bookDAO.update(book);
		return true;
	}
	
	@Transactional
	public boolean delete(long ISBN) {
		bookDAO.delete(ISBN);
		return true;
	}
	
	public double calculate(double price, int qte) {
		return price*qte;
	}
	
	@Transactional
	public double calculateAll(List<Long> ISBNs) {
		double s = 0;
		for(long ISBN: ISBNs) 
			s += getBookByISBN(ISBN).getPrice();
		return s;
		
	}

} 
