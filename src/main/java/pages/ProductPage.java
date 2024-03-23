package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage {
    @FindBy(css = ".inventory_details_name")
    private WebElement m_titleName;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    @Step("nameOfProduct: {0}")
    public boolean anExistingProduct(String nameOfProduct) {
        boolean exist = false;
        String name = getText(m_titleName);
        if (name.contentEquals(nameOfProduct)) {
            exist = true;
        }
        return exist;
    }
}
