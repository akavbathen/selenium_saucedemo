package tests;

import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Owner("Bathen")
public class CheckoutTest extends BaseTest {
    @Severity(SeverityLevel.MINOR)
    @Test
    public void tc01_filedYourInformation() {
        String product = "Sauce Labs Bolt T-Shirt";
        m_loginPage.login("standard_user", "secret_sauce");
        m_products.addToCart("Sauce Labs Bike Light");
        m_products.clickOnShoppingCart();
        m_shoppingCart.checkout();
        m_checkout.fillingInDetails("Bathen", "Akav", "1234");
        m_checkout.toContinue();
        assertTrue(m_overview.titleOfThePage("Checkout: Overview"));
    }

    @Severity(SeverityLevel.MINOR)
    @Test(description = "Return to shopping cart by clicking on cancelBtn")
    public void tc02_cancel() {
        String product = "Sauce Labs Bolt T-Shirt";
        m_loginPage.login("standard_user", "secret_sauce");
        m_products.addToCart("Sauce Labs Bike Light");
        m_products.clickOnShoppingCart();
        m_shoppingCart.checkout();
        m_checkout.cancel();
        String name = m_checkout.getTheNameOfTitlePage(m_shoppingCart.m_titlePage);
        addTextAttachment("Back to shopping cart");
        assertEquals(name, "Your Cart");
    }

    @Severity(SeverityLevel.MINOR)
    @Test(description = "Return to shopping cart by clicking on CartBtn")
    public void tc03_backToShoppingCart() {
        String product = "Sauce Labs Bolt T-Shirt";
        m_loginPage.login("standard_user", "secret_sauce");
        m_products.addToCart("Sauce Labs Bike Light");
        m_products.clickOnShoppingCart();
        m_shoppingCart.checkout();
        m_products.clickOnShoppingCart();
        String name = m_checkout.getTheNameOfTitlePage(m_shoppingCart.m_titlePage);
        assertEquals(name, "Your Cart");
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Filling in some details returns an error")
    public void tc04_fillInSomeOfTheDetails() {
        String product = "Sauce Labs Bolt T-Shirt";
        m_loginPage.login("standard_user", "secret_sauce");
        m_products.addToCart("Sauce Labs Bike Light");
        m_products.clickOnShoppingCart();
        m_shoppingCart.checkout();
        m_checkout.fillingInDetails("Bathen", "Akav", "");
        addTextAttachment("There are missing details");
        m_checkout.toContinue();
        assertEquals(m_checkout.errorMessage(), "Error: Postal Code is required");
    }
}