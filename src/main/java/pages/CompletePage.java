package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CompletePage extends BasePage {
    @FindBy(css = ".complete-header")
    public WebElement m_titlePage;
    @FindBy(css = ".btn.btn_primary.btn_small")
    private WebElement m_backBtn;

    public CompletePage(WebDriver driver) {
        super(driver);
    }

    public void backHome() {
        click(m_backBtn);
    }
}
