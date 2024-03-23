package utilities;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class ListenerClass extends TestListenerAdapter {
    @Attachment
    public byte[] captureScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Object webDriverAttribute = result.getTestContext().getAttribute("webDriver");
        if (webDriverAttribute instanceof WebDriver) {
            System.out.println("capturing..");
            captureScreenshot((WebDriver) webDriverAttribute);
            AllureAttachment.getPageSource((WebDriver) webDriverAttribute);
            AllureAttachment.attachConsoleLogs((WebDriver) webDriverAttribute);

        }
    }

}
