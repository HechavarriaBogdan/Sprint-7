package praktikum;

import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.http.params.CoreConnectionPNames;

import static io.restassured.RestAssured.given;

public class Client {
    public static final String BASE_URI = "https://qa-scooter.praktikum-services.ru/";;
    public static final String BASE_PATH = "api/v1/";
    public static final String COURIER_PATH = "courier";
    public static final String ORDER_PATH = "orders";

    public RequestSpecification spec() {
        RestAssuredConfig config = RestAssuredConfig.config()
                .httpClient(HttpClientConfig.httpClientConfig()
                        .setParam(CoreConnectionPNames.CONNECTION_TIMEOUT, 15000)
                        .setParam(CoreConnectionPNames.SO_TIMEOUT, 15000));
        return given().config(config).log().all()
                .contentType(ContentType.JSON) // вместо .header("Content-Type", "application/json")
                .baseUri(BASE_URI)
                .basePath(BASE_PATH);
    }

}
