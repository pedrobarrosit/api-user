package api.tests;

import api.helpers.handler;
import org.junit.jupiter.api.Test;


public class SerachUser extends handler {
    handler handler = new handler();

    @Test
    public void getPage2() throws Exception {
        handler.avatrsGetPage2();
    }

    @Test
    public void getPage1() throws Exception {
        handler.listGetPage1();
    }

    @Test
    public void responseWithDelay() throws Exception {
        handler.getResponseWithDelay();
    }

    @Test
    public void fetchUserById() throws Exception {
        handler.getUserById();
    }

    @Test
    public void fetchInvalidUserId() throws Exception {
        handler.getInvalidUserId();
    }

    @Test
    public void multipleApiAccess() throws Exception {
        handler.multipleApiRequests();
    }
}
