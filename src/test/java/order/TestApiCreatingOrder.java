package order;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Test;

public class TestApiCreatingOrder extends OrderBeforeAfter {

    @Test
    @DisplayName("Cоздание заказа с авторизацией и с ингредиентами")
    public void testCreatingNewOrderWithAuthorizationWithIngredients() {
        order = OrderData.getOrderData();
        ValidatableResponse createOrderResponse = orderHttpClient.createOrder(token, order);
        int statusCode = createOrderResponse.extract().statusCode();
        boolean success = createOrderResponse.extract().path("success");
        Assert.assertEquals(200, statusCode);
        Assert.assertTrue(success);
    }

    @Test
    @DisplayName("Cоздание заказа без авторизацией и с ингредиентами")
    public void testCreatingNewOrderWithoutAuthorizationWithIngredients() {
        order = OrderData.getOrderData();
        ValidatableResponse createOrderResponse = orderHttpClient.createOrder(order);
        int statusCode = createOrderResponse.extract().statusCode();
        boolean success = createOrderResponse.extract().path("success");
        Assert.assertEquals(200, statusCode);
        Assert.assertTrue(success);
    }

    @Test
    @DisplayName("Cоздание заказа с авторизацией без ингредиентов")
    public void testCreatingNewOrderWithAuthorizationWithoutIngredients() {
        order = OrderData.getOrderDataWithoutIngredients();
        ValidatableResponse createOrderResponse = orderHttpClient.createOrder(token, order);
        int statusCode = createOrderResponse.extract().statusCode();
        String message = createOrderResponse.extract().path("message");
        boolean success = createOrderResponse.extract().path("success");
        Assert.assertEquals(400, statusCode);
        Assert.assertEquals("Ingredient ids must be provided", message);

        Assert.assertFalse(success);
    }

    @Test
    @DisplayName("Cоздание заказа без авторизации без ингредиентов")
    public void testCreatingNewOrderWithoutAuthorizationWithoutIngredients() {
        order = OrderData.getOrderDataWithoutIngredients();
        ValidatableResponse createOrderResponse = orderHttpClient.createOrder(order);
        int statusCode = createOrderResponse.extract().statusCode();
        boolean success = createOrderResponse.extract().path("success");
        Assert.assertEquals(400, statusCode);
        Assert.assertFalse(success);
    }

    @Test
    @DisplayName("Cоздание заказа с авторизацией и с неверным хешем ингредиентов")
    public void testCreatingNewOrderWithAuthorizationWrongIngredients() {
        order = OrderData.getOrderDataWrongIngredients();
        ValidatableResponse createOrderResponse = orderHttpClient.createOrder(token, order);
        int statusCode = createOrderResponse.extract().statusCode();
        boolean success = createOrderResponse.extract().path("success");
        Assert.assertEquals(500, statusCode);
        Assert.assertTrue(success);
    }

    @Test
    @DisplayName("Cоздание заказа без авторизации и с неверным хешем ингредиентов")
    public void testCreatingNewOrderWithoutAuthorizationWrongIngredients() {
        order = OrderData.getOrderDataWrongIngredients();
        ValidatableResponse createOrderResponse = orderHttpClient.createOrder(order);
        int statusCode = createOrderResponse.extract().statusCode();
        boolean success = createOrderResponse.extract().path("success");
        Assert.assertEquals(500, statusCode);
        Assert.assertTrue(success);
    }
}
