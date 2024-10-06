package practicum.order;

import com.google.gson.Gson;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;


public class OrderClient {
    private static final String BASE_URL = "https://qa-scooter.praktikum-services.ru";
    private static final String ORDER_PATH = "/api/v1/orders";

    Gson gson = new Gson();

    @Step("Создание заказа")
    public ValidatableResponse createOrder(Order order){
        return RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL)
                .body(gson.toJson(order))
                .when()
                .post(ORDER_PATH)
                .then().log().all();
    }

    public ValidatableResponse getOrders() {
        return RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL)
                .when()
                .get(ORDER_PATH)
                .then().log().all();
    }
}
