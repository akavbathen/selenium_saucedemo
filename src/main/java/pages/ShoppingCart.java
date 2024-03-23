package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart extends BasePage {
    @FindBy(css = ".cart_item")
    private List<WebElement> m_cartItem;
    @FindBy(css = "#continue-shopping")
    private WebElement m_continueShopping;
    @FindBy(css = "#checkout")
    private WebElement m_checkoutBtn;
    @FindBy(css = ".title")
    public WebElement m_titlePage;

    public ShoppingCart(WebDriver driver) {
        super(driver);
    }

    @Step("product:{0}")
    public boolean productInShoppingCart(String nameOfProduct) {
        for (WebElement w : m_cartItem) {
            WebElement name = w.findElement(By.cssSelector(".inventory_item_name"));
            if (getText(name).contentEquals(nameOfProduct)) {
                return true;
            }
        }
        return false;
    }

    public void continueShopping() {
        click(m_continueShopping);
    }

    public ArrayList<String> productsInShoppingCart() {
        ArrayList<String> myRet = new ArrayList<>();
        for (WebElement w : m_cartItem) {
            String name = w.findElement(By.cssSelector(".inventory_item_name")).getText();
            myRet.add(name);
        }
        return myRet;
    }

    @Step("product:{0}")
    public void removeFromShoppingCart(String nameOfProduct) {
        for (WebElement w : m_cartItem) {
            WebElement name = w.findElement(By.cssSelector(".inventory_item_name"));
            if (name.getText().contentEquals(nameOfProduct)) {
                w.findElement(By.cssSelector("[id^=remove]")).click();
            }
        }
    }

    public void checkout() {
        click(m_checkoutBtn);
    }
}
