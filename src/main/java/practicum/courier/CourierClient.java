package practicum.courier;

import com.google.gson.Gson;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import java.util.Map;

public class CourierClient {
    private static final String BASE_URL = "https://qa-scooter.praktikum-services.ru";
    private static final String COURIER_PATH = "/api/v1/courier";

    Gson gson = new Gson();

    @Step("Авторизация курьера")
    public ValidatableResponse logIn(CourierCredentials creds) {
        return RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL)
                .body(gson.toJson(creds))
                .when()
                .post(COURIER_PATH+"/login")
                .then().log().all();
    }

    @Step("Регистрация курьера")
    public ValidatableResponse createCourier(Courier courier) {
        return RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL)
                .body(gson.toJson(courier))
                .when()
                .post(COURIER_PATH)
                .then().log().all();
    }

    @Step("Удаление курьера")
    public ValidatableResponse deleteCourier(int id){
        return RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL)
                .body(Map.of("id", id))
                .when()
                .delete(COURIER_PATH+"/"+id)
                .then().log().all();
    }
}
