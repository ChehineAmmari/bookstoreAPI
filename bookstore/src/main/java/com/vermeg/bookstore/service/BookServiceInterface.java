package com.vermeg.bookstore.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.vermeg.bookstore.model.Book;

public interface BookServiceInterface {
	
	public List<Book> getAllBooks();
	
	@Transactional
	public Book getBookByISBN(long ISBN);
	
	@Transactional
	public boolean add(Book book);
	
	@Transactional
	public boolean update(Book book);
	
	@Transactional
	public boolean delete(long ISBN);
	
	public double calculate(double price, int qte);
	
	@Transactional
	public double calculateAll(List<Long> ISBNs);
}
