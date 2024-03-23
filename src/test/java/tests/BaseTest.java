package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterMethod;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeMethod;
import pages.*;
import io.qameta.allure.Attachment;

public abstract class BaseTest {
    WebDriver m_driver;
    LoginPage m_loginPage;
    Products m_products;
    ProductPage m_productPage;
    ShoppingCart m_shoppingCart;
    MenuPage m_menu;
    SaucelabsWebPage m_SaucelabsWebPage;
    CheckoutPage m_checkout;
    CheckoutOverview m_overview;
    CompletePage m_complete;


    @BeforeMethod
    public void setupForPages(ITestContext testContext) {
        m_driver = WebDriverManager.chromedriver().create();
        testContext.setAttribute("webDriver",this.m_driver);
        m_driver.manage().window().maximize();
        m_driver.get("https://www.saucedemo.com/");
        m_loginPage = new LoginPage(m_driver);
        m_products = new Products(m_driver);
        m_productPage = new ProductPage(m_driver);
        m_shoppingCart = new ShoppingCart(m_driver);
        m_menu = new MenuPage(m_driver);
        m_SaucelabsWebPage = new SaucelabsWebPage(m_driver);
        m_checkout = new CheckoutPage(m_driver);
        m_overview = new CheckoutOverview(m_driver);
        m_complete = new CompletePage(m_driver);
    }

    @AfterMethod
    public void tearDown() {
        m_driver.quit();
    }

    @Attachment
    public  static String addTextAttachment(String message){
        return message;
    }


}


