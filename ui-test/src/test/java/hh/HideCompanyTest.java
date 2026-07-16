package hh;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import hh.pages.BlacklistPage;
import hh.pages.LoginPage;
import hh.pages.MainPage;
import hh.pages.SearchResultsPage;
import hh.pages.VacancyPage;

public class HideCompanyTest extends BaseTest {

    private static final String COMPANY_NOT_FOUND_MSG = "Компания '%s' не найдена в черном списке!";

    /**
     * Тест №2: Скрытие вакансий компании. Проверяет функционал скрытия компании
     * из списка результатов. Тест авторизуется, открывает первую вакансию,
     * скрывает компанию и проверяет её наличие в черном списке (скрытые
     * вакансии).
     */
    @Test
    @DisplayName("Тест №4: Скрытие вакансий компании")
    public void testHideCompany() {
        MainPage mainPage = new LoginPage().login(hh.utils.Config.getEmail(), hh.utils.Config.getPassword());
        SearchResultsPage searchResultsPage = mainPage.submitSearch();
        VacancyPage vacancyPage = searchResultsPage.openFirstVacancy();

        String companyName = vacancyPage.getCompanyName();
        String companyId = vacancyPage.getCompanyId();

        vacancyPage.clickMoreOptions();
        vacancyPage.clickHideCompany();

        vacancyPage.closeNotificationIfExists();

        BlacklistPage blacklistPage = vacancyPage.goToBlacklist();
        blacklistPage.clickCompaniesTab();

        String expectedMessage = String.format(COMPANY_NOT_FOUND_MSG, companyName);
        Assertions.assertTrue(blacklistPage.isCompanyInBlacklist(companyId), expectedMessage);
    }
}
