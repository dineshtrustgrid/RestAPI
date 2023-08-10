package day8;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class UpdateUser {

	
	
	@Test
	void test_update(ITestContext context)
	{
Faker faker = new Faker();
		
		JSONObject data= new JSONObject();
		
		data.put("name", faker.name().fullName());
		data.put("gender", "Female");
		data.put("email", faker.internet().emailAddress());
		data.put("status", "active");
		
		
		int id=(int) context.getSuite().getAttribute("user_id");
		String bearertoken = (String) context.getSuite().getAttribute("bearertoken");
		
		given()
			.headers("Authorization","Bearer "+bearertoken)
			.contentType("application/json")
			.body(data.toString())
			.pathParam("id", id)
		
		.when()
			.put("https://gorest.co.in/public/v2/users/{id}")
		.then()
			.statusCode(200)
			.log().all();
	}
}
