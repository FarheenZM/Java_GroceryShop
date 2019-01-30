import models.Basket;
import models.Product;

import java.util.ArrayList;

public class Controller {
    public static void main(String[] args) {

        // Creating product instances
        Product soup = new Product(0,"Soup", 0.65, "/images/soup.png");
        Product bread = new Product(1,"Bread", 0.80, "/images/bread.png");
        Product milk = new Product(2,"Milk", 1.30, "/images/milk.png");
        Product apple = new Product(3,"Apple", 1.00, "/images/apples.png");

        // Creating an array of all products available in the shop, so that they can be displayed in the shop
        ArrayList<Product> allProductsInShop = new ArrayList<>();
        allProductsInShop.add(soup);
        allProductsInShop.add(bread);
        allProductsInShop.add(milk);
        allProductsInShop.add(apple);

        System.out.println(allProductsInShop);

        // Creating an empty shopping basket initially, where products will be later added by user actions
        Basket basket = new Basket();
        System.out.println(basket.noOfProductsInTheBasket());
        basket.addAProductToBasket(apple);
        basket.addAProductToBasket(soup);
        basket.addAProductToBasket(milk);
        basket.addAProductToBasket(bread);
        System.out.println(basket.noOfProductsInTheBasket());
        System.out.println(basket.calculateSubtotalOfProductsInTheBasket());
        System.out.println(basket.calculateAppleDiscount());
        System.out.println(basket.calculateBreadDiscount());
        basket.addAProductToBasket(soup);
        System.out.println(basket.calculateBreadDiscount());

        System.out.println(soup);
        System.out.println(basket.calculateSubtotalOfProductsInTheBasket());
        System.out.println(basket.calculateFinalBill());

    }
}

