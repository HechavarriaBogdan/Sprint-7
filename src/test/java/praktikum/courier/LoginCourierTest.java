package praktikum.courier;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Map;

public class LoginCourierTest {

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
    @DisplayName("Нельзя авторизоваться без логина")
    public void loginCourierWithoutLogin() {
        var withoutLogin = Map.of("password", Courier.random().getPassword());
        ValidatableResponse createResponseWithoutLogin = client.logInWithoutFields(withoutLogin);
        checks.checkLoginWithoutFields(createResponseWithoutLogin);
    }

    @Test
    @DisplayName("Нельзя авторизоваться с пустым или Null логином")
    public void loginCourierWithEmptyLogin() {
        var courier = Courier.random();
        var emptyLogin = Map.of("login", "", "password", Courier.random().getPassword());
        var nullLogin = CourierCredentials.fromCourier(courier);
        nullLogin.setLogin(null);
        ValidatableResponse createResponseWithEmptyLogin = client.logInWithoutFields(emptyLogin);
        checks.checkLoginWithoutFields(createResponseWithEmptyLogin);
        ValidatableResponse createResponseWithNullLogin = client.logIn(nullLogin);
        checks.checkLoginWithoutFields(createResponseWithNullLogin);
    }

    @Test
    @DisplayName("Нельзя авторизоваться без пароля")
    public void loginCourierWithoutPassword() {
        var withoutPassword = Map.of("login", Courier.random().getLogin());
        ValidatableResponse createResponseWithoutPassword = client.logInWithoutFields(withoutPassword);
        checks.checkLoginWithoutFields(createResponseWithoutPassword);
    }

    @Test
    @DisplayName("Нельзя авторизоваться с пустым или Null паролем")
    public void loginCourierWithEmptyPassword() {
        var courier = Courier.random();
        var emptyPassword = Map.of("login", Courier.random().getLogin(), "password", "");
        var nullPassword = CourierCredentials.fromCourier(courier);
        nullPassword.setPassword(null);
        ValidatableResponse createResponseWithEmptyPassword = client.logInWithoutFields(emptyPassword);
        checks.checkLoginWithoutFields(createResponseWithEmptyPassword);
        ValidatableResponse createResponseWithNullPassword = client.logIn(nullPassword);
        checks.checkLoginWithoutFields(createResponseWithNullPassword);
    }

    @Test
    @DisplayName("Нельзя авторизоваться с несуществующим логином")
    public void wrongLoginCourier() {
        // Сгенерировали данные для рандомного курьера
        var courier = Courier.random();
        // Создали рандомного курьера
        ValidatableResponse createResponse = client.createCourier(courier);
        // Проверили тело ответа и статус-код
        checks.checkCreated(createResponse);
        // Передали в переменную сгенерированного курьера
        var wrongLogin = CourierCredentials.fromCourier(courier);
        // Изменили логин сгенерированного курьера
        wrongLogin.setLogin(String.valueOf(LocalDateTime.now()));
        // Залогинились под курьером с неверными данными
        ValidatableResponse WrongLoginResponse = client.logIn(wrongLogin);
        // Проверили тело ответа и статус-код
        checks.checkUnknownLogin(WrongLoginResponse);
        // Передали в переменную корректные данные
        var correctLogin = CourierCredentials.fromCourier(courier);
        // Успешно залогинились под курьером
        ValidatableResponse loginResponse = client.logIn(correctLogin);
        // Передали id курьера и проверяем статус-код и тело ответа
        courierId = checks.checkLoggedIn(loginResponse);
    }

    @Test
    @DisplayName("Нельзя авторизоваться с несуществующим паролем")
    public void wrongPasswordCourier() {
        // Сгенерировали данные для рандомного курьера
        var courier = Courier.random();
        // Создали рандомного курьера
        ValidatableResponse createResponse = client.createCourier(courier);
        // Проверили тело ответа и статус-код
        checks.checkCreated(createResponse);
        // Передали в переменную сгенерированного курьера
        var wrongPassword = CourierCredentials.fromCourier(courier);
        // Изменили логин сгенерированного курьера
        wrongPassword.setPassword(String.valueOf(LocalDateTime.now()));
        // Залогинились под курьером с неверными данными
        ValidatableResponse wrongPasswordResponse = client.logIn(wrongPassword);
        // Проверили тело ответа и статус-код
        checks.checkUnknownLogin(wrongPasswordResponse);
        // Передали в переменную корректные данные
        var correctPassword = CourierCredentials.fromCourier(courier);
        // Успешно залогинились под курьером
        ValidatableResponse passwordResponse = client.logIn(correctPassword);
        // Передали id курьера и проверяем статус-код и тело ответа
        courierId = checks.checkLoggedIn(passwordResponse);

    }

}
