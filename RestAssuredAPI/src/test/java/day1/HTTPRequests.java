package day1;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
public class HTTPRequests {

	
	/*given()
	 * Pre-requistie
	 * 
	 *  content type ,set cookies,set headers,add params,add auth,etc...
	 * 
	 * when()
	 *  request Urls
	 *  
	 *  get,post,put,delete,etc
	 * 
	 * 
	 * then()
	 * 
	 * validation 
	 * 
	 * response validation,status code validation,extract header cookies,response body..
	 * 
	 */

	int id;
	@Test(priority=1)
	void getlistuser() {
		
		given() 
		
		.when()
			.get("https://reqres.in/api/users?page=2")
		
		.then()
			.statusCode(200)
			.body("page",equalTo(2))
			.log().all();
		
	}

	
	@Test(priority =2)
	void createuser() {
		
		
		HashMap hm = new HashMap();
				hm.put("name", "dinesddh"); 
				hm.put("job", "trainersss");
				String courseArr[]= {"cobal","c++"};
				
				hm.put("course", courseArr);
				
		id =given()
		
			.contentType("application/json")
			.body(hm)
		
		.when()
			.post("https://reqres.in/api/users")
			.jsonPath().getInt("id");
		
		/*.then()
		
			.statusCode(201)
			.log().all();
			
			*/
			
			
	}

	@Test(priority =3,dependsOnMethods = {"createuser"})
	void updateuser() {
		
		HashMap hm = new HashMap();
		hm.put("name", "dineshkumar"); 
		hm.put("job", "learner");
		
     given()

	.contentType("application/json")
	.body(hm)

    .when()
	  .put("https://reqres.in/api/users/"+id)
	  
	 .then()
	 .statusCode(200)
     .log().all();

	}


	
	@Test(priority=4,dependsOnMethods = {"createuser"})
	void deleteuser() {
		given()
		
		.when()
		  .delete("https://reqres.in/api/users/"+id)
		  
		 .then()
		 .statusCode(204)
	     .log().all();

	}

}
