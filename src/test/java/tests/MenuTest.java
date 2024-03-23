package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


@Epic("Menu")
@Feature("options shown in the menu")
@Owner("Bathen")
public class MenuTest extends BaseTest {
    @Severity(SeverityLevel.TRIVIAL)
    @Story("Clicking the About button and transferring to the page")
    @Test
    public void tc01_clickingOnAboutBtn() {
        m_loginPage.login("standard_user", "secret_sauce");
        m_menu.enteringTheMenu();
        m_menu.about();
        assertTrue(m_SaucelabsWebPage.getUrl("https://saucelabs.com/"));
    }

    @Severity(SeverityLevel.TRIVIAL)
    @Story("Clicking the Logout button and transferring to the page")
    @Test
    public void tc02_clickingOnLogoutBtn() {
        m_loginPage.login("standard_user", "secret_sauce");
        m_menu.enteringTheMenu();
        m_menu.logout();
        assertTrue(m_menu.getUrl("https://www.saucedemo.com/"));
    }
}
