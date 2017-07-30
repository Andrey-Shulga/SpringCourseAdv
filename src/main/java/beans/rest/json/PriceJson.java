package beans.rest.json;

import java.io.Serializable;

public class PriceJson implements Serializable {

    private String price;

    public PriceJson() {
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
