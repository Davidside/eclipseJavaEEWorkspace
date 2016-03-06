package com.javacodegeeks.ultimate.jpa;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.inject.Typed;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;


import com.javacodegeeks.ultimate.jpa.Project.ProjectType;

public class Main {
	private static final Logger LOGGER = LogManager.getLogger(Main.class);

	public static void main(String[] args) {
		Main main = new Main();
		main.run();

	}
	
	public void run() {
		EntityManagerFactory factory = null;
		EntityManager entityManager = null;
		try {
			factory = Persistence.createEntityManagerFactory("PersistenceUnit");
			entityManager = factory.createEntityManager();
			
			persistPerson(entityManager);
			persistGeek(entityManager);
			persistPhones(entityManager);
			persistProject(entityManager);
			
			// testing Criteria API
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
			
			List<Person> criteriaPersons = getCriteriaPerson(entityManager, filters, sortedFilters);
			
			LOGGER.info("Using criteria builder from reading database info: ");
			for (Person person : criteriaPersons) {
				LOGGER.info(person.getFirstName() + " " + person.getLastName());
			}
			
			// testing totalCount method
			long totalCount = getCriteriaTotalCount(entityManager, filters);
			LOGGER.info("The total count of results is: " + totalCount);
			
			// end of testing criteria API
			
			/*
			TypedQuery<Person> query = entityManager.createQuery("from Person", Person.class);
			List<Person> resultList = query.getResultList();
			for (Person person : resultList) {
				StringBuilder sb = new StringBuilder();
				sb.append(person.getFirstName()).append(" ").append(person.getLastName());
				
				if (person.getIdCard() != null) {
					sb.append(" ").append(person.getIdCard());
				}
				
				if (person instanceof Geek) {
					Geek geek = (Geek)person;
					sb.append(" ").append(geek.getFavouriteProgrammingLanguage());
				}
				LOGGER.info(sb.toString());
			}
			*/
			
		} catch (Exception e) {
			LOGGER.log(Level.ERROR, e.getMessage(), e);
			e.printStackTrace();
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
			if (factory != null) {
				factory.close();
			}
		}
	}
	
	public void persistPerson(EntityManager entityManager) {
		EntityTransaction transaction = entityManager.getTransaction();
		
		try {
			transaction.begin();
			
			IdCard idCard = new IdCard();
			idCard.setIdNumber("790212/7894");
			idCard.setIssueDate(new Date());
			idCard.setValid(true);
			entityManager.persist(idCard);
			
			Person person = new Person();
			person.setFirstName("Homer");
			person.setLastName("Simpson");
			person.setIdCard(idCard);
			entityManager.persist(person);
			
			person = new Person();
			person.setFirstName("Homer");
			person.setLastName("Allpayne");
			entityManager.persist(person);
			
			transaction.commit();
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
		}
	}
	
	public void persistGeek(EntityManager entityManager) {
		EntityTransaction transaction = entityManager.getTransaction();
		
		try {
			transaction.begin();
			
			Geek geek = new Geek();
			geek.setFirstName("Gavin");
			geek.setLastName("Coffee");
			geek.setFavouriteProgrammingLanguage("Java");
			entityManager.persist(geek);
			
			geek = new Geek();
			geek.setFirstName("Thomas");
			geek.setLastName("Micro");
			geek.setFavouriteProgrammingLanguage("C#");
			entityManager.persist(geek);
			
			IdCard idCard = new IdCard();
			idCard.setIdNumber("896547/9875");
			idCard.setIssueDate(new Date());
			idCard.setValid(false);
			entityManager.persist(idCard);
			
			geek = new Geek();
			geek.setFirstName("Christian");
			geek.setLastName("Cup");
			geek.setIdCard(idCard);
			geek.setFavouriteProgrammingLanguage("Java");
			entityManager.persist(geek);
			
			transaction.commit();
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
		}
	}
	
	public void persistPhones(EntityManager entityManager) {
		EntityTransaction transaction = entityManager.getTransaction();
		
		try {
			transaction.begin();
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Person> query = builder.createQuery(Person.class);
			Root<Person> personRoot = query.from(Person.class);
			query.where(builder.equal(personRoot.get("firstName"), "Homer"));
			Person person = entityManager.createQuery(query).getSingleResult();
			
			Phone phone = new Phone();
			phone.setNumber("608 456 684");
			phone.setPerson(person);
			entityManager.persist(phone);
	
			transaction.commit();
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
		}
	}
	
	public void persistProject(EntityManager entityManager) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			
			List<Geek> resultList = entityManager.createQuery("from Geek g where g.favouriteProgrammingLanguage = :fpl", Geek.class)
					.setParameter("fpl", "Java").getResultList();
			
			Project project = new Project();
			project.setTitle("Java Project");
			project.setProjectType(ProjectType.TIME_AND_MATERIAL);
			
			for (Geek geek : resultList) {
				project.getGeeks().add(geek);
				geek.getProjects().add(project);
			}
			
			Period period = new Period();
			DateTime instant = new DateTime(DateTimeZone.UTC);
			period.setStartDate(instant.toDate());
			period.setEndDate(instant.plusYears(1).toDate());
			project.setProjectPeriod(period);
			
			entityManager.persist(project);
			transaction.commit();
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
		}
	}
	
	public List<Person> getCriteriaPerson(EntityManager entityManager, List<Filter> filters, List<SorterFilter> sortedFilters) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Person> criteriaQuery = criteriaBuilder.createQuery(Person.class);
		Root<Person> from = criteriaQuery.from(Person.class);
		ParameterExpression<String> paramFirstName = criteriaBuilder.parameter(String.class);
		ParameterExpression<String> paramLastName = criteriaBuilder.parameter(String.class);
		
		// List of apllied filters
		Map<String, String> appliedFilters = new HashMap<String, String>();
		List<Predicate> predicates = new ArrayList<Predicate>();
		for (Filter filter : filters) {
			if(filter.getName().equals("firstNameFilter")) {
				if (!filter.getParams().isEmpty()) {
					appliedFilters.put(filter.getName(), filter.getParams().get(0));
					predicates.add(criteriaBuilder.equal(from.get(Person_.firstName), paramFirstName));
				} 
			} else if(filter.getName().equals("lastNameFilter")){
				if (!filter.getParams().isEmpty()) {
					appliedFilters.put(filter.getName(), filter.getParams().get(0));
					predicates.add(criteriaBuilder.equal(from.get(Person_.lastName), paramLastName));
				} 
			}
		}
		
		// where clause
		criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
		
		// orderBy clause
		for(SorterFilter sortedFilter : sortedFilters) {
			if(sortedFilter.getName().equals("byLastNameSorting")) {
				if (!sortedFilter.getParams().isEmpty()) {
					if(sortedFilter.getParams().get(0).equals("ascending")) {
						criteriaQuery.orderBy(criteriaBuilder.asc(from.get(Person_.lastName)));
					} else if (sortedFilter.getParams().get(0).equals("descending")){
						criteriaQuery.orderBy(criteriaBuilder.desc(from.get(Person_.lastName)));
					}
				}
			}
		}
		
		// creating query from criteria
		TypedQuery<Person> query = entityManager.createQuery(criteriaQuery);
		
		// setting parameters
		if(appliedFilters.containsKey("firstNameFilter")) {
			query.setParameter(paramFirstName, appliedFilters.get("firstNameFilter"));
		}
		if(appliedFilters.containsKey("lastNameFilter")) {
			query.setParameter(paramLastName, appliedFilters.get("lastNameFilter"));
		}
		
		return query.getResultList();
	}
	
	public long getCriteriaTotalCount(EntityManager entityManager, List<Filter> filters) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Person> from = criteriaQuery.from(Person.class);
		ParameterExpression<String> paramFirstName = criteriaBuilder.parameter(String.class);
		ParameterExpression<String> paramLastName = criteriaBuilder.parameter(String.class);
		
		criteriaQuery.select(criteriaBuilder.count(from.get(Person_.id)));
		
		// List of apllied filters
		Map<String, String> appliedFilters = new HashMap<String, String>();
		List<Predicate> predicates = new ArrayList<Predicate>();
		for (Filter filter : filters) {
			if(filter.getName().equals("firstNameFilter")) {
				if (!filter.getParams().isEmpty()) {
					appliedFilters.put(filter.getName(), filter.getParams().get(0));
					predicates.add(criteriaBuilder.equal(from.get(Person_.firstName), paramFirstName));
				} 
			} else if(filter.getName().equals("lastNameFilter")){
					if (!filter.getParams().isEmpty()) {
						appliedFilters.put(filter.getName(), filter.getParams().get(0));
						predicates.add(criteriaBuilder.equal(from.get(Person_.lastName), paramLastName));
					} 
			}
		}
		
		// where clause
		criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
		
		// creating query from criteria
		TypedQuery<Long> query = entityManager.createQuery(criteriaQuery);
		
		// setting parameters
		if(appliedFilters.containsKey("firstNameFilter")) {
			query.setParameter(paramFirstName, appliedFilters.get("firstNameFilter"));
		}
		if(appliedFilters.containsKey("lastNameFilter")) {
			query.setParameter(paramLastName, appliedFilters.get("lastNameFilter"));
		}
		
		return query.getSingleResult();
	}
}
