package tests;

import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Owner("Bathen")
public class ProductsTest extends BaseTest {
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Checking that the selected product has add to the shopping cart")
    public void tc01_productSelection() {
        m_loginPage.login("standard_user", "secret_sauce");
        m_products.productSelection("Sauce Labs Bolt T-Shirt");
        assertTrue(m_productPage.anExistingProduct("Sauce Labs Bolt T-Shirt"));
    }

    @Severity(SeverityLevel.MINOR)
    @Test(description = "Changing the status of a product selected to remove")
    public void tc02_addToCartAndRemovalStatus() {
        m_loginPage.login("standard_user", "secret_sauce");
        m_products.addToCart("Sauce Labs Bolt T-Shirt");
        assertTrue(m_products.buttonStatusIsRemoved("Sauce Labs Bolt T-Shir"));
    }


    @Test
    public void tc03_filter() throws InterruptedException {
        m_loginPage.login("standard_user", "secret_sauce");
        List<String> sortBeforeFilter = m_products.createArray();
        String[] array = sortBeforeFilter.toArray(new String[0]);
        m_products.sortOnProducts(array, true);
        m_products.filteringByValue("za");
        m_products.waiting(3000);
        m_products.updateProductList();
        List<String> sortAfterFilter = m_products.createArray();
        String[] array2 = sortBeforeFilter.toArray(new String[0]);
        assertTrue(Arrays.equals(array, array2));
    }

    @Severity(SeverityLevel.MINOR)
    @Test(description = "Canceling one of the selected products and returning it to \"AddToCart\" status")
    public void tc04_addingAndCancelIngAproduct() {
        String product = "Sauce Labs Bolt T-Shirt";
        m_loginPage.login("standard_user", "secret_sauce");
        m_products.addToCart(product);
        m_products.addToCart("Sauce Labs Bike Light");
        m_products.removeaProduct(product);
        assertTrue(m_products.productInAddStatus(product));
    }

    @Severity(SeverityLevel.TRIVIAL)
    @Test(description = "Checking that the products are sorted according to Z-A")
    public void tc05_filterByNameZ_A() {
        m_loginPage.login("standard_user", "secret_sauce");
        m_products.filteringByValue("za");
        String expected = "Test.allTheThings() T-Shirt (Red)";
        String name = m_products.getProductNameOfIndex(0);
        assertEquals(name, expected);
    }

    @Severity(SeverityLevel.TRIVIAL)
    @Test(description = "Checking that the products are sorted according to A-Z")
    public void tc06_filterByNameA_Z() {
        m_loginPage.login("standard_user", "secret_sauce");
        m_products.filteringByValue("az");
        String name = m_products.retrainNameOfProduct(0);
        String expected = "Sauce Labs Backpack";
        assertEquals(name, expected);
    }

    @Severity(SeverityLevel.TRIVIAL)
    @Test(description = "Checking that the products are sorted from the lowest price to the highest price")
    public void tc07_filterByLowPrice() {
        m_loginPage.login("standard_user", "secret_sauce");
        m_products.filteringByValue("lohi");
        ArrayList<String> list = m_products.getAListOfPrices();
        ArrayList<String> ExpectedList = new ArrayList<>();
        ExpectedList.add("7.99");
        ExpectedList.add("9.99");
        ExpectedList.add("15.99");
        ExpectedList.add("15.99");
        ExpectedList.add("29.99");
        ExpectedList.add("49.99");
        assertEquals(list, ExpectedList);
    }

    @Severity(SeverityLevel.TRIVIAL)
    @Test(description = "Checking that the products are sorted from the highest price to the lowest price")
    public void tc08_filterByHighPrice() {
        m_loginPage.login("standard_user", "secret_sauce");
        m_products.filteringByValue("hilo");
        ArrayList<String> list = m_products.getAListOfPrices();
        ArrayList<String> ExpectedList = new ArrayList<>();
        ExpectedList.add("49.99");
        ExpectedList.add("29.99");
        ExpectedList.add("15.99");
        ExpectedList.add("15.99");
        ExpectedList.add("9.99");
        ExpectedList.add("7.99");
        assertEquals(list, ExpectedList);
    }
}
