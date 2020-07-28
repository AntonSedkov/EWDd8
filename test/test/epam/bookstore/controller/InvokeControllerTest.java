package test.epam.bookstore.controller;

import by.epam.bookstore.controller.InvokeController;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class InvokeControllerTest {

    //test result in logs/log.txt

    InvokeController controller;

    @BeforeMethod
    public void setUp() {
        controller = new InvokeController();
    }

    @Test
    public void testProcessRequest() {
        Map<String, String> responseParam = new HashMap<>();
        responseParam.put("id", "5");
        controller.processRequest("find", responseParam);
    }

    @Test(dependsOnMethods = {"testProcessRequest"})
    public void testProcessRequestException() {
        Map<String, String> responseParam = new HashMap<>();
        responseParam.put("id", "5000");
        controller.processRequest("find", responseParam);
    }

    @Test(dependsOnMethods = {"testProcessRequest", "testProcessRequestException"})
    public void testProcessRequestWithoutResult() {
        Map<String, String> responseParam = new HashMap<>();
        responseParam.put("title", "Something for title");
        controller.processRequest("find", responseParam);
    }

}