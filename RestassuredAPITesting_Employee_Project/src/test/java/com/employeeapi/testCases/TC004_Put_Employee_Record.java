package com.employeeapi.testCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;
import com.employeeapi.utilities.RestUtils;
import com.mongodb.util.JSON;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC004_Put_Employee_Record extends TestBase {

	
	
	  RequestSpecification httpRequest;
	  Response response;
	 

	String empName = RestUtils.empName();
	String empSal = RestUtils.empSalary();
	String empAge = RestUtils.empAge();
	

	@BeforeClass
	void getEmployeeData() throws InterruptedException {

		logger.info("********* Started TC004_Put_Employee_Record **********");

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";

		httpRequest = RestAssured.given();

		JSONObject requestParams = new JSONObject();
		requestParams.put("name", empName);
		requestParams.put("salary", empSal);
		requestParams.put("age", empAge);

		httpRequest.header("Content-Type", "application/json");

		httpRequest.body(requestParams.toJSONString());

		response = httpRequest.request(Method.PUT, "/update/"+empID);

		Thread.sleep(8000);

	}

	
	@Test
	void checkResposeBody() {
		logger.info(" ***********  Checking Respose Body ********** ");

		String responseBody = response.getBody().asString();
		
		logger.info("Response Body==>" + responseBody);
		
		Assert.assertEquals(responseBody.contains(empName), true);
		Assert.assertEquals(responseBody.contains(empSal), true);
		Assert.assertEquals(responseBody.contains(empAge), true);
	}

	@Test
	void checkStatusCode() {
		logger.info(" ***********  Checking Status Code ********** ");

		int statusCode = response.getStatusCode();
		logger.info("Status Code is ==>" + statusCode);
		Assert.assertEquals(statusCode, 200);

	}

	
	@Test
	void checkStatusLine() {
		logger.info(" ***********  Checking Status Line ********** ");

		String statusLine = response.getStatusLine();
		logger.info("Status Line is ==>" + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");

	}

	@Test
	void checkContentType() {

		logger.info(" ***********  Checking Content Type ********** ");

		String contentType = response.header("Content-Type");
		logger.info("Content Type is ==>" + contentType);
		Assert.assertEquals(contentType, "application/json");
	}

	@Test
	void checkServerType() {
		logger.info(" ***********  Checking Server Type ********** ");

		String serverType = response.header("Server");
		logger.info("Content Type is ==>" + serverType);
		Assert.assertEquals(serverType, "nginx");

	}

	@Test
	void checkContentEncoding() {
		logger.info(" ***********  Checking Content Encoding ********** ");

		String contentEncoding = response.header("Content-Encoding");
		logger.info("Content Type is ==>" + contentEncoding);
		Assert.assertEquals(contentEncoding, "gzip");
	}


	void tearDown() {

		logger.info("*********** finished TC004_Put_Employee_Record  ***************");

	}

}
