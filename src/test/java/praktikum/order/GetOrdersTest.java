package praktikum.order;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

public class GetOrdersTest {

    OrderClient client = new OrderClient();
    OrderChecks checks = new OrderChecks();

    @Test
    @DisplayName("Получение списка заказов")
    public void getOrders () {
        // Получили список всех заказов
        ValidatableResponse getOrders = client.getOrders();
        // Проверили что в ответе приходит список заказов
        checks.checkOrders(getOrders);
    }
}
