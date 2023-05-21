package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenTests {

	@Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
	public void testPostUser(String  id, String username, String firstName, String lastName, String email, String password, String phone)
	{

		User userPayLoad = new User();
		userPayLoad.setId(Integer.parseInt(id));
		userPayLoad.setUsername(username);
		userPayLoad.setFirstName(firstName);
		userPayLoad.setLastName(lastName);
		userPayLoad.setEmail(email);
		userPayLoad.setPassword(password);
		userPayLoad.setPhone(phone);
		
		Response response = UserEndPoints.createUser(userPayLoad);
		Assert.assertEquals(response.getStatusCode(), 200);
		System.out.println(userPayLoad.getUsername()+" added successfully");
		
	}

	
	@Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
	public void testDeleteUserByName(String username)
	{
		Response response = UserEndPoints.deleteUser(username);
		Assert.assertEquals(response.getStatusCode(), 200);
		System.out.println(username +" deleted successfully");
	}
}
