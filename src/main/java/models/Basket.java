package models;

import java.util.ArrayList;

public class Basket {
    private ArrayList<Product> products;

    public Basket() {
        this.products = new ArrayList<Product>();
    }

    // To access all the products from the basket
    public ArrayList<Product> getProducts() {
        return products;
    }

    // To display the total no. of products in the basket
    public int noOfProductsInTheBasket(){
        return products.size();
    }


    // To add a product into the basket
    public void addAProductToBasket(Product product){
        products.add(product);
    }


    // To remove a product from the basket
    public void removeAProductFromBasket(Product product){
        products.remove(product);
    }
}


