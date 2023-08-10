package day2;

import org.json.JSONObject;
import org.json.JSONTokener;
/*
 //* Differentways to create POST Request
 * Post request body using Hashmap
 * post request body creation using org.JSON
 * post request body creation using POJO class
 * post request using External Json files
 * 
 */

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

public class DifferentwayPostRequest {
	
// Post request body hashmap 
	@Test(priority=1)
	void testPostusingHashmap() {
		HashMap data=new HashMap();
				data.put("name","forks");
				data.put("location", "frances");
				data.put("phone","123456789");
				String courseArr[]= {"cobal","c++"};
				
				data.put("course", courseArr);
				
		given()
		.contentType("application/json")
		.body(data)
		
		.when()
			.post("https://reqres.in/api/users")
		.then()
			.statusCode(201)
			.body("name",equalTo("forks"))
			.body("location",equalTo("frances"))
			.body("phone",equalTo("123456789"))
			.body("course[0]",equalTo("cobal"))
			.body("course[1]",equalTo("c++"))
			.header("content-Type","application/json; charset=utf-8")
			.log().all();
			
			
	}
	
	/*@Test(priority=2)
	void testDelete() {
		
		
		given()
		
		.when()
			.delete("http:localhost:3000/students/4")
		
		.then()
			.statusCode(200)
			.log().all();
		
	}
	*/
	
	
	@Test
	void testpostusingJsonlibrary() 
	{
		
		JSONObject data = new JSONObject();
		data.put("name", "scotts");
		data.put("location", "france");
		data.put("phone","1234567");
		String courseArr[]= {"c","c++"};
		
		data.put("course", courseArr);
		
		
		given()
		.contentType("application/json")
		.body(data.toString())
		
		.when()
			.post("https://reqres.in/api/users")
		.then()
			.statusCode(201)
			.body("name",equalTo("scotts"))
			.body("location",equalTo("france"))
			.body("phone",equalTo("1234567"))
			.body("course[0]",equalTo("c"))
			.body("course[1]",equalTo("c++"))
			.header("content-Type","application/json; charset=utf-8")
			.log().all();
		
		
	}
	

		
		
	
	@Test
	void testpostusingPOJO() {
		
		
		POJO_POSTRequest pojo=new POJO_POSTRequest();
		pojo.setName("scott");
		pojo.setLocation("germany");
		pojo.setPhone("123456789");
		String[] coursearr= {"c","c++"};
		pojo.setCourse(coursearr);
		
		given()
		.contentType("application/json")
		.body(pojo)
		
		.when()
			.post("https://reqres.in/api/users")
		.then()
			.statusCode(201)
			.body("name",equalTo("scott"))
			.body("location",equalTo("germany"))
			.body("phone",equalTo("123456789"))
			.body("course[0]",equalTo("c"))
			.body("course[1]",equalTo("c++"))
			.header("content-Type","application/json; charset=utf-8")
			.log().all();
		
		
	}
		
/*
	@Test
	void testpostusingExternalfile() throws FileNotFoundException {
		
		
		File fe = new File(".\\body.json");
		
		FileReader fr = new FileReader(fe);
		
		JSONTokener jt = new JSONTokener(fr);
		
		JSONObject data=new JSONObject(jt);
		
		
		given()
		.contentType("application/json")
		.body(data.toString())
		
		.when()
			.post("http:localhost:3000/students")
		.then()
			.statusCode(201)
			.body("name",equalTo("scott"))
			.body("location",equalTo("germany"))
			.body("phone",equalTo("123456789"))
			.body("course[0]",equalTo("c"))
			.body("course[1]",equalTo("c++"))
			.header("content-Type","application/json; charset=utf-8")
			.log().all();
		
		
	}
	*/
}
