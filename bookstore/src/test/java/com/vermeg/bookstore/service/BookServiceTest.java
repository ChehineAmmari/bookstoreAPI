package com.vermeg.bookstore.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.vermeg.bookstore.dao.BookDAOInterface;
import com.vermeg.bookstore.model.Book;

public class BookServiceTest {
	
	@Mock
	BookDAOInterface bookDAO;
	
	@InjectMocks
	BookService bookService;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetAllBooks() {
		
		List<Book> books = new ArrayList<Book>();
		Book b1 = new Book(5050,"azerty",70,null,"author name1");
		Book b2 = new Book(5040,"qwerty",90,null,"author name2");
		books.add(b1);
		books.add(b2);
		
		when(bookDAO.getAllBooks()).thenReturn(books);
		
		assertTrue(bookService.getAllBooks().size() == books.size());
		
	}

	@Test
	public void testGetBookByISBN() {

		Book b1 = new Book(5050,"azerty",70,null,"author name1");
		
		when(bookDAO.getBookByISBN(5050L)).thenReturn(b1);
		
		assertTrue(bookService.getBookByISBN(5050L).getIsbn() == b1.getIsbn());
	}

	@Test
	public void testAdd() {
		
		Book b1 = new Book(5050,"azerty",70,null,"author name1");
		
		when(bookDAO.add(b1)).thenReturn(true);
		
		assertTrue(bookService.add(b1));
	}

	@Test
	public void testUpdate() {
		
		Book b1 = new Book(5050,"azerty",70,null,"author name1");
		
		when(bookDAO.update(b1)).thenReturn(true);
		
		assertTrue(bookService.update(b1));
	}

	@Test
	public void testDelete() {
		
		when(bookDAO.delete(5050)).thenReturn(true);
		
		assertTrue(bookService.delete(5050));
	}

	@Test
	public void testCalculate() {
		assertTrue(bookService.calculate(70, 2) == 140);
	}

	@Test
	public void testCalculateAll() {
		
		Book b1 = new Book(5050,"azerty",70,null,"author name1");
		Book b2 = new Book(5040,"qwerty",90,null,"author name2");
		
		ArrayList<Long> ISBNs = new ArrayList<Long>();
		ISBNs.add(5050L);
		ISBNs.add(5040L);
		
		when(bookDAO.getBookByISBN(5050)).thenReturn(b1);
		when(bookDAO.getBookByISBN(5040)).thenReturn(b2);
		
		assertTrue(bookService.calculateAll(ISBNs) == 160);
	}

}