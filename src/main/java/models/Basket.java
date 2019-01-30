package models;

import java.text.DecimalFormat;
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

    // To calculate the "total" price of the products in the basket
    public double calculateSubtotalOfProductsInTheBasket(){

        double subTotal = 0;
        for(Product product : this.products){
            subTotal += product.getPrice();
        }

        return formatPrice(subTotal);
    }

    // To limit the decimal places of a number(price here)
    public static double formatPrice(double input)
    {
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.parseDouble(df.format(input));
    }
}


