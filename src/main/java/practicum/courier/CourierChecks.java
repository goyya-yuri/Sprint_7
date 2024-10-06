package practicum.courier;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;

import static java.net.HttpURLConnection.*;

public class CourierChecks {
    @Step("Успешная регистрация курьера")
    public void checkCreated(ValidatableResponse response) {
        boolean created = response
                .assertThat()
                .statusCode(HTTP_CREATED)
                .extract()
                .path("ok");
        Assert.assertTrue(created);
    }

    @Step("Успешная авторизация курьера")
    public int checkLoggedIn(ValidatableResponse response) {
        int id = response
                .assertThat()
                .statusCode(HTTP_OK)
                .extract()
                .path("id");
        Assert.assertNotEquals(0,id);
        return id;
    }

    @Step("Успешная авторизация курьера")
    public void checkLoggedInWrongData(ValidatableResponse response) {
        String msg = response
                .assertThat()
                .statusCode(HTTP_NOT_FOUND)
                .extract()
                .path("message");
        Assert.assertEquals("Учетная запись не найдена", msg);
    }

    @Step("Успешная авторизация курьера")
    public void checkLoggedInNotEnoughData(ValidatableResponse response) {
        String msg = response
                .assertThat()
                .statusCode(HTTP_BAD_REQUEST)
                .extract()
                .path("message");
        Assert.assertEquals("Недостаточно данных для входа", msg);
    }

    @Step("Успешное удаление курьера")
    public void checkDeleted(ValidatableResponse response){
        boolean removed = response
                .assertThat()
                .statusCode(HTTP_OK)
                .extract()
                .path("ok");
        Assert.assertTrue(removed);
    }

    @Step("Недостаточно данных для создания курьера")
    public void checkCreatedFail(ValidatableResponse response) {
        String msg = response
                .assertThat()
                .statusCode(HTTP_BAD_REQUEST)
                .extract()
                .path("message");
        Assert.assertEquals("Недостаточно данных для создания учетной записи", msg);
    }

    @Step("Курьер с указанным логином уже существует")
    public void checkCreatedCloneFail(ValidatableResponse response) {
        String msg = response
                .assertThat()
                .statusCode(HTTP_CONFLICT)
                .extract()
                .path("message");
        Assert.assertEquals("Этот логин уже используется", msg);
    }
}
