package page;

import base.BasePage;
import org.openqa.selenium.WebDriver;

import static constantPage.ConstantMainPage.searchBox;

public class MainPage extends BasePage {
    public MainPage(WebDriver driver) {
        super(driver);
    }
    public MainPage googleSearch() {
        driverGet("https://www.google.com.tr/");
        sendKeys(searchBox, "Testcontainers Selenium Example");
        submit(searchBox);
        return new MainPage(driver);
    }
}
