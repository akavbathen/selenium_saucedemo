package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MenuPage extends BasePage {
    @FindBy(css = ".bm-burger-button")
    private WebElement m_burgetBtn;
    @FindBy(css = "[id^=about]")
    private WebElement m_aboutBtn;
    @FindBy(css = "[id^=logout]")
    private WebElement m_logoutBtn;

    public MenuPage(WebDriver driver) {
        super(driver);
    }

    public void enteringTheMenu() {
        click(m_burgetBtn);
        waiting(2000);
    }

    public void about() {
        click(m_aboutBtn);
    }

    public void logout() {
        click(m_logoutBtn);
    }
}
