package practicum.order;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;

import static java.net.HttpURLConnection.HTTP_CREATED;
import static java.net.HttpURLConnection.HTTP_OK;

public class OrderChecks {

    @Step("Успешная регистрация курьера")
    public void checkCreated(ValidatableResponse response) {
        int track = response
                .assertThat()
                .statusCode(HTTP_CREATED)
                .extract()
                .path("track");
        Assert.assertTrue(0 <= track && track <= 99999999);
    }

    @Step("Получен список заказов")
    public void orderListReturnValues(ValidatableResponse response) {
        String ordersJson = response
                .assertThat()
                .statusCode(HTTP_OK)
                .extract()
                .body()
                .asString();

        JsonObject jsonOrders = JsonParser.parseString(ordersJson).getAsJsonObject();
        Assert.assertTrue(jsonOrders.has("orders"));
    }
}
