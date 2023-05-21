package api.test;

import java.io.File;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class UserTest {
	Faker faker;
	User userPayLoad;

	@BeforeClass //Execute before starting all test methods
	public void setupData()
	{
		faker = new Faker();
		userPayLoad= new User();

		//Genarate random data and set the 
		userPayLoad.setId(faker.idNumber().hashCode());
		userPayLoad.setUsername(faker.name().username());
		userPayLoad.setFirstName(faker.name().firstName());
		userPayLoad.setLastName(faker.name().lastName());
		userPayLoad.setEmail(faker.internet().safeEmailAddress());
		userPayLoad.setPassword(faker.internet().password());
		userPayLoad.setPhone(faker.phoneNumber().cellPhone());
	}

	@Test
	public void testEndToEndWorkflow()
	{
		// Create the user
		Response postResponse = UserEndPoints.createUser(userPayLoad);	
		System.out.println(postResponse.asString());
		postResponse.then().log().all();
		Assert.assertEquals(postResponse.getStatusCode(), 200); 

		// get the user details
		Response getResponse = UserEndPoints.readUser(this.userPayLoad.getUsername());
		getResponse.then().log().all();
		Assert.assertEquals(getResponse.getStatusCode(), 200);

		//verify the get request returns the added username
		ResponseBody body = getResponse.getBody();
		body.asString();
		System.out.println("Response Body is: " + body.asString());

		Assert.assertEquals(body.asString().contains(userPayLoad.getUsername()), true);	
	}

	@Test(priority = 1)
	public void testCreateUserByName()
	{
		Response response = UserEndPoints.createUser(userPayLoad);	
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);  
	}

	@Test(priority = 2)
	public void testUpdateUserByName()
	{
		// update data using payload 
		userPayLoad.setFirstName(faker.name().firstName());
		userPayLoad.setLastName(faker.name().lastName());

		// Update the User details
		Response response = UserEndPoints.updateUser(this.userPayLoad.getUsername(), userPayLoad);

		// Verify the response status
		Assert.assertEquals(response.getStatusCode(), 200);


	}

	@Test(priority = 3)
	public void testGetUserDetails()
	{
		Response response = UserEndPoints.readUser(this.userPayLoad.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);

	}

	@Test(priority = 4)
	public void testDeleteUserByUserName()
	{
		Response response = UserEndPoints.deleteUser(this.userPayLoad.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);

	}
}