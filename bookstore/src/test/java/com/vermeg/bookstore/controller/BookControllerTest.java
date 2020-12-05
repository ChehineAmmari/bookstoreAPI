package com.vermeg.bookstore.controller;

import static org.mockito.Matchers.refEq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;


import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vermeg.bookstore.model.Book;
import com.vermeg.bookstore.service.BookServiceInterface;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class BookControllerTest {
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private BookController bookController;
	
	@Mock
	private BookServiceInterface service;
	
	ObjectMapper om;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
		om = new ObjectMapper();
	}
	
	@Test
	public void getBookByISBNTest() throws Exception {
		
		List<Book> books = new ArrayList<Book>();
		Book b1 = new Book(5050,"azerty",70,null,"author name1");
		Book b2 = new Book(5040,"qwerty",90,null,"author name2");
		books.add(b1);
		books.add(b2);
		
		when(service.getBookByISBN(5050)).thenReturn(b1);
		
		mockMvc.perform(get("/api/book/get/{ISBN}",5050L))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.*",hasSize(5)))
				.andDo(print());
	}
	
	@Test
	public void getAllBooksTest() throws Exception {
		List<Book> books = new ArrayList<Book>();
		Book b1 = new Book(5050,"azerty",70,null,"author name1");
		Book b2 = new Book(5040,"qwerty",90,null,"author name2");
		books.add(b1);
		books.add(b2);
		
		when(service.getAllBooks()).thenReturn(books);
		
		mockMvc.perform(get("/api/book/getAll")).andExpect(status().isOk()).andExpect(jsonPath("$",hasSize(2)));
	}
	
	@Test
	public void addTest() throws Exception {
		Book book = new Book(5050,"azerty",70,null,"author name1");
		String jsonBody = om.writeValueAsString(book);
		
		doNothing().when(service).add(book);
		
		mockMvc.perform(post("/api/book/add").content(jsonBody).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk())
				.andDo(print());
		
		verify(service,times(1)).add(refEq(book));
		
	}
	
	@Test
	public void updateTest() throws Exception {
		Book book = new Book(5050,"azerty",70,null,"author name3");
		String jsonBody = om.writeValueAsString(book);
		
		doNothing().when(service).update(book);
		
		mockMvc.perform(post("/api/book/update").content(jsonBody).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk())
				.andDo(print());
		
		verify(service,times(1)).update(refEq(book));
		
	}
	
	@Test
	public void deleteTest() throws Exception {
		List<Book> books = new ArrayList<Book>();
		Book b1 = new Book(5050,"azerty",70,null,"author name1");
		Book b2 = new Book(5040,"qwerty",90,null,"author name2");
		books.add(b1);
		books.add(b2);
		
		mockMvc.perform(get("/api/book/delete").param("ISBN", "5050"))
				.andExpect(status().isOk())
				.andDo(print());
		
	}
	
	@Test
	public void calculateTest() throws Exception {
		
		Book b1 = new Book(5050,"azerty",70,null,"author name1");
		Book b2 = new Book(5040,"qwerty",90,null,"author name2");
		
		when(service.getBookByISBN(5050)).thenReturn(b1);
		when(service.getBookByISBN(5040)).thenReturn(b2);
		when(service.calculate(70, 2)).thenReturn(140d);
		when(service.calculate(90, 3)).thenReturn(270d);
		
		mockMvc.perform(get("/api/book/calculate")
				.param("ISBN", "5050")
				.param("qte", "2"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", is(140d)))
				.andDo(print());
	}
	
	@Test
	public void calculateAllTest() throws Exception {

		Book b1 = new Book(5050,"azerty",70,null,"author name1");
		Book b2 = new Book(5040,"qwerty",90,null,"author name2");
		
		when(service.getBookByISBN(5050)).thenReturn(b1);
		when(service.getBookByISBN(5040)).thenReturn(b2);
		
		ArrayList<Long> isbns = new ArrayList<Long>();
		isbns.add(5050L);
		isbns.add(5040L);
		
		when(service.calculateAll(isbns)).thenReturn(160d);
		
		String jsonBody = om.writeValueAsString(isbns);
		
		mockMvc.perform(post("/api/book/calculateAll").content(jsonBody).contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", is(160d)))
		.andDo(print());
	}
	
}