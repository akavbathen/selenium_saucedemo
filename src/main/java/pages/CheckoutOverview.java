package pages;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.ArrayList;
import java.util.List;



public class CheckoutOverview extends BasePage {
    @FindBy(css = ".title")
    private WebElement m_titlePage;
    @FindBy(css = ".cart_item")
    private List<WebElement> m_listElement;
    @FindBy(css = ".summary_subtotal_label")
    private WebElement m_totalPrice;
    @FindBy(css = ".summary_tax_label")
    private WebElement m_tax;
    @FindBy(css = ".summary_info_label.summary_total_label")
    private WebElement m_total;
    @FindBy(css = "#cancel")
    private WebElement m_cancelBtn;
    @FindBy(css = "#finish")
    private WebElement m_finishBtn;

    public CheckoutOverview(WebDriver driver) {
        super(driver);
    }

    @Step("the title of page:{name}")
    public boolean titleOfThePage(String name) {
        return getText(m_titlePage).contentEquals(name);
    }

    public ArrayList<WebElement> getListOfWebelemnt() {
        ArrayList<WebElement> list = new ArrayList<WebElement>();
        for (WebElement w : m_listElement) {
            list.add(w);
        }
        return list;
    }

    @Step("ArrayList of WebelElment:{0}")
    public ArrayList<String> getListOfNameAPrice(ArrayList<WebElement> list) {
        ArrayList<String> nameAprice = new ArrayList<String>();

        for (WebElement s : list) {
            WebElement name = s.findElement(By.cssSelector(".inventory_item_name"));
            nameAprice.add(name.getText());
            String price = s.findElement(By.cssSelector(".inventory_item_price")).getText();

            String priceWithoutDollar = getFilaString("$", price);
            nameAprice.add(priceWithoutDollar);
        }
        return nameAprice;
    }

    public double sumOfTheProducts() {
        double sum = 0;
        ArrayList<WebElement> elem = getListOfWebelemnt();
        for (WebElement w : elem) {
            String price = w.findElement(By.cssSelector(".inventory_item_price")).getText();
            String priceWithoutDollar = getFilaString("$", price);

            sum += Double.parseDouble(priceWithoutDollar);

        }
        return sum;
    }

    public String getPriceTotal() {
        String totalPrice = m_totalPrice.getText();
        String priceWithoutDollar = getFilaString("Item total: $", totalPrice);

        return priceWithoutDollar;
    }

    public double toCalculateTax() {
        String sum = getPriceTotal();
        double value = Double.parseDouble(sum);

        double percent = 0.08;
        double wholeValue = value * percent;

        String formattedNumber = String.format("%.2f", wholeValue);
        double tax = Double.parseDouble(formattedNumber);

        return tax;
    }

    public double getTax() {
        String getTax = m_tax.getText();
        String priceWithoutDollar = getFilaString("Tax: $", getTax);
        double tax = Double.parseDouble(priceWithoutDollar);

        return tax;
    }

    public double total() {
        String total = m_total.getText();
        String priceWithoutDollar = getFilaString("Total: $", total);
        double newTotal = Double.parseDouble(priceWithoutDollar);

        return newTotal;
    }

    public void cancelBtn() {
        click(m_cancelBtn);
    }

    public void finish() {
        click(m_finishBtn);
    }
}