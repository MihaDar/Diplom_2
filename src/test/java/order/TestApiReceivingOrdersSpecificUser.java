package order;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Test;

public class TestApiReceivingOrdersSpecificUser extends OrderBeforeAfter {

    @Test
    @DisplayName("Получение заказов конкретного пользователя с авторизацией")
    public void testReceivingOrdersSpecificUser() {
        order = OrderData.getOrderData();
        ValidatableResponse createOrderResponse = orderHttpClient.getOrders(token);
        int statusCode = createOrderResponse.extract().statusCode();
        boolean success = createOrderResponse.extract().path("success");
        Assert.assertEquals(200, statusCode);
        Assert.assertTrue(success);
    }

    @Test
    @DisplayName("Получение заказов пользователя без авторизацией")
    public void testOrdersWithoutAuthorization() {
        order = OrderData.getOrderData();
        ValidatableResponse createOrderResponse = orderHttpClient.getOrdersWithoutAuthorization();
        int statusCode = createOrderResponse.extract().statusCode();
        String message = createOrderResponse.extract().path("message");
        boolean success = createOrderResponse.extract().path("success");
        Assert.assertEquals(401, statusCode);
        Assert.assertEquals("You should be authorised", message);
        Assert.assertFalse(success);
    }
}
