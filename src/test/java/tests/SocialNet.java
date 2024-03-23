package tests;

import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.Set;
import static org.testng.Assert.assertEquals;

@Owner("Bathen")
public class SocialNet extends BaseTest {
    @DataProvider(name = "SocialNetworks")
    public Object[][] data() {
        return new Object[][]{
                {".social_twitter>a", "https://twitter.com/saucelabs"},
                {".social_facebook>a", "https://www.facebook.com/saucelabs"},
                {".social_linkedin>a", "https://www.linkedin.com/company/sauce-labs/"}
        };
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(dataProvider = "SocialNetworks",description = "Checking connection to social networks")
    public void tc01_connectingWithSocialNetworks(String elem, String URL) {
        m_loginPage.login("standard_user", "secret_sauce");
        m_products.waiting(4000);
        WebElement linkElement = m_driver.findElement(By.cssSelector(elem));
        m_products.click(linkElement);
        m_products.waiting(2000);
        String main = m_driver.getWindowHandle();
        Set<String> list = m_driver.getWindowHandles();
        for (String string : list ) {
            m_driver.switchTo().window(string);
        }
        String currentURL = m_driver.getCurrentUrl();
        m_driver.close();
        m_driver.switchTo().window(main);
        assertEquals(currentURL, URL);
    }
}
