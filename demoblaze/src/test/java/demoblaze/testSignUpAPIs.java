package demoblaze;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class testSignUpAPIs extends testBase {

	@Test (priority = 1)
	public void testSignUpwithNewUser() {

		DateFormat dateFormat = new SimpleDateFormat("ddMMyyHHmmss");
		Date date = new Date(); 
		username = "test_"+dateFormat.format(date);

		String requestBody = "{\"username\":" + "\"" + username+"\"" + "," +  "\"password\":" + "\""+ password +"\"}";
		System.out.println (requestBody);

		Response response = RestAssured.given()
				.header("Content-type", "application/json").header("accept", "application/json")
				.and()
				.body(requestBody)
				.when()
				.post("/signup")
				.then()
				.extract().response();

		System.out.println (response.asString());
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.asString(), "\"\"\n");

	}
	@Test (priority = 2)
	public void testSignUpWithAlreadyExistUser() {

		String requestBody = "{\"username\":" + "\"" + username+"\"" + "," +  "\"password\":" + "\""+ password +"\"}";

		Response response = RestAssured.given()
				.header("Content-type", "application/json").header("accept", "application/json")
				.and()
				.body(requestBody)
				.when()
				.post("/signup")
				.then()
				.extract().response();

		System.out.println (response.asString());
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.asString(), "{\"errorMessage\":\"This user already exist.\"}\n");

	}

}
