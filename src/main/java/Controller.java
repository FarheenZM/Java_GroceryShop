import models.Basket;
import models.Product;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;

import static spark.Spark.*;

public class Controller {
    public static void main(String[] args) {

        VelocityTemplateEngine velocityTemplateEngine = new VelocityTemplateEngine();

        // for images & css
        staticFileLocation("/public");

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

        // Creating an empty shopping basket initially, where products will be later added by user actions
        Basket basket = new Basket();

        // home route
        get("/", (request, response) -> {
            HashMap<String, Object> model = new HashMap<>();
            model.put("allProducts", allProductsInShop);
            model.put("template", "templates/home.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, velocityTemplateEngine);

        // basket route
        get("/basket", (request, response) -> {
            HashMap<String, Object> model = new HashMap<>();
            model.put("basket", basket);
            model.put("template", "templates/basket.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, velocityTemplateEngine);

        // post request used for adding product to basket
        post("/basket/:id", (request, response) -> {
            Product basketProduct;
            if(Integer.parseInt(request.params("id")) == 0){
                basketProduct = soup;
            }else if(Integer.parseInt(request.params("id")) == 1){
                basketProduct = bread;
            }else if(Integer.parseInt(request.params("id")) == 2){
                basketProduct = milk;
            }else{
                basketProduct = apple;
            }

            basket.addAProductToBasket(basketProduct);
            response.redirect("/basket");
            return null;
        }, velocityTemplateEngine);

        // post request used for removing product from basket
        post("/basket/:id/remove", (request, response) -> {
            Product productToRemove;
            if(Integer.parseInt(request.params("id")) == 0){
                productToRemove = soup;
            }else if(Integer.parseInt(request.params("id")) == 1){
                productToRemove = bread;
            }else if(Integer.parseInt(request.params("id")) == 2){
                productToRemove = milk;
            }else{
                productToRemove = apple;
            }

            basket.removeAProductFromBasket(productToRemove);
            response.redirect("/basket");
            return null;
        }, velocityTemplateEngine);

        // final bill when calculated
        get("/bill", (request, response) -> {
            HashMap<String, Object> model = new HashMap<>();
            model.put("basket", basket);
            model.put("template", "templates/bill.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, velocityTemplateEngine);

    }
}
