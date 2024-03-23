package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import utilities.AllureAttachment;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Owner("Bathen")
public class CheckoutOverviewTest extends BaseTest {
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "The name and price of the selected products")
    public void tc01_toGetNameAndPrice() {
        m_loginPage.login("standard_user", "secret_sauce");
        m_products.addToCart("Sauce Labs Onesie");
        m_products.addToCart("Sauce Labs Backpack");
        m_products.clickOnShoppingCart();
        m_shoppingCart.checkout();
        m_checkout.fillingInDetails("Bathen", "Akav", "1234");
        m_checkout.toContinue();
        ArrayList<WebElement> elem = m_overview.getListOfWebelemnt();
        ArrayList<String> nameAndPrice = m_overview.getListOfNameAPrice(elem);
        String[] products = {"Sauce Labs Onesie", "7.99", "Sauce Labs Backpack", "29.99"};
        List<String> expectedList = Arrays.asList(products);
        assertEquals(expectedList, nameAndPrice);
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Checking that the final price corresponds to the price of the products and the tax.")
    public void tc02_totalPrice() {
        m_loginPage.login("standard_user", "secret_sauce");
        m_products.addToCart("Sauce Labs Bike Light");
        m_products.addToCart("Sauce Labs Backpack");
        m_products.clickOnShoppingCart();
        m_shoppingCart.checkout();
        m_checkout.fillingInDetails("Bathen", "Akav", "1234");
        m_checkout.toContinue();
        double sum = m_overview.sumOfTheProducts();
        String str = Double.toString(sum);
        String totalPrice = m_overview.getPriceTotal();
        assertEquals(str, totalPrice);
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Checking the tax calculation from the product price")
    @Description("Calculate the tax on the product and check that the total tax is correct")
    public void tc03_calculateTax() {
        m_loginPage.login("standard_user", "secret_sauce");
        //m_products.addToCart("Sauce Labs Bike Light");
        m_products.addToCart("Sauce Labs Backpack");
        m_products.clickOnShoppingCart();
        m_shoppingCart.checkout();
        m_checkout.fillingInDetails("Bathen", "Akav", "1234");
        m_checkout.toContinue();
        double calculateTax = m_overview.toCalculateTax();
        double tax = m_overview.getTax();
        addTextAttachment("to get A equals tax");
        assertEquals(calculateTax, tax);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Checking that the total matches the items in the cart including the tax.")
    public void tc04_getTotal() {
        m_loginPage.login("standard_user", "secret_sauce");
        m_products.addToCart("Sauce Labs Bike Light");
        m_products.addToCart("Sauce Labs Backpack");
        m_products.clickOnShoppingCart();
        m_shoppingCart.checkout();
        m_checkout.fillingInDetails("Bathen", "Akav", "1234");
        m_checkout.toContinue();
        String price = m_overview.getPriceTotal();
        double totalPrice = Double.parseDouble(price);
        double tax = m_overview.toCalculateTax();
        double total = totalPrice + tax;
        double expected = m_overview.total();
        addTextAttachment("to get A equals total");
        assertEquals(total, expected);
    }

    @Severity(SeverityLevel.TRIVIAL)
    @Test(description = "Go to the shopping cart and go back to the product page")
    public void tc05_cancel() {
        m_loginPage.login("standard_user", "secret_sauce");
        m_products.addToCart("Sauce Labs Bike Light");
        m_products.addToCart("Sauce Labs Backpack");
        m_products.clickOnShoppingCart();
        m_shoppingCart.checkout();
        m_checkout.fillingInDetails("Bathen", "Akav", "1234");
        m_checkout.toContinue();
        m_overview.cancelBtn();
        String name = m_overview.getTheNameOfTitlePage(m_products.m_productsPage);
        addTextAttachment("back to the products page");
        assertEquals(name, "Product");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "End-to-end process")
    public void tc06_toFinish() {
        m_loginPage.login("standard_user", "secret_sauce");
        m_products.addToCart("Sauce Labs Bike Light");
        m_products.addToCart("Sauce Labs Backpack");
        m_products.clickOnShoppingCart();
        m_shoppingCart.checkout();
        m_checkout.fillingInDetails("Bathen", "Akav", "1234");
        m_checkout.toContinue();
        m_overview.finish();
        String name = m_overview.getTheNameOfTitlePage(m_complete.m_titlePage);
        assertEquals(name, "Thank you for your order!");
        AllureAttachment.attachURL("https://www.saucedemo.com/checkout-complete.html");
    }
}
