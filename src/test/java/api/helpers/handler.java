package api.helpers;

import api.config.TestConfig;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class handler extends TestConfig {
    @BeforeEach
    public void setup() {
        RestAssured.filters(new AllureRestAssured());
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType("application/json")
                .build();
    }

    public static String loadJson(String pathJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(pathJson)));
    }

    public void createUserPost(String requestBody) throws IOException {

        Response response = given()
                .log().all()
                .body(requestBody)
                .when()
                .post("users")
                .then()
                .log().all()
                .extract().response();
        assertEquals(201, response.getStatusCode());
        assertNotNull(response.jsonPath().get("id"));
        assertEquals("morpheus", response.jsonPath().getString("name"));
    }
    public void createRecord(String requestBody) throws IOException {

        Response response = given()
                .log().all()
                .body(requestBody)
                .when()
                .post("register")
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();
        assertNotNull(response.jsonPath().get("token"));
    }
    public void createLogin(String requestBody) throws IOException {
        Response response = given()
                .log().all()
                .body(requestBody)
                .when()
                .post("login")
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();
        assertNotNull(response.jsonPath().get("token"));
    }
    public void updateData(String requestBody) throws IOException {

        Response response = given()
                .log().all()
                .body(requestBody)
                .when()
                .put("users/2")
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();
        assertEquals("morpheus",response.jsonPath().getString("name"));
    }
    public void  deleteUser() throws IOException {
        Response response = given()
                .log().all()
                .delete("users/2")
                .then()
                .log().all()
                .extract().response();
        assertEquals(204, response.getStatusCode());
    }
    public void createLoginWithEmptyPassword(String requestBody) throws IOException {
        Response response = given()
                .log().all()
                .body(requestBody)
                .when()
                .post("login")
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract().response();
        assertEquals("Missing password", response.jsonPath().getString("error"));
    }
    public void avatrsGetPage2() throws Exception {

        List<String> avatars = given()
                .log().all()
                .when()
                .get("users?page=2")
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .extract().path("data.avatar");
        assertTrue(avatars.stream().allMatch(avatar -> avatar.startsWith("https://reqres.in")));
    }
    public void listGetPage1() throws Exception {
        int page = given()
                .log().all()
                .when()
                .get("users?page=1")
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .extract().path("page");
        assertEquals(1, page);

    }
    public void getResponseWithDelay() throws Exception {
        List<String> emails = given()
                .log().all()
                .when()
                .get("users?delay=3")
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .extract().path("data.email");
        assertTrue(emails.stream().allMatch(email -> email.contains("@reqres.in")));
    }
    public void getUserById() throws Exception {
        Response response = given()
                .log().all()
                .when()
                .get("users/2")
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();
        assertEquals("Janet", response.jsonPath().getString("data.first_name"));
        assertEquals(2, response.jsonPath().getInt("data.id"));
    }
    public void getInvalidUserId() throws Exception {
        Response response = given()
                .log().all()
                .when()
                .get("users/40")
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .extract().response();
        assertNull(response.jsonPath().get("id"));
    }
    public void multipleApiRequests() throws Exception {
        String endpoint = "users/2";

        given().when().get(endpoint).then().log().all();
        given().when().get(endpoint).then().log().all();
        given().when().get(endpoint).then().log().all();
        given().when().get(endpoint).then().log().all();
        given().when().get(endpoint).then().log().all();
        given().when().get(endpoint).then().log().all();
        given().when().get(endpoint).then().log().all();
        given().when().get(endpoint).then().log().all();
        given().when().get(endpoint).then().log().all();
        given().when().get(endpoint).then().log().all();
        given().when().get(endpoint).then().log().all();

        given()
                .when()
                .get(endpoint)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK);
    }

}
