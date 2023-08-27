package order;
import data.DataBaseHttp;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;

public class OrderHttpClient extends DataBaseHttp{
    private static final String API_ORDERS = "/api/orders";

    public ValidatableResponse createOrder(String token, Order order){
        return given()
                .spec(getDataBaseHttp())
                .header("authorization", token)
                .body(order)
                .when()
                .post(API_ORDERS)
                .then();
    }
    public ValidatableResponse createOrder(Order order){
        return given()
                .spec(getDataBaseHttp())
                .body(order)
                .when()
                .post(API_ORDERS)
                .then();
    }
    public ValidatableResponse getOrders(String token){
        return given()
                .spec(getDataBaseHttp())
                .header("authorization", token)
                .get(API_ORDERS)
                .then();
    }
    public ValidatableResponse getOrdersWithoutAuthorization(){
        return given()
                .spec(getDataBaseHttp())
                .get(API_ORDERS)
                .then();
    }
}
