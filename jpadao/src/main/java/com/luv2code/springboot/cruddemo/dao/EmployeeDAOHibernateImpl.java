package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO{

	private EntityManager entityManager;
	
	@Autowired
	public EmployeeDAOHibernateImpl(EntityManager theEntityManager) {
		// TODO Auto-generated constructor stub
		entityManager = theEntityManager;
	}
	
	
	@Override
	public List<Employee> findAll() {
		// TODO Auto-generated method stub
		
		//get current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		//create a query
		Query<Employee> theQuery = currentSession.createQuery("from Employee",Employee.class);
		
		//execute query and get result list
		List<Employee> employees = theQuery.getResultList();
		
		//return the results
		
		return employees;
	}


	@Override
	public Employee findById(int theId) {
		// TODO Auto-generated method stub
		//get current hibernate session
				Session currentSession = entityManager.unwrap(Session.class);
		
		//Employee create
		Employee theEmployee = currentSession.get(Employee.class,theId);
		
				
		return theEmployee;
	}


	@Override
	public void save(Employee theEmployee) {
		// TODO Auto-generated method stub
		
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(theEmployee);
		
	}


	@Override
	public void deleteById(int theId) {
		// TODO Auto-generated method stub
		Session currentSession = entityManager.unwrap(Session.class);
		Query theQuery = currentSession.createQuery("delete from Employee where id=:employeeId");
		theQuery.setParameter("employeeId",theId);
		theQuery.executeUpdate();
	}

}
