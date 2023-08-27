package user;
import data.DataBaseHttp;
import io.restassured.http.Header;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.*;

public class UserHttpClient extends DataBaseHttp {
    private static final String API_AUTH_REGISTER = "/api/auth/register";
    private static final String API_AUTH_LOGIN = "/api/auth/login";
    private static final String API_AUTH_USER = "/api/auth/user";

    public ValidatableResponse createUser(User user){
        return given()
                .spec(getDataBaseHttp())
                .body(user)
                .when()
                .post(API_AUTH_REGISTER)
                .then();
    }

    public ValidatableResponse loginUser(User user){
        return given()
                .spec(getDataBaseHttp())
                .body(user)
                .when()
                .post(API_AUTH_LOGIN)
                .then();
    }

    public ValidatableResponse updateUser(String accessToken, User user){
        return given()
                .header(new Header("Authorization",accessToken))
                .spec(getDataBaseHttp())
                .body(user)
                .when()
                .patch(API_AUTH_USER)
                .then();
    }

    public ValidatableResponse deleteUser(String accessToken){
        return given()
                .header(new Header("Authorization",accessToken))
                .spec(getDataBaseHttp())
                .when()
                .delete(API_AUTH_USER)
                .then();
    }
}
