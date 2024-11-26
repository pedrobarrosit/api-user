package api.config;


import org.junit.jupiter.api.BeforeAll;
import  static io.restassured.RestAssured.*;

public class TestConfig {
    @BeforeAll
    public static void setUp() {baseURI = System.getProperty("base.url", "https://reqres.in/api/");}
}
