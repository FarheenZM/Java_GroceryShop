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
    
}
