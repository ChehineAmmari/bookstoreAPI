package com.vermeg.bookstore.dao;

import java.util.List;

import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vermeg.bookstore.model.Book;

@Repository
public class BookDAO implements BookDAOInterface{

	@Autowired
	SessionFactory sessionFactory;
	
	public List<Book> getAllBooks() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Book> books = session.createQuery("from Book").list();	
		return books;
	}
	
	public Book getBookByISBN(long ISBN) {
		Session session = this.sessionFactory.getCurrentSession();
		Book book = (Book) session.get(Book.class, ISBN);
		return book;
	}
	
	public void add(Book book) {
		
		System.out.println(book.toString());
		
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(book);
	}
	
	public void update(Book book) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(book);
	}
	
	public void delete(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		Book b = (Book) session.load(Book.class, id);
		if(b != null)
			session.delete(b);
	}
	
}
