package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.payload.User;

import io.restassured.response.Response;

public class UserTest {

	Faker faker;
	User userpayload;
	
	public Logger logger;
	@BeforeClass
	public void setupData() {
		
		faker = new Faker();
		
		userpayload = new User();
		
		userpayload.setId(faker.idNumber().hashCode());
		userpayload.setUsername(faker.name().username());
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().emailAddress());
		userpayload.setPassword(faker.internet().password());
		userpayload.setPhone(faker.phoneNumber().cellPhone());
			
		logger= LogManager.getLogger(this.getClass());
		
	}
	
	
	@Test (priority=1)
	
	public void testpostUser() {
		
		Response response=UserEndpoints.createUser(userpayload);
				
		response.then().log().body();
				
		Assert.assertEquals(response.getStatusCode(), 200);
				
		logger.info("************************Created User******************");
	}
	
	
	@Test(priority =2)
	
	public void testgetUser() {
		
		String username=userpayload.getUsername();
		Response response =UserEndpoints.readUser(username);
		
		response.then().log().body();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("************************Get User******************");
		
	}
	
	@Test(priority=3)
	
	public void testupdateUser() {
		
		String username=userpayload.getUsername();
		Response response=UserEndpoints.updateUser(userpayload, username);
		response.then().log().body();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("************************Updated User******************");
	}
	
	
	@Test(priority=4)
	
	public void testdeleteUser()
	{
		
		Response response=UserEndpoints.deleteUser(this.userpayload.getUsername());
		
		response.then().log().body();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("************************Deleted User******************");
	}
}
