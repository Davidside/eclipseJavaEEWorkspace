package com.tutorialspoint.test;
   
import com.tutorialspoint.entity.Book;
import com.tutorialspoint.entity.Publisher;
import com.tutorialspoint.exceptions.NoBookAvailableException;
import com.tutorialspoint.stateless.LibraryPersistentBeanRemote;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;

import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class EJBTester {

   BufferedReader brConsoleReader = null; 
   Properties props;
   InitialContext ctx;
   {
      props = new Properties();
      try {
         props.load(new FileInputStream("jndi.properties"));
      } catch (IOException ex) {
         ex.printStackTrace();
      }
      try {
         ctx = new InitialContext(props);            
      } catch (NamingException ex) {
         ex.printStackTrace();
      }
      brConsoleReader = 
      new BufferedReader(new InputStreamReader(System.in));
   }
   
   public static void main(String[] args) {

      EJBTester ejbTester = new EJBTester();

      ejbTester.testEntityEjb();
   }
   
   private void showGUI(){
      System.out.println("**********************");
      System.out.println("Welcome to Book Store");
      System.out.println("**********************");
      System.out.print("Options \n1. Add Book\n2. Exit \nEnter Choice: ");
   }
   
   private void testEntityEjb(){

      try {
         int choice = 1; 
         
         LibraryPersistentBeanRemote libraryBean =
         (LibraryPersistentBeanRemote)ctx.lookup("tutorialspoint/libraryPersistentBean");
         /*
         Queue queue =(Queue) ctx.lookup("/queue/BookQueue");
         QueueConnectionFactory factory = (QueueConnectionFactory) ctx.lookup("ConnectionFactory");
         QueueConnection connection = factory.createQueueConnection();
         QueueSession session = connection.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
         QueueSender sender = session.createSender(queue);
		*/
         
         while (choice != 2) {
            String bookName;
            String publisherName;
            String publisherAddress;
            showGUI();
            String strChoice = brConsoleReader.readLine();
            choice = Integer.parseInt(strChoice);
            if (choice == 1) {
               System.out.print("Enter book name: ");
               bookName = brConsoleReader.readLine();
               System.out.print("Enter publisher name: ");
               publisherName = brConsoleReader.readLine();
               System.out.print("Enter publisher address: ");
               publisherAddress = brConsoleReader.readLine();
               Book book = new Book();
               book.setName(bookName);
               book.setPublisher(new Publisher(publisherName, publisherAddress));
               // ObjectMessage objectMessage = session.createObjectMessage(book);
               // sender.send(objectMessage);
               libraryBean.addBook(book);
            } else if (choice == 2) {
               break;
            }
         }
         

         
         List<Book> booksList = libraryBean.getBooks();

         System.out.println("Book(s) entered so far: " + booksList.size());
         int i = 0;
         for (Book book : booksList) {
            System.out.println((i+1)+". " + book.getName());
            System.out.println("Publication: " + book.getPublisher());
            i++;
         }           
      } catch (NoBookAvailableException e) {
    	  System.out.println(e);
      } catch (Exception e) {
         System.out.println(e.getMessage());
         e.printStackTrace();
      }finally {
         try {
            if(brConsoleReader !=null){
               brConsoleReader.close();
            }
         } catch (IOException ex) {
            System.out.println(ex.getMessage());
         }
      }
   }
}