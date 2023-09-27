package base;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.MainPage;

import java.util.List;
import java.util.Random;

public class BasePage {

    protected WebDriver driver;
    WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 30);
    }

    protected JavascriptExecutor getJSExecutor() {
        return (JavascriptExecutor) driver;
    }

    public void waitBySeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void submit(By by){
        waitUntilPresence(by);
        findElement(by).submit();
    }
    public void driverGet(String url){
        driver.get(url);
    }

    public WebElement findElement(By by) {
        return driver.findElement(by);
    }

    public void clickElement(By by) {
        waitUntilPresence(by);
        WebElement element;
        element = findElement(by);
        if (!element.isDisplayed()) {
            scrollTo(element.getLocation().getX(), element.getLocation().getY());
        }
        try {
            waitUntilElementClickable(by);
        } catch (Exception ex) {
            Assert.fail("Element tıklanabilir değil!");
        }
        element.click();
    }

    public void printNumberOfElements(By by) {
        List<WebElement> elementSayisi = findElements(by);
        int a = elementSayisi.size();
        System.out.println(by+" elementinden " + a + " tane var.");
    }

    public void randomEmailGenerator(By by) {
        int number = randomPick(30000);
        findElement(by).sendKeys(number + "@gmail.com");
        System.out.println(by + " elementine " + number + " maili yazıldı.");
    }
    public int randomPick(int size) {
        Random rand = new Random();
        int minimum = 1;
        int pick = rand.nextInt(size - minimum) + minimum;
        System.out.println(minimum + " değeri ile " + size + " değeri arasında rastgele seçilen sayı : " + pick);
        return pick;
    }
    public void changeFrame(By by){
        WebElement webElement = findElement(by);
        driver.switchTo().frame(webElement);
        //System.out.println(by+" Frame icine girdi");
        System.out.println(by+" Frame icine girdi");
    }

    List<WebElement> findElements(By by) {
        return driver.findElements(by);
    }
    //findElements(getElementInfoToBy(findElementInfoByKey(key)));
    public void sendKeys(By by, String text) {
        waitUntilPresence(by);
        WebElement element;
        element = findElement(by);
        if (!element.isDisplayed()) {
            scrollTo(element.getLocation().getX(), element.getLocation().getY());
        }
        waitUntilElementClickable(by);
        element.sendKeys(text);
    }

    public void findTextAndClick(By by, String text) {
        System.out.println("Elementin var olması bekleniyor... ");
        waitUntilPresence(by);
        List<WebElement> searchText = driver.findElements(by);
        for (int i = 0; i < searchText.size(); i++) {
            if (searchText.get(i).getText().trim().contains(text)) {
                searchText.get(i).click();
                System.out.println("Bulunan elemente tıklandı.");
                break;
            }
        }
    }

    public void hoverElement(By by) {
        Actions action = new Actions(driver);
        System.out.println("Üstüne gelinen element :" + by);
        action.moveToElement(findElement(by)).build().perform();
    }

    /**
     * scrollTo WebElement location
     *
     * @param x
     * @param y
     */
    protected void scrollTo(int x, int y) {
        String jsStmt = String.format("window.scrollTo(%d, %d);", x, y);
        getJSExecutor().executeScript(jsStmt, true);
    }

    public void selectVisibleText(By by, String text) {
        Select select = new Select(findElement(by));
        select.selectByVisibleText(text);
    }

    public String getText(By by) {
        waitUntilPresence(by);
        return findElement(by).getText();
    }

    public void assertionTrue(String message, boolean condition) {
        Assert.assertTrue(message, condition);
    }

    public void waitUntilPresence(By by) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    public void waitUntilElementAppear(By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void waitUntilElementClickable(By by) {
        System.out.println("Elementin tıklanabilir olması bekleniyor.");
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public void untilElementDissapear(By by) {
        WebDriverWait dissapear = new WebDriverWait(driver, 300);
        dissapear.ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
        dissapear.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public void elementDisplayedControl(By by) {

    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }


    public MainPage callMainPage() {
        return new MainPage(driver);
    }
}
