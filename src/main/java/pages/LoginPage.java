package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    @FindBy(css = "#user-name")
    private WebElement m_userNameFiled;
    @FindBy(css = "#password")
    private WebElement m_passwordFiled;
    @FindBy(css = "#login-button")
    private WebElement m_loginFiled;
    @FindBy(css = "h3")
    private WebElement m_popUp;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("user:{0} and password:{1}")
    public void login(String user, String password) {
        fillText(m_userNameFiled, user);
        fillText(m_passwordFiled, password);
        click(m_loginFiled);
    }

    public String errorPopUp() {
        String popup = getText(m_popUp);
        return popup;
    }
}


