package day8;

import static io.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.annotations.Test;

public class Deleteuser {
	
	@Test
	
	void test_delete(ITestContext context) {
		
		int id=(int) context.getSuite().getAttribute("user_id");
		String bearertoken = (String) context.getSuite().getAttribute("bearertoken");
			
			given()
				.headers("Authorization","Bearer "+bearertoken)
				
				.pathParam("id", id)
			
			.when()
				.delete("https://gorest.co.in/public/v2/users/{id}")
			.then()
				.statusCode(204)
				.log().all();
	}

}
