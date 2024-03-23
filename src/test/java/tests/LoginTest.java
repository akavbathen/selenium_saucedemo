package tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

@Epic("Security")
@Feature("try to connect")
@Owner("Bathen")
public class LoginTest extends BaseTest {
    @DataProvider(name = "loginData")
    public Object[][] createData() {
        return new Object[][]{
                {"standard_user", "secret_sauce"},
                {"problem_user", "secret_sauce"},
                {"performance_glitch_user", "secret_sauce"},
                {"visual_user", "secret_sauce"}
        };
    }

    @Severity(SeverityLevel.CRITICAL)
    @Story("Attempting to login to the system with correct details")
    @Test(dataProvider = "loginData")
    public void tc01_login(String username, String password) {
        m_loginPage.login(username, password);
        m_loginPage.waiting(2000);
        String name = m_loginPage.getTheNameOfTitlePage(m_products.m_productsPage);
        assertEquals(name, "Products");
    }

    @DataProvider(name = "UsernameNotExist")
    public Object[][] Data() {
        return new Object[][]{
                {"locked_out_user", "secret_sauce"},
        };
    }

    @Severity(SeverityLevel.NORMAL)
    @Story("Attempting to login to the system with uncorrect details")
    @Test(dataProvider = "UsernameNotExist")
    public void tc02_notConnected(String username, String password) {
        String popUp = "Epic sadface: Sorry, this user has been locked out.";
        m_loginPage.login(username, password);
        m_loginPage.waiting(2000);
        addTextAttachment("An expected result is that the login attempt will fail");
        String name = m_loginPage.errorPopUp();
        assertEquals(name, popUp);
    }


    @Severity(SeverityLevel.NORMAL)
    @Story("Pop-up when login error")
    @Test(description = "Checking a pop-up message with an empty field")
    public void tc03_loginWithoutAPassword() {
        m_loginPage.login("standard_user", "");
        String error = m_loginPage.errorPopUp();
        addTextAttachment("An expected result is that the login attempt will fail");
        assertEquals(error, "Epic sadface: Password is required");
    }
}
