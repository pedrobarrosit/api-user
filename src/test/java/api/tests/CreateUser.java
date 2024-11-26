package api.tests;

import api.helpers.handler;
import org.junit.jupiter.api.Test;
import java.io.IOException;

public class CreateUser extends handler {
    handler handler = new handler();

    @Test
    public void testCreateUser() throws IOException {
        String requestBody = handler.loadJson("src/test/java/api/data/createUser.json");
        handler.createUserPost(requestBody);
    }

    @Test
    public void testRegisterRecord() throws IOException {
        String requestBody = handler.loadJson("src/test/java/api/data/register.json");
        handler.createRecord(requestBody);
    }

    @Test
    public void testCreateLogin() throws IOException {
        String requestBody = handler.loadJson("src/test/java/api/data/login.json");
        handler.createLogin(requestBody);
    }

    @Test
    public void testUpdateData() throws IOException {
        String requestBody = handler.loadJson("src/test/java/api/data/atualizarUser.json");
        handler.updateData(requestBody);
    }

    @Test
    public void testDeleteUser() throws IOException {
        handler.deleteUser();
    }

    @Test
    public void testCreateLoginWithEmptyPassword() throws IOException {
        String requestBody = handler.loadJson("src/test/java/api/data/fallCreate.json");
        handler.createLoginWithEmptyPassword(requestBody);
    }

}
