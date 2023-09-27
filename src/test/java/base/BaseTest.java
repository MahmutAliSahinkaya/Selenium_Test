package base;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;

@Testcontainers
public class BaseTest {


    private static BrowserWebDriverContainer<?> webDriverContainer;
    private static WebDriver driver;


    @Container
    private final GenericContainer<?> google = new GenericContainer<>("suspicious_euclid")
            .withExposedPorts(4444);


    @BeforeClass
    public static void setUp() {

        webDriverContainer = new BrowserWebDriverContainer<>()
                .withCapabilities(DesiredCapabilities.chrome())
                .withRecordingMode(BrowserWebDriverContainer.VncRecordingMode.RECORD_ALL, new File("target"));

        webDriverContainer.start();

        driver = webDriverContainer.getWebDriver();
    }

    @Test
    public void testGoogleSearch() {
        driver.get("https://www.google.com");
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("Testcontainers Selenium Example");
        searchBox.submit();
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
        webDriverContainer.stop();
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void setDriver(WebDriver driver) {
        BaseTest.driver = driver;
    }
}

