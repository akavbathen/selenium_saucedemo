package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Products extends BasePage {
    @FindBy(css = ".title")
    public WebElement m_productsPage;
    @FindBy(css = ".inventory_item")
    public List<WebElement> m_productsList;
    @FindBy(css = ".shopping_cart_link")
    private WebElement m_shoppingCartBtn;
    @FindBy(css = ".inventory_item_name")
    private List<WebElement> m_nameOfProductsList;

    public Products(WebDriver driver) {
        super(driver);
    }

    @Step("product: {0}")
    public void productSelection(String nameOfProduct) {
        for (WebElement webElement : m_productsList) {
            WebElement name = webElement.findElement(By.cssSelector(".inventory_item_name"));
            if (getText(name).contentEquals(nameOfProduct)) {
                name.click();
                break;
            }
        }
    }

    @Step("name Of Product:{0}")
    public void addToCart(String nameOfProduct) {
        for (WebElement webElement : m_productsList) {
            WebElement name = webElement.findElement(By.cssSelector(".inventory_item_name"));
            if (getText(name).contentEquals(nameOfProduct)) {
                click(webElement.findElement(By.cssSelector("[id^='add-to-cart-']")));
                break;
            }
        }
    }

    @Step("nameOfProduct{0}")
    public boolean buttonStatusIsRemoved(String nameOfProduct) {
        for (WebElement webElement : m_productsList) {
            WebElement name = webElement.findElement(By.cssSelector(".inventory_item_name"));
            if (getText(name).contentEquals(nameOfProduct)) {
                WebElement name2 = webElement.findElement(By.cssSelector(".btn.btn_secondary.btn_small"));
                return getText(name2).contentEquals("Remove");
            }
        }
        return false;
    }

    public void clickOnShoppingCart() {
        click(m_shoppingCartBtn);
    }

    @Step("filter by{0}")
    public void filteringByValue(String filter) {
        WebElement Element = m_driver.findElement(By.cssSelector(".product_sort_container"));
        Select s = new Select(Element);
        s.selectByValue(filter);
    }

    @Step("my Arry:{0} and boolean:{1}")
    public void sortOnProducts(String[] myArry, boolean reverse) {
        if (reverse == true) {
            Arrays.sort(myArry, Collections.reverseOrder());
        } else {
            Arrays.sort(myArry);
        }
    }

    public List<String> createArray() {
        List<String> myRet = new ArrayList<>();
        for (WebElement e : m_productsList) {
            WebElement name = e.findElement(By.cssSelector(".inventory_item_name"));
            myRet.add(name.getText());
        }
        return myRet;
    }

    public void updateProductList() {
        m_productsList = m_driver.findElements(By.cssSelector(".inventory_item"));
    }

    @Step("product:{0}")
    public boolean productInAddStatus(String nameOfProduct) {
        for (WebElement webElement : m_productsList) {
            WebElement name = webElement.findElement(By.cssSelector(".inventory_item_name"));
            if (getText(name).contentEquals(nameOfProduct)) {
                WebElement name2 = webElement.findElement(By.cssSelector("[id^=add-to]"));
                return name2.getText().contentEquals("Add to cart");
            }
        }
        return false;
    }

    @Step("product:{0}")
    public void removeaProduct(String nameOfProduct) {
        for (WebElement w : m_productsList) {
            WebElement name = w.findElement(By.cssSelector(".inventory_item_name"));
            if (name.getText().contentEquals(nameOfProduct)) {
                click(w.findElement(By.cssSelector("[id^=remove]")));
            }
        }
    }

    @Step("ArrayList:{0}")
    public void addProductsToCart(ArrayList<String> myRet) {
        for (String s : myRet) {
            addToCart(s);
        }
    }

    @Step("index:{0}")
    public String getProductNameOfIndex(int index) {
        WebElement web = m_productsList.get(index);
        WebElement name = web.findElement(By.cssSelector(".inventory_item_name"));
        return getText(name);
    }

    @Step("index:{0}")
    public String retrainNameOfProduct(int index) {
        WebElement w = m_nameOfProductsList.get(index);
        String name = getText(w);
        return name;
    }

    public ArrayList<String> getAListOfPrices() {
        ArrayList<String> products = new ArrayList<>();
        for (WebElement w : m_productsList) {
            String price = w.findElement(By.cssSelector(".inventory_item_price")).getText();
            String priceWithoutDollar = price.replace("$", "");
            products.add(priceWithoutDollar);
        }
        return products;
    }
}