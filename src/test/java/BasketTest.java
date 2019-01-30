import models.Basket;
import models.Product;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BasketTest {
    Basket basket;

    Product p1, p2, p3, p4;

    @Before
    public void before(){
        basket = new Basket();
        p1 = new Product(0,"Soup", 0.65, "/images/soup.png");
        p2 = new Product(1,"Bread", 0.80, "/images/bread.png");
        p3 = new Product(2,"Milk", 1.30, "/images/milk.png");
        p4 = new Product(3,"Apple", 1.00, "/images/apples.png");
    }

    @Test
    public void isBasketEmpty(){
        assertEquals(0, basket.noOfProductsInTheBasket());
    }

    @Test
    public void canGetAllProductsFromBasket(){
        assertEquals(0, basket.getProducts().size());

        basket.addAProductToBasket(p1);
        basket.addAProductToBasket(p2);
        assertEquals(2, basket.getProducts().size());
    }

    @Test
    public void canAddProductsInBasket(){
        assertEquals(0, basket.noOfProductsInTheBasket());
        basket.addAProductToBasket(p1);
        assertEquals(1, basket.noOfProductsInTheBasket());
    }

    @Test
    public void canRemoveProductsFromBasket(){
        assertEquals(0, basket.noOfProductsInTheBasket());
        basket.addAProductToBasket(p1);
        basket.addAProductToBasket(p2);
        assertEquals(2, basket.noOfProductsInTheBasket());
        basket.removeAProductFromBasket(p2);
        assertEquals(1, basket.noOfProductsInTheBasket());
    }

    @Test
    public void canCalculateTotalPriceOfProductsInBasket(){
        assertEquals(0.0, basket.calculateSubtotalOfProductsInTheBasket(), 0.0);
        basket.addAProductToBasket(p1);
        basket.addAProductToBasket(p3);
        basket.addAProductToBasket(p3);
        basket.addAProductToBasket(p4);
        assertEquals(4.25, basket.calculateSubtotalOfProductsInTheBasket(), 0.0);
    }

    @Test
    public void canCalculateAppleDiscount(){
        // if Apple is not purchased (Bread is)
        basket.addAProductToBasket(p2);
        assertEquals(0, basket.calculateAppleDiscount(), 0.0);
        basket.removeAProductFromBasket(p2);

        // if 1 Apple
        basket.addAProductToBasket(p4);
        assertEquals(0.10, basket.calculateAppleDiscount(), 0.0);
        basket.removeAProductFromBasket(p4);

        // if 2 Apples
        basket.addAProductToBasket(p4);
        basket.addAProductToBasket(p4);
        assertEquals(0.20, basket.calculateAppleDiscount(), 0.01);
        basket.removeAProductFromBasket(p4);
        basket.removeAProductFromBasket(p4);

        // if 2 Apples along with other products
        basket.addAProductToBasket(p4);
        basket.addAProductToBasket(p1);
        basket.addAProductToBasket(p4);
        assertEquals(0.20, basket.calculateAppleDiscount(), 0.01);
//        basket.removeAProductFromBasket(p4);
//        basket.removeAProductFromBasket(p1);
//        basket.removeAProductFromBasket(p4);
    }

    @Test
    public void canCalculateBreadDiscount(){
        // 2 Soups but no bread is purchased
        basket.addAProductToBasket(p1);
        basket.addAProductToBasket(p1);
        assertEquals(0, basket.calculateBreadDiscount(), 0.0);
        basket.removeAProductFromBasket(p1);
        basket.removeAProductFromBasket(p1);

        // No soup & 1 bread
        basket.addAProductToBasket(p2);
        assertEquals(0.0, basket.calculateBreadDiscount(), 0.0);
        basket.removeAProductFromBasket(p2);

        // 1 soup & 1 bread - no discount
        basket.addAProductToBasket(p1);
        basket.addAProductToBasket(p2);
        assertEquals(0.0, basket.calculateBreadDiscount(), 0.0);
        basket.removeAProductFromBasket(p2);
        basket.removeAProductFromBasket(p1);

        // if 2 Soups & 1 bread - 1 bread gets discount
        basket.addAProductToBasket(p1);
        basket.addAProductToBasket(p1);
        basket.addAProductToBasket(p2);
        assertEquals(0.40, basket.calculateBreadDiscount(), 0.0);
        basket.removeAProductFromBasket(p2);
        basket.removeAProductFromBasket(p1);
        basket.removeAProductFromBasket(p1);

        // if 2 Soups & 2 bread - only 1 bread gets discount
        basket.addAProductToBasket(p1);
        basket.addAProductToBasket(p1);
        basket.addAProductToBasket(p2);
        basket.addAProductToBasket(p2);
        assertEquals(0.40, basket.calculateBreadDiscount(), 0.0);
        basket.removeAProductFromBasket(p2);
        basket.removeAProductFromBasket(p2);
        basket.removeAProductFromBasket(p1);
        basket.removeAProductFromBasket(p1);

        // if 3 Soups & 1 bread - 1 bread gets discount
        basket.addAProductToBasket(p1);
        basket.addAProductToBasket(p1);
        basket.addAProductToBasket(p1);
        basket.addAProductToBasket(p2);
        assertEquals(0.40, basket.calculateBreadDiscount(), 0.0);
        basket.removeAProductFromBasket(p2);
        basket.removeAProductFromBasket(p1);
        basket.removeAProductFromBasket(p1);
        basket.removeAProductFromBasket(p1);

        // if 3 Soups & 2 bread - only 1 bread gets discount
        basket.addAProductToBasket(p1);
        basket.addAProductToBasket(p1);
        basket.addAProductToBasket(p1);
        basket.addAProductToBasket(p2);
        basket.addAProductToBasket(p2);
        assertEquals(0.40, basket.calculateBreadDiscount(), 0.0);
        basket.removeAProductFromBasket(p2);
        basket.removeAProductFromBasket(p2);
        basket.removeAProductFromBasket(p1);
        basket.removeAProductFromBasket(p1);
        basket.removeAProductFromBasket(p1);


        // if 4 Soups & 1 bread - 1 bread gets discount
        basket.addAProductToBasket(p1);
        basket.addAProductToBasket(p1);
        basket.addAProductToBasket(p1);
        basket.addAProductToBasket(p1);
        basket.addAProductToBasket(p2);
        assertEquals(0.40, basket.calculateBreadDiscount(), 0.0);
        basket.removeAProductFromBasket(p2);
        basket.removeAProductFromBasket(p1);
        basket.removeAProductFromBasket(p1);
        basket.removeAProductFromBasket(p1);
        basket.removeAProductFromBasket(p1);

        // if 4 Soups & 2 bread - 2 bread gets discount
        basket.addAProductToBasket(p1);
        basket.addAProductToBasket(p1);
        basket.addAProductToBasket(p1);
        basket.addAProductToBasket(p1);
        basket.addAProductToBasket(p2);
        basket.addAProductToBasket(p2);
        assertEquals(0.80, basket.calculateBreadDiscount(), 0.0);
        basket.removeAProductFromBasket(p2);
        basket.removeAProductFromBasket(p2);
        basket.removeAProductFromBasket(p1);
        basket.removeAProductFromBasket(p1);
        basket.removeAProductFromBasket(p1);
        basket.removeAProductFromBasket(p1);
    }
}

