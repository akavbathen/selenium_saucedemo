package pages;

import java.lang.reflect.Array;
import java.time.Duration;
import java.util.Random;

import io.qameta.allure.Attachment;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;

public class BasePage {

	public WebDriver m_driver;
	WebDriverWait m_wait;
	Actions m_mouse;
	JavascriptExecutor m_js;

	

	public BasePage(WebDriver driver) {
		this.m_driver = driver;
		PageFactory.initElements(driver, this);
		m_wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		m_mouse = new Actions(driver);
		m_js = (JavascriptExecutor) driver;
	}
	
	
	public void login(WebElement el, String text) {
		el.clear();
		el.sendKeys(text);
		
	}
	
	public void click(WebElement el) {
		//highlightElement(el, "yellow");
		//waitForElementToBeClickable(el);
		el.click();
		waiting(500);
		
	}
	
	public String getText(WebElement el) {
		return el.getText();
		
	}
	
	public void fillText(WebElement el, String text) {
		waiting(300);
		el.clear();
		el.sendKeys(text);
	}
	
	public void sleep() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}

	}
	
	public void waiting(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// wait
		public void waitForElementToBeClickable(WebElement el) {
			m_wait.until(ExpectedConditions.elementToBeClickable(el));
		}


		//mouse
		public void mouseMoveToElement(WebElement el) {
			//highlightElement(el, "green");
			m_mouse.moveToElement(el).build().perform();
		}
		
		
		public void scroll() {
			m_js.executeScript("window.scrollBy(0,2000)", "");
		}
		
		
		
		/*
		 * Call this method with your element and a color like (red,green,orange etc...)
		 */
		protected void highlightElement(WebElement element, String bgColor) {
			//keep the old style to change it back
			String originalStyle = element.getAttribute("style");
			String newStyle = "background-color: " + bgColor + ";" + originalStyle;

			// Change the style 
			m_js.executeScript("var tmpArguments = arguments;setTimeout(function () {tmpArguments[0].setAttribute('style', '" + newStyle + "');},0);",
					element);

			// Change the style back after few milliseconds
			m_js.executeScript("var tmpArguments = arguments;setTimeout(function () {tmpArguments[0].setAttribute('style', '"
					+ originalStyle + "');},400);", element);

		}

	public String random() {
		String add = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		int stringLength = 4;
		StringBuilder stringBuilder = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < stringLength; i++) {
			int randomIndex = random.nextInt(add.length());
			stringBuilder.append(add.charAt(randomIndex));
		}

		return stringBuilder.toString();

	}
	public boolean isYourElementPresent(WebElement element) {
		try {
			return element.isDisplayed(); // Checks if the element is present and displayed
		} catch (Exception e) {
			return false;
		}
	}

	public boolean getUrl(String url) {
		String currentUrl = m_driver.getCurrentUrl();

		if (currentUrl.equals(url)) {
			return true;

		}
		return false;
	}

	public boolean arrayListAreEqual(ArrayList<String> list, ArrayList<String> list2) {
		if (list.size() == list2.size() && list.equals(list2)) {
			return true;
		} else
			return false;

	}
	public String getTheNameOfTitlePage(WebElement el){
		String name  = getText(el);
		return name;

	}

	public String getFilaString(String remove,String str){
		String newStr = str.replace(remove, "");

        return newStr;
    }

}
