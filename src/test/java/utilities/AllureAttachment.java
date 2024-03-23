package utilities;

import io.qameta.allure.Attachment;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LogEntry;

import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class AllureAttachment {

    @Attachment(value = "Html source", type = "text/html", fileExtension = ".html")
    public static byte[] getPageSource(WebDriver driver) {
        return driver.getPageSource().getBytes(StandardCharsets.UTF_8);
    }

    @Attachment(value = "Console Logs", type = "text/plain", fileExtension = ".txt")
    public static String attachConsoleLogs(WebDriver driver) {
        String consoleLogs = driver
                .manage()
                .logs()
                .get(LogType.BROWSER)
                .getAll()
                .stream()
                .map(LogEntry::toString)
                .collect(Collectors.joining(System.lineSeparator()));
        return StringUtils.isBlank(consoleLogs) ? "No Console Logs Found" : consoleLogs;
    }


    @Attachment(value = "URL attachment", type = "text/uri-list", fileExtension = ".uri")
    public static String attachURL(String url) {
        return url;
    }
}

