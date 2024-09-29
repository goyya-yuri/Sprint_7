package practicum.order;

import practicum.RandomString;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Order {
    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private int rentTime;
    private String deliveryDate;
    private String comment;
    private List<String> color;

    public Order(String firstName,
                 String lastName,
                 String address,
                 String metroStation,
                 String phone,
                 int rentTime,
                 String deliveryDate,
                 String comment,
                 List<String> color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    public Order(){}

    public static Order random(){
        double random = new Random().nextDouble();
        return new Order(
                RandomString.randomString(7),
                RandomString.randomString(7),
                RandomString.randomString(16),
                Integer.toString((int) Math.ceil(random*200)),
                RandomString.randomPhone(),
                (int)Math.ceil(random*7),
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(new Date()),
                "",
                List.of("BLACK", "GRAY")
        );
    }

    public void setColor(List<String> color) {
        this.color = color;
    }
}
