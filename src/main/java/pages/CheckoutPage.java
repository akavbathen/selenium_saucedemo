package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends BasePage {
    @FindBy(css = ".title")
    public WebElement m_title;
    @FindBy(css = "#first-name")
    private WebElement m_firstNameFiled;
    @FindBy(css = "#last-name")
    private WebElement m_lastNameFiled;
    @FindBy(css = "#postal-code")
    private WebElement m_postalNameFiled;
    @FindBy(css = "#continue")
    private WebElement m_continueBtn;
    @FindBy(css = "#cancel")
    private WebElement m_cancelBtn;
    @FindBy(css = "h3")
    private WebElement m_error;


    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    @Step("details with with firstName: {0} and lastName: {1} and postal {3}")
    public void fillingInDetails(String firstName, String lastName, String postal) {
        fillText(m_firstNameFiled, firstName);
        fillText(m_lastNameFiled, lastName);
        fillText(m_postalNameFiled, postal);
    }

    public void toContinue() {
        click(m_continueBtn);
    }

    public void cancel() {
        click(m_cancelBtn);
    }

    public String errorMessage() {
        String name = getText(m_error);
        return name;
    }
}
