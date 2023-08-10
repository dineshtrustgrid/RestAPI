package day7;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class Authentication {
	
//	@Test(priority=1)
	void basicAuthentication() {
		
		given()
			.auth().basic("postman", "password")
		
		
		.when()
			.get("https://postman-echo.com/basic-auth")
		
		.then()
			.statusCode(200)
			.body("authenticated",equalTo(true))
			.log().all();
		
	}
	

	
//	@Test(priority=2)
	void digestAuthentication() {
		
		given()
			.auth().digest("postman", "password")
		
		
		.when()
			.get("https://postman-echo.com/basic-auth")
		
		.then()
			.statusCode(200)
			.body("authenticated",equalTo(true))
			.log().all();
		
	}
	//@Test(priority=3)
	void preemptiveAuthentication() {
		
		given()
			.auth().preemptive().basic("postman", "password")
		
		
		.when()
			.get("https://postman-echo.com/basic-auth")
		
		.then()
			.statusCode(200)
			.body("authenticated",equalTo(true))
			.log().all();
		
	}
	
	
//	@Test(priority=4)
	
	void bearertokenAuthentication() {
		
		String bearertoken="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";
				
			given()
				.headers("Authorization","Bearer "+bearertoken)
			.when()
				.get("https://api.github.com/user/repos")
			.then()
			.statusCode(200)
			.log().all();
	}
	
	//@Test(priority=5)
	
		void oauth1Authentication() {
			
		
		given()
		
			.auth().oauth("consumerkey","consumersecret","accesstoken","tokensecret")
		
		.when()
		.get("url")
		.then()
		.statusCode(200)
		.log().all();
		
		}
	
	
	@Test
	
	void oauth2Authentication() {
		given()
		
		.auth().oauth2("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")
	
	.when()
	.get("https://api.github.com/user/repos")
	.then()
	.statusCode(200)
	.log().all();
		
	}
	
	
}
