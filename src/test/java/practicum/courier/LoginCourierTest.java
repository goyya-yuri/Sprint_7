package practicum.courier;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;

public class LoginCourierTest {
    private final CourierClient client = new CourierClient();
    private final CourierChecks check = new CourierChecks();

    int courierId;

    @After
    public void deleteCourier(){
        if(courierId == 0){return;}
        ValidatableResponse deleteResponse = client.deleteCourier(courierId);
        check.checkDeleted(deleteResponse);
    }

    @Test
    @DisplayName("Авторизация курьера")
    public void loginCourierDefault(){
        var courier = Courier.random();
        ValidatableResponse createResponse = client.createCourier(courier);
        check.checkCreated(createResponse);

        var creds = CourierCredentials.fromCourier(courier);
        ValidatableResponse loginResponse = client.logIn(creds);
        courierId = check.checkLoggedIn(loginResponse);
    }

    @Test
    @DisplayName("Авторизации без логина")
    public void loginCourierFailWithoutLogin(){
        var courier = Courier.random();
        ValidatableResponse createResponse = client.createCourier(courier);
        check.checkCreated(createResponse);

        var creds = CourierCredentials.fromCourier(courier);
        creds.setLogin("");
        ValidatableResponse loginResponse = client.logIn(creds);
        check.checkLoggedInNotEnoughData(loginResponse);
    }

    @Test
    @DisplayName("Авторизации без пароля")
    public void loginCourierFailWithoutPassword(){
        var courier = Courier.random();
        ValidatableResponse createResponse = client.createCourier(courier);
        check.checkCreated(createResponse);

        var creds = CourierCredentials.fromCourier(courier);
        creds.setPassword("");
        ValidatableResponse loginResponse = client.logIn(creds);
        check.checkLoggedInNotEnoughData(loginResponse);
    }

    @Test
    @DisplayName("Авторизации с неправильным паролем")
    public void loginCourierFailWidthWrongPassword(){
        var courier = Courier.random();
        ValidatableResponse createResponse = client.createCourier(courier);
        check.checkCreated(createResponse);

        var creds = CourierCredentials.fromCourier(courier);
        creds.setPassword("newWrongPass");
        ValidatableResponse loginResponse = client.logIn(creds);
        check.checkLoggedInWrongData(loginResponse);
    }

    @Test
    @DisplayName("Авторизация несуществующего курьера")
    public void loginFailUnregisteredCourier(){
        var creds = new CourierCredentials("goodman", "saulpass");
        ValidatableResponse loginResponse = client.logIn(creds);
        check.checkLoggedInWrongData(loginResponse);
    }
}
