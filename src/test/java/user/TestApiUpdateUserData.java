package user;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Test;

public class TestApiUpdateUserData extends UserBeforeAfter{

    @Test
    @DisplayName("Изменение данных пользователя с авторизацией")
    public void testUpdateUserDataWithAuthorization() {
        user = UserData.getRandom();
        ValidatableResponse updateResponse = userHttpClient.updateUser(token, user);
        int statusCode = updateResponse.extract().statusCode();
        boolean success = updateResponse.extract().path("success");
        Assert.assertEquals(200, statusCode);
        Assert.assertTrue(success);
    }

    @Test
    @DisplayName("Изменение данных пользователя с авторизацией")
    public void testUpdateUserDataWithoutAuthorization() {

        user = UserData.getRandom();
        ValidatableResponse updateResponse = userHttpClient.updateUser(null, user);
        int statusCode = updateResponse.extract().statusCode();
        String message = updateResponse.extract().path("message");
        boolean success = updateResponse.extract().path("success");
        Assert.assertEquals(401, statusCode);
        Assert.assertEquals("You should be authorised", message);
        Assert.assertFalse(success);
    }
}
