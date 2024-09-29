package praktikum.courier;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import java.net.HttpURLConnection;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class CourierChecks {
    @Step("Проверка успешного логирования курьера и получения id")
    public int checkLoggedIn(ValidatableResponse loginResponse) {
        int id = loginResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK) // code 200
                .extract()
                .path("id");
        assertNotEquals(0, id);
        return id;
    }

    @Step("Проверка успешного создания курьера")
    public void checkCreated(ValidatableResponse createResponse) {
        boolean created = createResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_CREATED) // code 201
                .extract()
                .path("ok");
        assertTrue(created);
    }

    @Step("Проверка успешного удаления курьера")
    public void checkDeleted(ValidatableResponse deleteResponse) {
        boolean deleted = deleteResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK) // code 201
                .extract()
                .path("ok");
        assertTrue(deleted);
    }

    @Step("Проверка неуспешного создания двух одинаковых курьеров")
    public void checkCreatedSameCourier(ValidatableResponse alreadyInUseResponse) {
        var alreadyInUsed = alreadyInUseResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_CONFLICT)
                .and()
                .body("message", equalTo("Этот логин уже используется"));
    }

    @Step("Проверка неуспешного создания курьера без обязательного поля")
    public void checkCreatedWithoutFields(ValidatableResponse notRequiredFieldsResponse) {
        var notRequiredFields = notRequiredFieldsResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_BAD_REQUEST)
                .and()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Step("Проверка неуспешной авторизации без обязательного поля")
    public void checkLoginWithoutFields(ValidatableResponse notRequiredFieldsResponse) {
        var notRequiredFields = notRequiredFieldsResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_BAD_REQUEST)
                .and()
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Step("Проверка авторизации с несуществующим логином или паролем")
    public void checkUnknownLogin(ValidatableResponse unknownFieldsResponse) {
        var unknownFields = unknownFieldsResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_NOT_FOUND)
                .and()
                .body("message", equalTo("Учетная запись не найдена"));

    }



}
