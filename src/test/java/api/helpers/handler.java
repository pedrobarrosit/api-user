package api.helpers;

import api.config.TestConfig;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class handler extends TestConfig {

    public static String loadJson(String pathJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(pathJson)));
    }

    public void createUserPost(String requestBody) throws IOException {

        given()
                .contentType("application/json")
                .log().all()
                .body(requestBody)
                .when()
                .post("users")
                .then()
                .log().all()
                .statusCode(201)
                .body("id", notNullValue())
                .body("name", equalTo("morpheus"));
    }
    public void createRecord(String requestBody) throws IOException {

        String token = given()
                .contentType("application/json")
                .log().all()
                .body(requestBody)
                .when()
                .post("register")
                .then()
                .log().all()
                .statusCode(200)
                .body("id", notNullValue())
                .extract()
                .path("token");
        assert token != null && !token.isEmpty() : "Token não encontrado";
        System.out.println("Token extraído: " + token);
    }
    public void createLogin(String requestBody) throws IOException {
        String token = given()
                .contentType("application/json")
                .log().all()
                .body(requestBody)
                .when()
                .post("login")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .path("token");
        assert token != null && !token.isEmpty() : "Token não encontrado";
        System.out.println("Token extraído: " + token);
    }
    public void updateData(String requestBody) throws IOException {

        given()
                .contentType("application/json")
                .log().all()
                .body(requestBody)
                .when()
                .put("users/2")
                .then()
                .log().all()
                .statusCode(200)
                .body("nome", equalTo("morpheus"))
                .body("emprego", equalTo("residente de zion"));
    }
    public void  deleteUser() throws IOException {
        int statusCode = given()
                .contentType("application/json")
                .log().all()
                .delete("users/2")
                .then()
                .log().all()
                .extract().statusCode();
        assertEquals(204, statusCode, "O status do response e 204");
    }
    public void createLoginWithEmptyPassword(String requestBody) throws IOException {
        String error = given()
                .contentType("application/json")
                .log().all()
                .body(requestBody)
                .when()
                .post("login")
                .then()
                .log().all()
                .statusCode(400)
                .extract()
                .path("error");
        assert "Missing password".equals(error) : "Erro esperado " + error;
    }
    public void listGetPage2() throws Exception {

        int total = given()
                .contentType("application/json")
                .log().all()
                .when()
                .get("users?page=2")
                .then()
                .log().all()
                .statusCode(200)
                .extract().path("total");
        assertEquals(12, total);
        System.out.println("O total de users:" + total);
    }
    public void listGetPage1() throws Exception {
        int page = given()
                .contentType("application/json")
                .log().all()
                .when()
                .get("users?page=1")
                .then()
                .log().all()
                .statusCode(200)
                .extract().path("page");
        assertEquals(1, page);
        System.out.println("Acessar page:" + page);
    }
    public void getResponseWithDelay() throws Exception {
        List<Integer> ids = given()
                .contentType("application/json")
                .log().all()
                .when()
                .get("users?delay=3")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .jsonPath().getList("data.id");
        assert !ids.isEmpty();
        System.out.println("Lista ids" + ids);
    }
    public void getUserById() throws Exception {
        int id = given()
                .contentType("application/json")
                .log().all()
                .when()
                .get("users/2")
                .then()
                .log().all()
                .statusCode(200)
                .extract().path("data.id");
        assert id == 2;
        System.out.println("ID:" + id );
    }
    public void getInvalidUserId() throws Exception {
        Object id = given()
                .contentType("application/json")
                .log().all()
                .when()
                .get("users/40")
                .then()
                .log().all()
                .statusCode(404)
                .extract().path("data.id");
        assert id == null;
        System.out.println("ID:" + null);
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
                .statusCode(200);
    }

}
