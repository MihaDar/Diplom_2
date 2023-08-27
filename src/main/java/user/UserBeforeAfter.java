package user;

import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

public abstract class UserBeforeAfter {

    static UserHttpClient userHttpClient;
    User user;
    String token;

    @Before
    public void setUp() {
        userHttpClient = new UserHttpClient();
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
