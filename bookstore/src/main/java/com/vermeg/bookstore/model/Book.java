package com.vermeg.bookstore.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="book")
public class Book {

	@Id
	@Column(name="isbn")
	private long isbn;
	
	@Column(name="title")
	private String title;
	
	@Column(name="price")
	private double price;
	
	@Column(name="releaseDate")
	private Date releaseDate;
	
	@Column(name="authorName")
	private String authorName;
	
	public Book() {}

	public Book(long isbn, String title, double price, Date releaseDate, String authorName) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.price = price;
		this.releaseDate = releaseDate;
		this.authorName = authorName;
	}

	public long getIsbn() {
		return isbn;
	}

	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", title=" + title + ", price=" + price + ", releaseDate=" + releaseDate
				+ ", authorName=" + authorName + "]";
	}
	
	
	
	
	
}
