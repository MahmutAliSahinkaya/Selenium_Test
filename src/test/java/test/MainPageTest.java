package test;

import base.BaseTest;
import org.junit.Before;
import org.junit.Test;
import page.MainPage;

public class MainPageTest extends BaseTest {
    MainPage mainPage;


    @Before
    public void before() {
        mainPage = new MainPage(getDriver());
    }

    @Test
    public void mainControl() {
        mainPage.callMainPage().googleSearch();
    }
}
