package hh.pages;

import hh.elements.Button;

/**
 * Страница черного списка (скрытых компаний и вакансий) кандидата.
 */
public class BlacklistPage extends BasePage {

    private static final String COMPANIES_TAB_XPATH = "//a[@href='/applicant/blacklist/employer?hhtmFrom=blacklist_vacancies' and text()='Компании']";
    private static final String BLACKLISTED_COMPANIES_XPATH = "//*[@data-qa='blacklisted-employer-title']";

    private final Button companiesTabButton = Button.byXpath(COMPANIES_TAB_XPATH);
    private final com.codeborne.selenide.ElementsCollection blacklistedCompanies = com.codeborne.selenide.Selenide.$$x(BLACKLISTED_COMPANIES_XPATH);

    public BlacklistPage() {
        super(BlacklistPage.class, "body", "//%s");
    }

    public void clickCompaniesTab() {
        companiesTabButton.click();
    }

    public boolean isCompanyInBlacklist(String companyId) {
        try {
            blacklistedCompanies.first().shouldBe(com.codeborne.selenide.Condition.visible, java.time.Duration.ofSeconds(15));
        } catch (Throwable e) {
            return false;
        }

        int count = blacklistedCompanies.size();
        for (int i = 0; i < count; i++) {
            String href = blacklistedCompanies.get(i).getAttribute("href");
            if (href != null && href.contains("/employer/" + companyId)) {
                return true;
            }
        }
        return false;
    }
}
