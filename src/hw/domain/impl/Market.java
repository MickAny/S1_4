package hw.domain.impl;

import hw.domain.Customer;
import hw.domain.Product;
import hw.exceptions.IncorrectCustomerNameException;
import hw.exceptions.IncorrectProductAmountException;
import hw.exceptions.IncorrectProductNameException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Market {
    private static int counterOfOrders = 0;
    Scanner sc = new Scanner(System.in);
    private final List<Customer> customers;
    private final List<Product> products;

    List<String> orders = new ArrayList<>();
    public Market(List<Customer> customers, List<Product> products) {
        this.customers = customers;
        this.products = products;
    }

    public void showCustomers(){
        System.out.println("Available customers: ");
        customers.forEach(System.out::println);
    }
    public void showProducts(){
        System.out.println("Available products: ");
        products.forEach(System.out::println);
    }


    private void makeOrder(){

        try (FileWriter writer = new FileWriter("OrderList")) {
                System.out.println("Введите имя покупателя:");
                showCustomers();
                String tempCustomerName = sc.nextLine();
                if (!getCustomerByName(tempCustomerName))
                    throw new IncorrectCustomerNameException("Вы ввели неверное имя");

                System.out.println("Введите название товара:");
                products.forEach(product -> System.out.println("Product" + product));
                String tempProductName = sc.nextLine();
                if (!getProductByName(tempProductName))
                    throw new IncorrectProductNameException("Вы ввели неверное название продукта");

                System.out.println("Введите количество товара(шт):");
                showProducts();
                int tempProductAmount = sc.nextInt();
                if (!getAmountOfProduct(tempProductName, tempProductAmount))
                    throw new IncorrectProductAmountException("Вы ввели неверное количество товара");

                orders.add("Customer: %s -- Product: %s -- Amount %d"
                        .formatted(tempCustomerName, tempProductName, tempProductAmount));

                System.out.println("The operation was completed successfully");
                counterOfOrders++;

            writer.write(Arrays.toString(orders.toArray()));




        }catch (IOException | IncorrectCustomerNameException | IncorrectProductNameException | IncorrectProductAmountException e){
            System.out.println(e.getMessage());
        }
    }
    private boolean getCustomerByName(String name){
        for (Customer c: customers) {
            if(c.getName().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }
    private boolean getProductByName(String name){
        for (Product p: products) {
            if(p.getName().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }
    private boolean getAmountOfProduct(String name, int amount){
        if(amount == 0) return false;
        for (Product p: products) {
            if(p.getName().equalsIgnoreCase(name) && p.getAmount() >= amount){
                p.setAmount(p.getAmount() - amount);
                return true;
            }
        }
        return false;
    }

    public void view(){
        boolean t = true;

        while (t) {

            System.out.println("Хотите сделать заказ?(y/n)");

            switch (sc.nextLine()) {
                case "y" -> makeOrder();
                case "n" -> {
                    System.out.println("Количество успешных заказов: " + counterOfOrders);
                    t = false;
                }
            }
        }
    }
}
