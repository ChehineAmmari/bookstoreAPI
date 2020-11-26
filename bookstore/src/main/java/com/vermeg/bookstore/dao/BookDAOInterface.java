package com.vermeg.bookstore.dao;

import java.util.List;

import com.vermeg.bookstore.model.Book;

public interface BookDAOInterface {
	
	public List<Book> getAllBooks();
	
	public Book getBookByISBN(long ISBN);
	
	public void add(Book book);
	
	public void update(Book book);
	
	public void delete(long id);
	
}
