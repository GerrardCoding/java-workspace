package com.goodee.model.vo;

public class Member {
	//bookName, bookId, publisher, price
	private String bookName;
	private int bookId;
	private String publisher;
	private int price;
	
	public Member() { }

	public Member(String bookName, int bookId, String publisher, int price) {
		super();
		this.bookName = bookName;
		this.bookId = bookId;
		this.publisher = publisher;
		this.price = price;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Member [bookName=" + bookName + ", bookId=" + bookId + ", publisher=" + publisher + ", price=" + price
				+ "]";
	}
	
	
}
