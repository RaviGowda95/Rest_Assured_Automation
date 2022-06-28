package com.employeeapi.testCases;

import org.json.simple.JSONObject;
import org.junit.AfterClass;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC005_DELETE_Employee_Record  extends TestBase{

	RequestSpecification httpRequest;
	Response response;

	@BeforeClass
	public void deleteEmployee() throws InterruptedException {

		System.out.println("*********Started with TC005_DELETE_Employee_Record*********");
		RestAssured.baseURI = "https://dummy.restapiexample.com";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/employees");

		//First get the JSONpath object instance from the Response interface
		JsonPath jsonPathEvaluator = response.jsonPath();

		//Capture id
		String empID = jsonPathEvaluator.get("[0].id");

		//Delete request
		Response response = httpRequest.request(Method.DELETE,"/delete/"+ empID);//passing id to delete record

		Thread.sleep(5000);

	}

	@Test
	public void checkResponseBody() {

		String	responseBody = response.getBody().asString();
		//System.out.println("Response body is :" + responseBody);
		Assert.assertEquals(responseBody.contains("successfully! deleted Records"),true);

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

		System.out.println("*********Finished with TC005_DELETE_Employee_Record*********");
	}

}
