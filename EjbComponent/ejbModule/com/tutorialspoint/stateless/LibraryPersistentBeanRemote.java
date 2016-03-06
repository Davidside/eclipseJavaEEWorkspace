package com.tutorialspoint.stateless;

import com.tutorialspoint.entity.Book;
import com.tutorialspoint.exceptions.NoBookAvailableException;

import java.util.List;
import javax.ejb.Remote;

@Remote
public interface LibraryPersistentBeanRemote {

   void addBook(Book bookName);

   List<Book> getBooks() throws NoBookAvailableException;
   
}