package demoblaze;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class testLoginAPIs extends testBase {
	@Test (priority = 1)
	public void testLoginwithValidUserNameAndValidPassword() {

		String requestBody = "{\"username\":" + "\"" + username+"\"" + "," +  "\"password\":" + "\""+ password +"\"}";

		System.out.println (requestBody);
		Response response = RestAssured.given()
				.header("Content-type", "application/json").header("accept", "application/json")
				.and()
				.body(requestBody)
				.when()
				.post("/login")
				.then()
				.extract().response();

		Assert.assertTrue(response.asString().contains("Auth_token"));

	}
	
	@Test  (priority = 2)
	public void testLoginwithValidUserNameAndInValidPassword() {

		String requestBody = "{\"username\":" + "\"" + username+"\"" + "," +  "\"password\":" + "\""+ "test1" +"\"}";

		System.out.println (requestBody);
		Response response = RestAssured.given()
				.header("Content-type", "application/json").header("accept", "application/json")
				.and()
				.body(requestBody)
				.when()
				.post("/login")
				.then()
				.extract().response();
		
		System.out.println(response.asString());

		Assert.assertFalse(response.asString().contains("Auth_token"));
		Assert.assertEquals(response.asString(), "{\"errorMessage\":\"Wrong password.\"}\n");

	}

}
