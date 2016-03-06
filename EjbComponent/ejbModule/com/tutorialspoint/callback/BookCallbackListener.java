package com.tutorialspoint.callback;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import com.tutorialspoint.entity.Book;

public class BookCallbackListener {

	@PrePersist
	public void prePersist(Book book){
		System.out.println("BookCallbackListener.prePersist: Book to be created with the book id: " + book.getId());
	}
	
	@PostPersist
	public void postPersist(Book book) {
		System.out.println("BookCallbackListener.postPersist: Book created with book id: " + ((Book)book).getId());
	}
	
	@PreRemove
	public void preRemove(Book book) {
		System.out.println("BookCallbackListener.preRemove: About to delete Book with id: " + book.getId());
	}
	
	@PostRemove
	public void postRemove(Book book) {
		System.out.println("BookCallbackListener.postRemove: Deleted Book with id: " + book.getId());
	}
	
	@PreUpdate
	public void preUpdate(Book book) {
		System.out.println("BookCallbackListener.preUpdate: About to update Book with id: " + book.getId());
	}
	
	@PostUpdate
	public void postUpdate(Book book) {
		System.out.println("BookCallbackListener.postUpdate: Updated Book with id: " + book.getId());
	}
	
	@PostLoad
	public void postLoad(Book book) {
		System.out.println("BookCallbackListener.postLoad: Loaded Book with id: " + book.getId());
	}
}
