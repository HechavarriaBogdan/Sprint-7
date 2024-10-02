package praktikum.order;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class OrderCredentials {
    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private Number rentTime;
    private String deliveryDate;
    private String comment;
    private String[] color;

    // Конструктор со всеми параметрами
    public OrderCredentials(String firstName, String lastName, String address, String metroStation, String phone,
                            Number rentTime, String deliveryDate, String comment, String[] color) {
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


    // Генерирует рандомные данные для создания заказа
    static OrderCredentials random() {
        Faker faker = new Faker();
        String randomFirstName = faker.name().firstName();
        String randomLastName = faker.name().lastName();
        String randomAddress = faker.address().fullAddress();
        String randomMetroStation = String.valueOf(faker.number().numberBetween(0, 11));
        String randomDeliveryDate = LocalDate.now().format(DateTimeFormatter.ISO_DATE);
        String randomPhone = "+7" + faker.number().numberBetween(9000000000L, 9999999999L);
        Number randomRentTime = faker.number().numberBetween(0, 24);
        String randomComment = faker.lorem().sentence();

        return new OrderCredentials(randomFirstName, randomLastName, randomAddress, randomMetroStation,
                randomPhone, randomRentTime, randomDeliveryDate, randomComment, new String[]{"BLACK"});
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String[] getColor() {
        return color;
    }

    public void setColor(String[] color) {
        this.color = color;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMetroStation() {
        return metroStation;
    }

    public void setMetroStation(String metroStation) {
        this.metroStation = metroStation;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Number getRentTime() {
        return rentTime;
    }

    public void setRentTime(Number rentTime) {
        this.rentTime = rentTime;
    }
}
