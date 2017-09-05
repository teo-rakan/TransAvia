package transavia.com.utils;

public class Destination {
    private String city;
    private String country;
    private int price;

    public String getLocation() {
        return city + ", " + country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
