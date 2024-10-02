package praktikum.order;

import io.qameta.allure.Allure;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class CreateOrderTest {

    private String[] color;

    OrderClient client = new OrderClient();
    OrderChecks checks = new OrderChecks();

    public CreateOrderTest(String[] color) {
        this.color = color;
    }

    @Parameterized.Parameters(name = "color as {0}")
    public static Object[][] orderData() {
        return new Object[][] {
                {new String[]{"BLACK"}},
                {new String[]{"GREY"}},
                {new String[]{"BLACK", "GREY"}},
                {new String[]{}},
        };
    }

    @Test
    @DisplayName("Создание заказа с разными цветами")
    public void orderTest() {
        Allure.parameter("color", color);
        // Сгенерировали данные для рандомного заказа
        OrderCredentials orderCredentials = OrderCredentials.random();
        // Изменили color на данные из двойного массива
        orderCredentials.setColor(color);
        // Создали заказ с рандомными данными
        ValidatableResponse createResponse = client.createOrder(orderCredentials);
        // Проверили тело ответа и статус-код
        checks.checkCreated(createResponse);
    }
}
