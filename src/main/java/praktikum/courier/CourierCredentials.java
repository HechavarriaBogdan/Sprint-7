package praktikum.courier;

import io.restassured.response.ValidatableResponse;
import praktikum.Client;

public class CourierCredentials {
    private String login;
    private String password;

    public CourierCredentials(String login, String password) {
        this.login = login;
        this.password = password;
    }

    // Нужно чтобы логиниться под курьером с использованием данных для создания курьера
    public static CourierCredentials fromCourier(Courier courier) {
        return new CourierCredentials(courier.getLogin(), courier.getPassword());
    }




    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }


    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
