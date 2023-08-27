package user;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestApiCreatingUser extends UserBeforeAfter {

    @Override
    @Before
    public void setUp() {
        userHttpClient = new UserHttpClient();
    }

    @Test
    @DisplayName("Cоздание уникального пользователя")
    public void testCreatNewUser() {
        user = UserData.getUserData();
        ValidatableResponse createResponse = userHttpClient.createUser(user);
        int statusCode = createResponse.extract().statusCode();
        boolean success = createResponse.extract().path("success");
        token = createResponse.extract().path("accessToken");
        Assert.assertEquals(200, statusCode);
        Assert.assertTrue(success);
    }

    @Test
    @DisplayName("Cоздание пользователя, который уже зарегистрирован")
    public void testCreateUserAlreadyRegistered() {
        user = UserData.getUserData();
        ValidatableResponse createResponse = userHttpClient.createUser(user);
        token = createResponse.extract().path("accessToken");
        ValidatableResponse createNewResponse = userHttpClient.createUser(user);
        int errorStatusCode = createNewResponse.extract().statusCode();
        String detailMessage = createNewResponse.extract().path("message");
        Assert.assertEquals(403, errorStatusCode);
        Assert.assertEquals("User already exists", detailMessage);
    }

    @Test
    @DisplayName("Создать пользователя и не заполнить поле Email")
    public void testCreateUserWithoutEmail() {
        user = UserData.getUserDataWithoutEmail();
        ValidatableResponse createResponse = userHttpClient.createUser(user);
        int statusCode = createResponse.extract().statusCode();
        String message = createResponse.extract().path("message");
        token = createResponse.extract().path("accessToken");
        Assert.assertEquals(403, statusCode);
        Assert.assertEquals("Email, password and name are required fields", message);
    }

    @Test
    @DisplayName("Создать пользователя и не заполнить поле Password")
    public void testCreateUserWithoutPassword() {
        user = UserData.getUserDataWithoutPassword();
        ValidatableResponse createResponse = userHttpClient.createUser(user);
        int statusCode = createResponse.extract().statusCode();
        String message = createResponse.extract().path("message");
        token = createResponse.extract().path("accessToken");
        Assert.assertEquals(403, statusCode);
        Assert.assertEquals("Email, password and name are required fields", message);
    }

    @Test
    @DisplayName("Создать пользователя и не заполнить поле Name")
    public void testCreateUserWithoutName() {
        user = UserData.getUserDataWithoutName();
        ValidatableResponse createResponse = userHttpClient.createUser(user);
        int statusCode = createResponse.extract().statusCode();
        String message = createResponse.extract().path("message");
        token = createResponse.extract().path("accessToken");
        Assert.assertEquals(403, statusCode);
        Assert.assertEquals("Email, password and name are required fields", message);
    }
}
