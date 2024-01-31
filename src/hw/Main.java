package hw;

import hw.domain.Customer;
import hw.domain.Product;
import hw.domain.impl.Market;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Product> products = new ArrayList<>(List.of(
                new Product("Milk", 5),
                new Product("Cheese", 3),
                new Product("Yogurt", 1)
        ));

        List<Customer> customers = new ArrayList<>(List.of(
                new Customer("Ann"),
                new Customer("Bob"),
                new Customer("Tim")
        ));


        Market market = new Market(customers, products);
        market.view();
    }
}
