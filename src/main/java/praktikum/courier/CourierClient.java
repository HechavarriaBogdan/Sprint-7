package praktikum.courier;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import java.util.Map;


public class CourierClient extends praktikum.Client {

    @Step("Создание курьера")
    public ValidatableResponse createCourier(Courier courier) {
        return spec()
                .body(courier)
                .when()
                .post(COURIER_PATH)
                .then().log().all();
    }

    @Step("Создание курьера без обязательных полей")
    public ValidatableResponse createCourierWithoutFields(Map<String, String> map) {
        return spec()
                .body(map)
                .when()
                .post(COURIER_PATH)
                .then().log().all();
    }

    @Step("Логин курьера")
    public ValidatableResponse logIn(CourierCredentials creds) {
        return spec()
                .body(creds)
                .when()
                .post(COURIER_PATH + "/login")
                .then().log().all();
    }

    @Step("Логин курьера без обязательных полей")
    public ValidatableResponse logInWithoutFields(Map<String, String> map) {
        return spec()
                .body(map)
                .when()
                .post(COURIER_PATH + "/login")
                .then().log().all();
    }

    @Step("Удаление курьера")
    public ValidatableResponse delete(int id) {
        return spec()
                .body(Map.of("id", id))
                .when()
                .delete(COURIER_PATH + "/" + id)
                .then().log().all();
    }

}
