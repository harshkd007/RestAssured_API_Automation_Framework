package api.endpoints;


import api.payload.User;

import static io.restassured.RestAssured.given; 

import io.restassured.http.ContentType;
import io.restassured.response.Response;

// Perform the Create, Read, Update, Delete requests
public class UserEndPoints {

	// Add the user
	//-H 'accept: application/json' \
	//-H 'Content-Type: application/json' \
	public static Response createUser(User payload)
	{
		Response response =given()
				.contentType(ContentType.JSON)
				.accept((ContentType.JSON))
				.body(payload)
				.when()
				.post(Routes.post_url);
		return response;
	}

    // Get the user details based on username
	public static Response readUser(String username)
	{
		Response response =  given()
				.pathParam("username", username)
				.when()
				.get(Routes.get_url);

		return response;
	}

	// Update the user details based on username
	//-H 'accept: application/json' \
	//-H 'Content-Type: application/json' \
	public static Response updateUser(String username, User payload)
	{
		Response response =   given()
				.contentType(ContentType.JSON)
				.accept((ContentType.JSON))
				.pathParam("username", username)
				.body(payload)
				.when()
				.put(Routes.put_url);

		return response;
	}

	// Delete the user details based on username
	public static Response deleteUser(String username)
	{
		Response response =  given()
				.pathParam("username", username)
				.when()
				.delete(Routes.delete_url);

		return response;
	}



}
