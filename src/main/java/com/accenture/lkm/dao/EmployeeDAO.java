package com.accenture.lkm.dao;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.accenture.lkm.model.Employee;

@Repository
public class EmployeeDAO {
	/**
	 * Map is used to Replace the Database 
	 * */
	static public Map<Integer, Employee> mapOfEmloyees = new LinkedHashMap<Integer, Employee>();
	static int count=10004;
	static
	{
		mapOfEmloyees.put(10001, new Employee("Jack",10001,12345.6,1001));
		mapOfEmloyees.put(10002, new Employee("Justin",10002,12355.6,1002));
		mapOfEmloyees.put(10003, new Employee("Eric",10003,12445.6,1003));
	}
	
	/**
	 * Returns all the Existing Employees
	 * */
	public Collection<Employee> getAllEmployee(){
		return mapOfEmloyees.values();			
	}
	

	/**Get Employee details using EmployeeId .
	 * Returns an Employee object response with Data if Employee is Found
	 * Else returns a null
	 * */
	public Employee getEmployeeDetailsById(int id){
		return mapOfEmloyees.get(id);
	}
	/**Create Employee details.
	 * Returns auto-generated Id
	 * */
	public Integer addEmployee(Employee employee){
		count++;
		employee.setEmployeeId(count);
		mapOfEmloyees.put(count, employee);
		return count;
	}
	
	/**Update the Employee details,
	 * Receives the Employee Object and returns the updated Details  
	 * */
	public Employee updateEmployee (Employee employee){
		mapOfEmloyees.put(employee.getEmployeeId(), employee);
		return employee;
	}
	/**Delete the Employee details,
	 * Receives the EmployeeID and returns the deleted employee's Details  
	 * */
	public Employee removeEmployee (int id){
		Employee emp= mapOfEmloyees.remove(id);
		return emp;
	}
	
}