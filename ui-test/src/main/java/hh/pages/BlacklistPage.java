package hh.pages;

import hh.elements.Button;
import hh.elements.Text;

/**
 * Страница черного списка (скрытых компаний и вакансий) кандидата.
 */
public class BlacklistPage extends BasePage {

    private static final String COMPANIES_TAB_XPATH = "//a[@href='/applicant/blacklist/employer?hhtmFrom=blacklist_vacancies' and text()='Компании']";
    private static final String BLACKLISTED_COMPANY_TITLE_XPATH = "//*[@data-qa='blacklisted-employer-title' and contains(text(), '%s')]";

    private final Button companiesTabButton = Button.byXpath(COMPANIES_TAB_XPATH);

    public BlacklistPage() {
        super(BlacklistPage.class, "body", "//%s");
    }

    public void clickCompaniesTab() {
        companiesTabButton.click();
    }

    public boolean isCompanyInBlacklist(String companyName) {
        String xpath = String.format(BLACKLISTED_COMPANY_TITLE_XPATH, companyName);
        return Text.byXpath(xpath).isDisplayed();
    }
}
