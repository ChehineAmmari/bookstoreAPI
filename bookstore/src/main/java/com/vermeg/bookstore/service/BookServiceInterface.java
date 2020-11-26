package com.vermeg.bookstore.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.vermeg.bookstore.model.Book;

public interface BookServiceInterface {
	
	public List<Book> getAllBooks();
	
	@Transactional
	public Book getBookByISBN(long ISBN);
	
	@Transactional
	public void add(Book book);
	
	@Transactional
	public void update(Book book);
	
	@Transactional
	public void delete(long ISBN);
	
	public double calculate(double price, int qte);
	
	@Transactional
	public double calculateAll(List<Long> ISBNs);
}
