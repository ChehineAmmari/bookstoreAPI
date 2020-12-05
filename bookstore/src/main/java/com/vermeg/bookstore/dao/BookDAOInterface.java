package com.vermeg.bookstore.dao;

import java.util.List;

import com.vermeg.bookstore.model.Book;

public interface BookDAOInterface {
	
	public List<Book> getAllBooks();
	
	public Book getBookByISBN(long ISBN);
	
	public boolean add(Book book);
	
	public boolean update(Book book);
	
	public boolean delete(long id);
	
}
