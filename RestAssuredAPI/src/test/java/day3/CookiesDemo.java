package day3;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class CookiesDemo {

	@Test
	void testcookies() {
		
		given()
		
		.when()
			.get("https://www.google.com/")
		
		.then()
			.statusCode(200)
			.cookie("AEC")
			.log().all();
		
	}
	
	@Test
	 void getcookiesinfo() {
		
	Response res=given()
		
		.when()
			.get("https://www.google.com/");
	
	
		String cookie_value=res.getCookie("AEC");
		String cookie_value1=res.getCookie("NID");
			System.out.println(cookie_value);
			System.out.println(cookie_value1);
			
		Map<String,String> cookieslist=res.getCookies();
		
		System.out.println("keys"+cookieslist.keySet().size());
		
		for (String k :cookieslist.keySet()) {
			
			System.out.println(res.getCookie(k));
		}			
	}
}
