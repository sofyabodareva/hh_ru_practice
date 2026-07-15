package hh.pages;

import com.codeborne.selenide.Selenide;
import hh.elements.Button;

public class MainPage extends BasePage {
    private static final String MAIN_PAGE_TYPE = "supernova-navi-dashboard";
    private static final String BASE_ELEMENT_XPATH = "//*[@data-qa='%s']";
    private static final String LOGIN_BUTTON_DATA_QA = "login";

    private final Button loginButton = Button.byDataQa(LOGIN_BUTTON_DATA_QA);

    public MainPage() {
        super(MainPage.class, MAIN_PAGE_TYPE, BASE_ELEMENT_XPATH);
    }

    public static MainPage open() {
        Selenide.open("/");
        return new MainPage();
    }

    public LoginPage clickLogin() {
        loginButton.click();
        return new LoginPage();
    }
}
