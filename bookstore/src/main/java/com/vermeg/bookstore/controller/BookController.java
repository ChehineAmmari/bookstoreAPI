package com.vermeg.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.vermeg.bookstore.model.Book;
import com.vermeg.bookstore.service.BookServiceInterface;

@RestController
@RequestMapping("/api/book")
@EnableWebMvc
public class BookController {
	
	@Autowired BookServiceInterface bookService;
	
	@RequestMapping(value = "getAll", method = RequestMethod.GET, produces = "application/json")
	public List<Book> getAllBooks() {
		return bookService.getAllBooks();
	}
	
	@RequestMapping(value = "get/{ISBN}", method = RequestMethod.GET, produces = "application/json")
	public Book getBook(@PathVariable long ISBN) {
		return bookService.getBookByISBN(ISBN);
	}
	
	@RequestMapping(value = "add", method = RequestMethod.POST, headers = "Accept=application/json")
	public void add(@RequestBody Book book) {
		bookService.add(book);
	}
	
	@RequestMapping(value = "update", method = RequestMethod.POST, headers = "Accept=application/json")
	public void update(@RequestBody Book book) {
		bookService.update(book);
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public void delete(@RequestParam long ISBN) {
		bookService.delete(ISBN);
	}
	
	@RequestMapping(value = "calculate", method = RequestMethod.GET)
	public double calculate(@RequestParam long ISBN, @RequestParam int qte ) {
		System.out.println(bookService.calculate(getBook(ISBN).getPrice(), qte));
		return bookService.calculate(getBook(ISBN).getPrice(), qte);
	}
	
	@RequestMapping(value = "calculateAll", method = RequestMethod.POST, headers="Accept=application/json")
	public double calculateAll(@RequestBody List<Long> ISBNs) {
		System.out.println(bookService.calculateAll(ISBNs));
		return bookService.calculateAll(ISBNs);
	}
	

}
