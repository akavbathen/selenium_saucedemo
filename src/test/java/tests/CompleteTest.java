package tests;

import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.testng.Assert.assertEquals;

@Owner("Bathen")
public class CompleteTest extends BaseTest {
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "reach the end of the purchase and then return to the product page")
    public void tc01_backHome() {
        m_loginPage.login("standard_user", "secret_sauce");
        m_products.addToCart("Sauce Labs Bike Light");
        m_products.addToCart("Sauce Labs Backpack");
        m_products.clickOnShoppingCart();
        m_shoppingCart.checkout();
        m_checkout.fillingInDetails("Bathen", "Akav", "1234");
        m_checkout.toContinue();
        m_overview.finish();
        m_complete.backHome();
        String name = m_complete.getTheNameOfTitlePage(m_products.m_productsPage);
        assertEquals(name, "Products");
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(description = "After completing the purchase process, check that there are no products left in the cart")
    public void tc02_cartEmptyOfProducts() {
        m_loginPage.login("standard_user", "secret_sauce");
        m_products.addToCart("Sauce Labs Bike Light");
        m_products.addToCart("Sauce Labs Backpack");
        m_products.clickOnShoppingCart();
        m_shoppingCart.checkout();
        m_checkout.fillingInDetails("Bathen", "Akav", "1234");
        m_checkout.toContinue();
        m_overview.finish();
        m_products.clickOnShoppingCart();
        ArrayList<String> myRet = m_shoppingCart.productsInShoppingCart();
        addTextAttachment("Expect an empty cart");
        assertEquals(myRet.size(), 0);
    }
}
