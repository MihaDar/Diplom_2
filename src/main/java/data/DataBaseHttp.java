package data;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.http.ContentType;
public abstract class DataBaseHttp {
    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site/";

    protected RequestSpecification getDataBaseHttp(){
       return new RequestSpecBuilder()
               .setBaseUri(BASE_URL)
               .setContentType(ContentType.JSON)
               .build();
    }
}
