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

    // To calculate the discount(10%) on apples
    public double calculateAppleDiscount(){
        // need to check if the basket has apples & also check its quantity
        // find the price of apple to calculate discount on it

        double applePrice = 0, appleDiscount = 0;
        int appleCount = 0;

        for(Product product : this.products){
            if(product.getName() == "Apple"){
                appleCount += 1;
                applePrice = product.getPrice();
            }
        }

        appleDiscount += applePrice * appleCount * 0.1;  // 10%

        return formatPrice(appleDiscount);
    }

    // To calculate the discount on bread if at least 2 soups are purchased
    public double calculateBreadDiscount(){
        // need to check if the basket has soup & has bread & in what quantity
        // find the price of bread to calculate discount on it

        int soupCount = 0, breadCount = 0;
        double breadPrice = 0, breadDiscount = 0;

        for(Product product : this.products){
            if(product.getName() == "Soup"){
                soupCount += 1;
            }
            if(product.getName() == "Bread"){
                breadCount += 1;
                breadPrice = product.getPrice();
            }
        }


        if(breadCount > 0 && soupCount >= 2){
            // 1 bread has discount for 2 soups, so we count the number of breads that can get discount based on soup count
            int noOfBreadsApplicableForDiscount  = soupCount/2;

            // if we have more breads (breadCount) than valid for the discount(noOfBreadsApplicableForDiscount), use the latter for calculating discount
            if(breadCount >= noOfBreadsApplicableForDiscount) {
                breadDiscount = breadPrice * noOfBreadsApplicableForDiscount * 0.5;  // 50%
            }
            // only apply discount on the number of breads in basket because the applicable breads count is more
            // if the noOfBreadsApplicableForDiscount is more than the actual no of breads in basket then do the following
            else if(breadCount < noOfBreadsApplicableForDiscount)
            {
                breadDiscount = breadPrice * breadCount * 0.5;   // 50%
            }
        }

        return formatPrice(breadDiscount);
    }
}


