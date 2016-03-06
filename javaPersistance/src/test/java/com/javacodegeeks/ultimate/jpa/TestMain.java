package com.javacodegeeks.ultimate.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.TestCase;

public class TestMain extends TestCase {
	private static EntityManagerFactory factory ;
	private static EntityManager entityManager;
	private List<Person> expectedPersonsList;
	
	@BeforeClass
	public static void BeforeClass() {
		// Create new factory
		factory = Persistence.createEntityManagerFactory("PersistenceUnit");
		entityManager = factory.createEntityManager();
	}
	
	@AfterClass
	public static void AfterClass() {
		factory = null;
		entityManager = null;
	}
	
	@Before
	public void setUp() {
		//Create some test data
		EntityTransaction transaction = null;
		expectedPersonsList = new ArrayList<Person>();
	
		Person person = new Person();
		person.setFirstName("Homer");
		person.setLastName("Simpson");
		entityManager.persist(person);
		expectedPersonsList.add(person);
			
	}
	
	@After
	public void tearDown() {
		
		//Clear table data
		
		// Clear List<Person>
		expectedPersonsList = null;
	}
	
	@Test
	public void testGetCriteriaPerson() {
		List<Filter> filters = new ArrayList<Filter>();
		List<String> params = new ArrayList<String>();
		params.add("Simpson");
		
		Filter filter = new Filter();
		filter.setName("lastNameFilter");
		filter.setParams(params);
		filters.add(filter);
		
		List<SorterFilter> sortedFilters = new ArrayList<SorterFilter>();
		params = new ArrayList<String>();
		params.add("descending");
		
		SorterFilter sorterFilter = new SorterFilter();
		sorterFilter.setName("byLastNameSorting");
		sorterFilter.setParams(params);
		sortedFilters.add(sorterFilter);
		
		List<Person> criteriaPersons = new Main().getCriteriaPerson(entityManager, filters, sortedFilters);
		
		for (int i = 0; i < criteriaPersons.size(); i++) {
			Person actulPerson = (Person) criteriaPersons.get(i);
			Person expectedPerson = (Person) expectedPersonsList.get(i);
			assertEquals(expectedPerson, actulPerson);
		}
	}
}
