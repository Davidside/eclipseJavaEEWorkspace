package com.tutorialspoint.messageBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import com.tutorialspoint.entity.Book;
import com.tutorialspoint.stateless.LibraryPersistentBeanRemote;

/**
 * Message-Driven Bean implementation class for: LibraryMessageBean
 */
@MessageDriven(
		activationConfig = { 
				@ActivationConfigProperty( 	propertyName = "destinationType", 
											propertyValue = "javax.jms.Queue"),
				@ActivationConfigProperty( 	propertyName = "destination", 
											propertyValue = "/queue/BookQueue")
		}
)
public class LibraryMessageBean implements MessageListener {

	@Resource
	private MessageDrivenContext mdctx;
	
	@EJB
	LibraryPersistentBeanRemote libraryBean;
    /**
     * Default constructor. 
     */
    public LibraryMessageBean() {
        // TODO Auto-generated constructor stub
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
        ObjectMessage objectMessage = null;
        
        try {
			objectMessage = (ObjectMessage) message;
			Book book = (Book) objectMessage.getObject();
			libraryBean.addBook(book);
			
		} catch (JMSException e) {
			mdctx.setRollbackOnly();
		}
        
    }
    
    @PostConstruct
    public void postConstruct() {
 	   System.out.println("postConstruct: LibraryMessageBean session bean created: ");
    }
    
    @PreDestroy
    public void preDestroy(){
       System.out.println("preDestroy: LibraryMessageBean session bean is removed: ");
    }

}
