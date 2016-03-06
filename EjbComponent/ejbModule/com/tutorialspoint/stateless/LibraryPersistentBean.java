package com.tutorialspoint.stateless;

import com.tutorialspoint.entity.Book;
import com.tutorialspoint.exceptions.NoBookAvailableException;
import com.tutorialspoint.interceptor.BusinessInterceptor;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Interceptors(BusinessInterceptor.class)
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@Path("/hellows")
public class LibraryPersistentBean implements LibraryPersistentBeanRemote {
    
   public LibraryPersistentBean(){
   }

   @PersistenceContext(unitName="EjbComponentPU")
   private EntityManager entityManager;         

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void addBook(Book book) {
      entityManager.persist(book);
   }    

   
   public List<Book> getBooks() throws NoBookAvailableException {
      List<Book> books = entityManager.createQuery("From Book").getResultList();
      if(books.size() == 0) {
    	  throw new NoBookAvailableException("No Book available in library.");
      }
      return books;
   }
   
   @GET
   @Produces(MediaType.APPLICATION_XML)
   public String sayHello() {
   	return "Hello";
   }
   
   @PostConstruct
   public void postConstruct() {
	   System.out.println("postConstruct: LibraryPersistentBean session bean created with entity Manager object: ");
   }
   
   @PreDestroy
   public void preDestroy(){
      System.out.println("preDestroy: LibraryPersistentBean session bean is removed ");
   }
}