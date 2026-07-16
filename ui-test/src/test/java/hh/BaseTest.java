package hh;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;

import hh.pages.MainPage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import hh.pages.LoginPage;

public abstract class BaseTest {

    private static final String LOGIN = System.getProperty("hh.login", System.getenv("HH_LOGIN"));
    private static final String PASSWORD = System.getProperty("hh.password", System.getenv("HH_PASSWORD"));

    // Метод, который сработает до тестов
    @BeforeAll
    public static void openBrowser() {
        Configuration.baseUrl = "https://hh.ru";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        
        
    }

    @BeforeEach
    public void login() {
        MainPage mainPage = MainPage.open();
        LoginPage loginPage = mainPage.clickLogin();
        loginPage.fillLogin((LOGIN));
        loginPage.fillPassword(PASSWORD);
        loginPage.submit();
    }

    // Метод, который сработает после тестов 
    @AfterEach
    public void closeBrowser() {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }
}
