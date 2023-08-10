package day8;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class CreateUser
{
	
	@Test
	
	void test_createUser(ITestContext context) {
		
		Faker faker = new Faker();
		
		JSONObject data= new JSONObject();
		
		data.put("name", faker.name().fullName());
		data.put("gender", "Male");
		data.put("email", faker.internet().emailAddress());
		data.put("status", "inactive");
		
		
		String bearertoken = "d967d26c931d94150c94807dd67bb0aa323384fa864ed2d8779de6a2200c30bb";
		
	int id=	given()
			.headers("Authorization","Bearer "+bearertoken)
			.contentType("application/json")
			.body(data.toString())
		
		.when()
			.post("https://gorest.co.in/public/v2/users")
			.jsonPath().getInt("id");
	
	System.out.println("Generated"+ id);
	
	/*
	 Access within only one test folder in testng xml file
	context.setAttribute("user_id", id);
	context.setAttribute("bearertoken", bearertoken);
	*/
	
	context.getSuite().setAttribute("user_id", id);
	context.getSuite().setAttribute("bearertoken", bearertoken);
	
	
	
	}

}
