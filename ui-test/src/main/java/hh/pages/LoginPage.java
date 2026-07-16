package hh.pages;

import hh.elements.Button;
import hh.elements.Input;
import hh.elements.Text;

public class LoginPage extends BasePage {

    private static final String LOGIN_PAGE_TYPE = "applicant-login-card";
    private static final String BASE_ELEMENT_XPATH = "//*[@data-qa='%s']";

    private static final String LOGIN_INPUT_DATA_QA = "applicant-login-input-email";
    private static final String PASSWORD_INPUT_DATA_QA = "applicant-login-input-password";
    private static final String SUBMIT_BUTTON_DATA_QA = "submit-button";
    private static final String ERROR_MESSAGE_DATA_QA = "form-helper-error";
    private static final String EXPAND_PASSWORD_DATA_QA = "expand-login-by-password";

    private final Input loginInput = Input.byDataQa(LOGIN_INPUT_DATA_QA);
    private final Input passwordInput = Input.byDataQa(PASSWORD_INPUT_DATA_QA);
    private final Button submitButton = Button.byDataQa(SUBMIT_BUTTON_DATA_QA);
    private final Text errorMessage = Text.byDataQa(ERROR_MESSAGE_DATA_QA);
    private final Button emailAuthButton = Button.byXpath("//div[text()='Почта']");
    private final Button expandPasswordButton = Button.byDataQa(EXPAND_PASSWORD_DATA_QA);

    public LoginPage() {
        super(LoginPage.class, LOGIN_PAGE_TYPE, BASE_ELEMENT_XPATH);
    }

    public void fillLogin(String login) {
        loginInput.fill(login);
    }

    public void fillPassword(String password) {
        passwordInput.fill(password);
    }

    public MainPage submit() {
        submitButton.click();
        return new MainPage();
    }

    public MainPage login(String email, String password) {
        submitButton.click();
        emailAuthButton.click();
        loginInput.fill(email);
        expandPasswordButton.click();
        passwordInput.fill(password);
        submitButton.click();
        com.codeborne.selenide.Selenide.sleep(3000);
        return new MainPage();
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }

    public String getErrorText() {
        return errorMessage.getText();
    }
}
