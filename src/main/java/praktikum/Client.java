package praktikum;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class Client {
    public static final String BASE_URI = "https://qa-scooter.praktikum-services.ru/";;
    public static final String BASE_PATH = "api/v1/";
    public static final String COURIER_PATH = "courier";
    public static final String ORDER_PATH = "orders";

    public RequestSpecification spec() {
        return given().log().all()
                .contentType(ContentType.JSON) // вместо .header("Content-Type", "application/json")
                .baseUri(BASE_URI)
                .basePath(BASE_PATH);
    }

}
