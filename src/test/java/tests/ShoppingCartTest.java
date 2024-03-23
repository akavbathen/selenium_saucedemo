package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

import java.util.ArrayList;

@Owner("Bathen")
public class ShoppingCartTest extends BaseTest {
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Checking after clicking the \"Add to cart\" button, the product is added to the shopping cart")
    public void tc01_addProductToShoppingCart() {
        m_loginPage.login("standard_user", "secret_sauce");
        m_products.addToCart("Sauce Labs Onesie");
        m_products.clickOnShoppingCart();
        assertTrue(m_shoppingCart.productInShoppingCart("Sauce Labs Onesie"));
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Return to the products page to add a product, and check that all the selected products have changed status")
    public void tc02_continueShopping() {
        m_loginPage.login("standard_user", "secret_sauce");
        m_products.addToCart("Sauce Labs Onesie");
        m_products.clickOnShoppingCart();
        m_shoppingCart.continueShopping();
        m_products.addToCart("Sauce Labs Fleece Jacket");
        assertTrue(m_products.buttonStatusIsRemoved("Sauce Labs Onesie"));
        assertTrue(m_products.buttonStatusIsRemoved("Sauce Labs Fleece Jacket"));
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Checking that all the selected products have been added to the cart")
    public void tc03_allProductsAdded() {
        String prod1 = "Sauce Labs Onesie";
        String prod2 = "Sauce Labs Fleece Jacket";
        m_loginPage.login("standard_user", "secret_sauce");
        m_products.addToCart(prod1);
        m_products.addToCart(prod2);
        m_products.clickOnShoppingCart();
        ArrayList<String> array = m_shoppingCart.productsInShoppingCart();
        ArrayList<String> array2 = new ArrayList<>();
        array2.add(prod1);
        array2.add(prod2);
        assertTrue(m_shoppingCart.arrayListAreEqual(array, array2));
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Removing a product from the cart and then a new check in the cart only products that have been added and not removed.")
    public void tc04_removingProductsFromTheCart() {
        m_loginPage.login("standard_user", "secret_sauce");
        ArrayList<String> producst = new ArrayList<>();
        producst.add("Sauce Labs Bolt T-Shirt");
        producst.add("Sauce Labs Backpack");
        producst.add("Sauce Labs Bike Light");
        m_products.addProductsToCart(producst);
        m_products.clickOnShoppingCart();
        String pr = producst.remove(0);
        m_shoppingCart.removeFromShoppingCart(pr);
        ArrayList<String> producstInShoppingCart = m_shoppingCart.productsInShoppingCart();
        assertEquals(producstInShoppingCart, producst);
    }

    @Severity(SeverityLevel.MINOR)
    @Test(description = "Checkout button test")
    public void tc05_clickCheckout() {
        String title = "Checkout: Your Information";
        m_loginPage.login("standard_user", "secret_sauce");
        String prodOne = "Sauce Labs Onesie";
        m_products.addToCart(prodOne);
        m_products.clickOnShoppingCart();
        m_shoppingCart.checkout();
        String name = m_shoppingCart.getTheNameOfTitlePage(m_checkout.m_title);
        assertEquals(name, title);
    }
}
