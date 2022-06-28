package com.employeeapi.testCases;

import org.junit.AfterClass;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC001_GET_All_Employees extends TestBase{

	@BeforeClass
	public void getAllEmployees() throws InterruptedException {

		System.out.println("*********Started with TC001_Get_All_Employees*********");
		RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/employees");
		Thread.sleep(5000);
	}

	@Test
	public void checkResponseBody() {

		String	responseBody = response.getBody().asString();
		System.out.println("Response body is :" + responseBody);
		Assert.assertTrue(responseBody!=null);

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

	@Test
	public void checkContentLength() {

		String contentLength = response.header("Content-Length");
		System.out.println("Content Length  is :" + contentLength);
		if(Integer.parseInt(contentLength)<100) {
			System.out.println("Content Length is less than 100");
		}

		Assert.assertTrue(Integer.parseInt(contentLength)>100);

	}

	@Test
	public void checkCookies() {

		String cookie = response.getCookie("PHPSESSID");
		System.out.println("Cookie  is :" + cookie);
		//Assert.assertEquals(cookie, "1esuvsfslcmiee2bfrsgnijtg0"); //cookies cannot be validated as it is dynamic

	}
	@AfterClass
	public void tearDown() {
		
		System.out.println("*********Finished with TC001_Get_All_Employees*********");
	}
}
