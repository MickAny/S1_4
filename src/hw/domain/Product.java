package hw.domain;

public class Product {
    private String name;
    private int amount;
    public Product(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Product: %s | %d left".formatted(name, amount);
    }
}
