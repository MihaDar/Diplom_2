package user;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Test;

public class TestApiUserLogin extends UserBeforeAfter{

    @Test
    @DisplayName("Логин под существующим пользователем")
    public void testLoginExistingUser() {
        user = UserData.getUserDataLogin();
        ValidatableResponse createResponse = userHttpClient.loginUser(user);
        int statusCode = createResponse.extract().statusCode();
        boolean success = createResponse.extract().path("success");
        Assert.assertEquals(200, statusCode);
        Assert.assertTrue(success);
    }

    @Test
    @DisplayName("Логин пользователя c неверным полем Email")
    public void testLoginUserWrongEmail() {
        user = UserData.getUserDataLoginWrongEmail();
        ValidatableResponse createResponse = userHttpClient.loginUser(user);
        int statusCode = createResponse.extract().statusCode();
        boolean success = createResponse.extract().path("success");
        String message = createResponse.extract().path("message");
        Assert.assertFalse(success);
        Assert.assertEquals(401, statusCode);
        Assert.assertEquals("email or password are incorrect", message);
    }

    @Test
    @DisplayName("Логин пользователя c неверным полем Password")
    public void testLoginUserWrongPassword() {
        user = UserData.getUserDataLoginWrongPassword();
        ValidatableResponse createResponse = userHttpClient.loginUser(user);
        int statusCode = createResponse.extract().statusCode();
        boolean success = createResponse.extract().path("success");
        String message = createResponse.extract().path("message");
        Assert.assertFalse(success);
        Assert.assertEquals(401, statusCode);
        Assert.assertEquals("email or password are incorrect", message);
    }
}
