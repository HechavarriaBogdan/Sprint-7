package praktikum.courier;

import io.qameta.allure.junit4.DisplayName;



import io.restassured.response.ValidatableResponse;
import org.junit.After;

import org.junit.Test;

import java.util.Map;


public class CreateCourierTest {
    // Создали объект класса CourierClient, так как методы createCourier() и logIn() не Static
    private CourierClient client = new CourierClient();
    private CourierChecks checks = new CourierChecks();

    int courierId;


    @After
    public void deleteCourier() {
        if (courierId != 0) // не удаляем курьера с невалидным id
        {
            // Проверяем код и тело ответа после удаления
            ValidatableResponse deleteResponse = client.delete(courierId);
            checks.checkDeleted(deleteResponse);
        }
    }

    @Test
    @DisplayName("Создание курьера и авторизация")
    public void courier() {
        // Сгенерировали данные для рандомного курьера
        var courier = Courier.random();
        // Создали рандомного курьера
        ValidatableResponse createResponse = client.createCourier(courier);
        // Проверили тело ответа и статус-код
        checks.checkCreated(createResponse);
        // Создали тело для логирования под курьером
        var creds = CourierCredentials.fromCourier(courier);
        // Залогинились под курьером
        ValidatableResponse loginResponse = client.logIn(creds);
        // Передали id курьера и проверяем статус-код и тело ответа
        courierId = checks.checkLoggedIn(loginResponse);
    }

    @Test
    @DisplayName("Нельзя создать двух одинаковых курьеров")
    public void doubleCourier() {
        var courier = Courier.random();
        ValidatableResponse createResponse = client.createCourier(courier);
        checks.checkCreated(createResponse);
        ValidatableResponse alreadyInUseResponse = client.createCourier(courier);
        checks.checkCreatedSameCourier(alreadyInUseResponse);
        var creds = CourierCredentials.fromCourier(courier);
        ValidatableResponse loginResponse = client.logIn(creds);
        courierId = checks.checkLoggedIn(loginResponse);

    }

    @Test
    @DisplayName("Нельзя создать курьера без логина")
    public void courierWithoutFields() {
        var withoutLogin = Map.of("password", Courier.random().getPassword(),
                "firstName", Courier.random().getFirstName());
        ValidatableResponse createResponseWithoutLogin = client.createCourierWithoutFields(withoutLogin);
        checks.checkCreatedWithoutFields(createResponseWithoutLogin);

    }

    @Test
    @DisplayName("Нельзя создать курьера с пустым или Null логином")
    public void courierWithEmptyLogin() {
        var emptyLogin = Map.of("login", "", "password", Courier.random().getPassword(),
                "firstName", Courier.random().getFirstName());
        var nullLogin = Courier.random();
        nullLogin.setLogin(null);
        ValidatableResponse createResponseWithEmptyLogin = client.createCourierWithoutFields(emptyLogin);
        checks.checkCreatedWithoutFields(createResponseWithEmptyLogin);

        ValidatableResponse createResponseWithNullLogin = client.createCourier(nullLogin);
        checks.checkCreatedWithoutFields(createResponseWithNullLogin);
    }

    @Test
    @DisplayName("Нельзя создать курьера без пароля")
    public void courierWithoutPassword() {
        var withoutPassword = Map.of("login", Courier.random().getLogin(),
                "firstName", Courier.random().getFirstName());
        ValidatableResponse createResponseWithoutPassword = client.createCourierWithoutFields(withoutPassword);
        checks.checkCreatedWithoutFields(createResponseWithoutPassword);
    }

    @Test
    @DisplayName("Нельзя создать курьера с пустым млм Null паролем")
    public void courierWithEmptyPassword() {
        var emptyPassword = Map.of("login", Courier.random().getLogin(), "password", "",
                "firstName", Courier.random().getFirstName());
        var nullPassword = Courier.random();
        nullPassword.setPassword(null);

        ValidatableResponse createResponseWithEmptyPassword = client.createCourierWithoutFields(emptyPassword);
        checks.checkCreatedWithoutFields(createResponseWithEmptyPassword);

        ValidatableResponse createResponseWithNullPassword = client.createCourier(nullPassword);
        checks.checkCreatedWithoutFields(createResponseWithNullPassword);
    }

    @Test
    @DisplayName("Нельзя создать курьера без имени")
    public void courierWithoutFirstName() {
        var withoutFirstName = Map.of("login", Courier.random().getLogin(),
                "password", Courier.random().getPassword());
        ValidatableResponse createResponseWithoutFirstName = client.createCourierWithoutFields(withoutFirstName);
        checks.checkCreatedWithoutFields(createResponseWithoutFirstName);
    }

    @Test
    @DisplayName("Нельзя создать курьера с пустым или Null именем")
    public void courierWithEmptyFirstName() {
        var emptyFirstName = Map.of("login", Courier.random().getLogin(), "password", Courier.random().getPassword(),
                "firstName", "");
        var nullFirstName = Courier.random();
        nullFirstName.setFirstName(null);
        ValidatableResponse createResponseWithEmptyFirstName = client.createCourierWithoutFields(emptyFirstName);
        checks.checkCreatedWithoutFields(createResponseWithEmptyFirstName);

        ValidatableResponse createResponseWithNullFirstName = client.createCourier(nullFirstName);
        checks.checkCreatedWithoutFields(createResponseWithNullFirstName);
    }




}
