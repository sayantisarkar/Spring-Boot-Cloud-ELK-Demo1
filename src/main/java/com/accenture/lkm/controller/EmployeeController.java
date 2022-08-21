package com.accenture.lkm.controller;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.lkm.dao.EmployeeDAO;
import com.accenture.lkm.model.Employee;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeDAO employeeDAO;

	public static Logger logger = Logger.getLogger(EmployeeController.class);

	/**
	 * Method is used to get all the employee details and return the same
	 */
	@RequestMapping(value = "emp/controller/getDetails", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Employee>> getEmployeeDetails() {
		logger.info("From Producer method[getEmployeeDetails] start");
		logger.debug("From Producer method[getEmployeeDetails] start");
		Collection<Employee> listEmployee = employeeDAO.getAllEmployee();
		logger.debug("From Producer method[getEmployeeDetails] end");
		logger.info("From Producer method[getEmployeeDetails] end");
		return new ResponseEntity<Collection<Employee>>(listEmployee, HttpStatus.OK);
	}

	/**
	 * Method finds an employee using employeeId and returns the found Employee If
	 * no employee is not existing corresponding to the employeeId, then null is
	 * returned with HttpStatus.INTERNAL_SERVER_ERROR as status
	 */
	@RequestMapping(value = "emp/controller/getDetailsById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> getEmployeeDetailByEmployeeId(@PathVariable("id") int myId) {
		logger.info("From Producer method[getEmployeeDetailByEmployeeId] start");
		Employee employee = employeeDAO.getEmployeeDetailsById(myId);
		if (employee != null) {
			logger.info("From Producer method[getEmployeeDetailByEmployeeId] end");
			return new ResponseEntity<Employee>(employee, HttpStatus.OK);
		} else {
			logger.info("From Producer method[getEmployeeDetailByEmployeeId] end");
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}

	}

	/** Method creates an employee and returns the auto-generated employeeId */
	@RequestMapping(value = "/emp/controller/addEmp", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_HTML_VALUE)
	public ResponseEntity<String> addEmployee(@RequestBody Employee employee) {
		logger.info("From Producer method[addEmployee] start");
		logger.debug("From Producer method[addEmployee] start");
		int empId = employeeDAO.addEmployee(employee);
		logger.debug("From Producer method[addEmployee] end");
		logger.info("From Producer method[addEmployee] end");
		return new ResponseEntity<String>("Employee added successfully with id:" + empId, HttpStatus.CREATED);
	}

	/**
	 * Method updates an employee and returns the updated Employee If Employee to be
	 * updated is not existing, then null is returned with
	 * HttpStatus.INTERNAL_SERVER_ERROR as status
	 */
	@RequestMapping(value = "/emp/controller/updateEmp", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
		logger.info("From Producer method[updateEmployee] start");
		if (employeeDAO.getEmployeeDetailsById(employee.getEmployeeId()) == null) {
			Employee employee2 = null;
			return new ResponseEntity<Employee>(employee2, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		System.out.println(employee);
		employeeDAO.updateEmployee(employee);
		logger.info("From Producer method[updateEmployee] end");
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

	/**
	 * Method deletes an employee using employeeId and returns the deleted Employee
	 * If Employee to be deleted is not existing, then null is returned with
	 * HttpStatus.INTERNAL_SERVER_ERROR as status
	 */
	@RequestMapping(value = "/emp/controller/deleteEmp/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> deleteEmployee(@PathVariable("id") int myId) {
		logger.info("From Producer method[deleteEmployee] start");
		if (employeeDAO.getEmployeeDetailsById(myId) == null) {
			Employee employee2 = null;
			return new ResponseEntity<Employee>(employee2, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		Employee employee = employeeDAO.removeEmployee(myId);
		System.out.println("Removed: " + employee);
		logger.info("From Producer method[deleteEmployee] end");
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}
}
