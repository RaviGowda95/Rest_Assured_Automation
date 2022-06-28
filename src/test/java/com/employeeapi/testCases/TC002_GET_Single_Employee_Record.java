package com.employeeapi.testCases;

import org.junit.AfterClass;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC002_GET_Single_Employee_Record extends TestBase {

	@BeforeClass
	public void getEmployeeData() throws InterruptedException {

		System.out.println("*********Started with TC002_Get_Single_Employee_Record*********");
		RestAssured.baseURI = "https://dummy.restapiexample.com";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/employee");
		Thread.sleep(5000);
	}

	@Test
	public void checkResponseBody() {

		String	responseBody = response.getBody().asString();
		//System.out.println("Response body is :" + responseBody);
		Assert.assertEquals(responseBody.contains(empID),true);

	}

	@Test
	public void checkStatusCode() {

		int statusCode = response.getStatusCode();
		System.out.println("Status Code is :" + statusCode);
		Assert.assertEquals(statusCode, 200);


	}

	@Test
	public void checkResponseTime() {

		long responseTime = response.getTime();
		System.out.println("Response Time is :" + responseTime);
		if(responseTime>3000)
			System.out.println("Response Time is greater than 2000");
		Assert.assertTrue(responseTime<3000);

	}


	@Test
	public void checkStatusLine() {

		String statusLine = response.getStatusLine();
		System.out.println("Status Line is :" + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");

	}

	@Test
	public void checkContentType() {

		String contentType = response.getContentType();
		System.out.println("Content Type is :" + contentType);
		Assert.assertEquals(contentType, "text/html; charset=UTF-8");

	}

	@Test
	public void checkServerType() {

		String serverType = response.header("server");
		System.out.println("Server Type is :" + serverType);
		Assert.assertEquals(serverType, "nginx");

	}


	@Test
	public void checkContentLength() {

		String contentLength = response.header("Content-Length");
		System.out.println("Content Length  is :" + contentLength);
		Assert.assertTrue(Integer.parseInt(contentLength)<1500);

	}

	@AfterClass
	public void tearDown() {
		
		System.out.println("*********Finished with TC002_Get_Single_Employee_Record*********");
	}
}
