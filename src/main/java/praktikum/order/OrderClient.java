package praktikum.order;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import praktikum.Client;

public class OrderClient extends Client {

    @Step("Создание заказа")
    public ValidatableResponse createOrder(OrderCredentials orderCredentials) {
        return spec()
                .body(orderCredentials)
                .when()
                .post(ORDER_PATH)
                .then().log().all();
    }

    @Step("Получение списка заказов")
    public ValidatableResponse getOrders() {
        return spec()
                .when()
                .get(ORDER_PATH)
                .then()
                .log().all();
    }
}
