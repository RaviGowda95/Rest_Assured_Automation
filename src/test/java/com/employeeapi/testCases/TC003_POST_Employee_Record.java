package com.employeeapi.testCases;

import org.json.simple.JSONObject;
import org.junit.AfterClass;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;
import com.employeeapi.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC003_POST_Employee_Record extends TestBase{

	RequestSpecification httpRequest;
	Response response;

	String empName = RestUtils.empName();
	String empSalary = RestUtils.empSal();
	String empAge = RestUtils.empAge();

	@BeforeClass
	public void createEmployee() throws InterruptedException {

		System.out.println("*********Started with TC003_POST_Employee_Record*********");
		RestAssured.baseURI = "https://dummy.restapiexample.com";
		httpRequest = RestAssured.given();
		//here we are creating data which we can send along with the post request
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", empName);
		requestParams.put("salary", empSalary);
		requestParams.put("age", empAge);

		//adding a header stating that the Request body is in JSON format
		httpRequest.header("Content-Type","application/json");

		//adding the JSON to the body of the request
		httpRequest.body(requestParams.toJSONString());

		//Post Request
		Response response = httpRequest.request(Method.POST,"/create");
		Thread.sleep(8000);

	}

	@Test
	public void checkResponseBody() {

		String	responseBody = response.getBody().asString();
		System.out.println("Response body is :" + responseBody);
		Assert.assertEquals(responseBody.contains(empName),true);
		Assert.assertEquals(responseBody.contains(empSalary),true);
		Assert.assertEquals(responseBody.contains(empAge),true);

	}

	@Test
	public void checkStatusCode() {

		int statusCode = response.getStatusCode();
		System.out.println("Status Code is :" + statusCode);
		Assert.assertEquals(statusCode, 200);

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
		Assert.assertEquals(contentType, "application/json");

	}

	@Test
	public void checkServerType() {

		String serverType = response.header("server");
		System.out.println("Server Type is :" + serverType);
		Assert.assertEquals(serverType, "nginx");

	}


	@Test
	public void checkContentEncoding() {

		String contentEncoding = response.header("Content-Encoding");
		System.out.println("Content Encoding  is :" + contentEncoding);
		Assert.assertEquals(contentEncoding, "gzip");

	}
	
	@AfterClass
	public void tearDown() {
		
		System.out.println("*********Finished with TC003_POST_Employee_Record*********");
	}

}
