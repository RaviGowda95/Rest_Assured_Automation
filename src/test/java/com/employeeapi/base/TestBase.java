package com.employeeapi.base;

import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {

	public static RequestSpecification httpRequest;
	public static Response response;
	
	public String empID ="719"; //Hard code input - to get details of single employee and update employee
	public Logger logger;
	
	@BeforeClass
	public void setup() {
		
//		logger = Loggers.getLogger("EmployeesRestAPI");
//		PropertyConfigurator.configure("Log4j.properties");
//		logger.setLevel(Level.DEBUG);
	}
}
