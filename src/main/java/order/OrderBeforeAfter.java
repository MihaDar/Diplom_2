package order;

import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import user.User;
import user.UserData;
import user.UserHttpClient;

public class OrderBeforeAfter {
    OrderHttpClient orderHttpClient;
    Order order;
    private static UserHttpClient userHttpClient;
    private User user;
    String token;

    @Before
    public void setUp() {
        userHttpClient = new UserHttpClient();
        orderHttpClient = new OrderHttpClient();
        user = UserData.getUserData();
        ValidatableResponse createResponse = userHttpClient.createUser(user);
        token = createResponse.extract().path("accessToken");
    }

    @After
    public void cleanUp() {
        if (token != null) {
            ValidatableResponse response = userHttpClient.deleteUser(token);
            boolean success = response.extract().path("success");
            Assert.assertTrue(success);
        }
    }
}
