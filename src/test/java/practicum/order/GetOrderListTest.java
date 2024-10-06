package practicum.order;

import io.restassured.response.ValidatableResponse;
import org.junit.Test;

public class GetOrderListTest {
    private final OrderClient client = new OrderClient();
    private final OrderChecks check = new OrderChecks();

    @Test
    public void getOrdersList(){
        ValidatableResponse response = client.getOrders();
        check.orderListReturnValues(response);
    }
}
