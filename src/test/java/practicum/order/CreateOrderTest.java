package practicum.order;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class CreateOrderTest {
    private final OrderClient client = new OrderClient();
    private final OrderChecks check = new OrderChecks();
    List<String> color;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { List.of("BLACK", "GRAY") },
                { List.of("BLACK") },
                { List.of("GRAY") },
                { List.of("") },

        });
    }

    public CreateOrderTest(List<String> color){
        this.color = color;
    }

    @Test
    @DisplayName("Создание заказа")
    public void createOrder(){
        Order order = Order.random();
        order.setColor(color);
        ValidatableResponse response = client.createOrder(order);
        check.checkCreated(response);
    }
}
