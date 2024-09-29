package practicum.courier;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;

public class CreateCourierTest {
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
    @DisplayName("Создание курьера")
    public void courier(){
        var courier = Courier.random();
        ValidatableResponse createResponse = client.createCourier(courier);
        check.checkCreated(createResponse);

        var creds = CourierCredentials.fromCourier(courier);
        ValidatableResponse loginResponse = client.logIn(creds);
        courierId = check.checkLoggedIn(loginResponse);
    }

    @Test
    @DisplayName("Создание 2-х курьеров с одинаковыми данными")
    public void courierFailClone(){
        var courier = Courier.random();
        client.createCourier(courier);
        var creds = CourierCredentials.fromCourier(courier);
        ValidatableResponse loginResponse = client.logIn(creds);
        courierId = check.checkLoggedIn(loginResponse);

        ValidatableResponse createResponseClone = client.createCourier(courier);
        check.checkCreatedCloneFail(createResponseClone);
    }

    @Test
    @DisplayName("Проверка на невозможность создать курьера без логина")
    public void courierFailWithoutLogin(){
        var courier = Courier.random();
        courier.setLogin("");
        ValidatableResponse createResponse = client.createCourier(courier);
        check.checkCreatedFail(createResponse);

        var creds = CourierCredentials.fromCourier(courier);
        ValidatableResponse loginResponse = client.logIn(creds);
        check.checkLoggedInNotEnoughData(loginResponse);
    }

    @Test
    @DisplayName("Проверка на невозможность создать курьера без пароля")
    public void courierFailWithoutPassword(){
        var courier = Courier.random();
        courier.setPassword("");
        ValidatableResponse createResponse = client.createCourier(courier);
        check.checkCreatedFail(createResponse);

        var creds = CourierCredentials.fromCourier(courier);
        ValidatableResponse loginResponse = client.logIn(creds);
        check.checkLoggedInNotEnoughData(loginResponse);
    }

    @Test
    @DisplayName("Проверка на невозможность создать курьера без имени")
    public void courierFailWithoutName(){
        var courier = Courier.random();
        courier.setFirstName("");
        ValidatableResponse createResponse = client.createCourier(courier);
        check.checkCreatedFail(createResponse);

        var creds = CourierCredentials.fromCourier(courier);
        ValidatableResponse loginResponse = client.logIn(creds);
        check.checkLoggedIn(loginResponse);
    }
}
