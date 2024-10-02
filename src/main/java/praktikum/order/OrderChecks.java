package praktikum.order;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.MatcherAssert;

import java.net.HttpURLConnection;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

public class OrderChecks {
    @Step("Проверка успешного создания заказа и получение track-номера")
    public void checkCreated(ValidatableResponse createResponse) {
        int track = createResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_CREATED)
                .extract()
                .path("track");
        assertNotEquals(0, track);
    }

    @Step("Проверка получения списка заказов")
    public void checkOrders(ValidatableResponse getOrders) {
        Orders orders = getOrders
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .extract().as(Orders.class);
        MatcherAssert.assertThat(orders, notNullValue());
    }
}
