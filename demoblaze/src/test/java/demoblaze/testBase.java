package demoblaze;

import org.testng.annotations.BeforeSuite;

import io.restassured.RestAssured;

public class testBase {
	public static String username ;
	public static String password = "dGVzdDE=";
	@BeforeSuite
	public static void setup() {


		RestAssured.baseURI = "https://api.demoblaze.com";

	}



}

