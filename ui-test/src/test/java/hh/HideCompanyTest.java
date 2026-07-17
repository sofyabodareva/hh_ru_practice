package hh;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import hh.pages.BlacklistPage;
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
    @DisplayName("Тест №2: Скрытие вакансий компании")
    public void testHideCompany() {
        logger.info("Тест №2: Скрытие вакансий компании");
        logger.info("0) Авторизация и поиск вакансии");
        MainPage mainPage = login(hh.utils.Config.getEmail(), hh.utils.Config.getPassword());
        SearchResultsPage searchResultsPage = mainPage.submitSearch();
        
        logger.info("1) Открыть карточку вакансии");
        VacancyPage vacancyPage = searchResultsPage.openFirstVacancy();

        String companyName = vacancyPage.getCompanyName();
        String companyId = vacancyPage.getCompanyId();

        logger.info("2) Нажать кнопку «ещё»");
        vacancyPage.clickMoreOptions();
        
        logger.info("3) Нажать кнопку «скрыть вакансии компании»");
        vacancyPage.clickHideCompany();

        vacancyPage.closeNotificationIfExists();

        logger.info("4) Открыть меню, 5) Нажать на кнопку «скрытые вакансии и компании»");
        BlacklistPage blacklistPage = vacancyPage.goToBlacklist();
        
        logger.info("6) Нажать кнопку «компании»");
        blacklistPage.clickCompaniesTab();

        logger.info("7) Проверить, есть ли скрытая компания в списке");
        String expectedMessage = String.format(COMPANY_NOT_FOUND_MSG, companyName);
        Assertions.assertTrue(blacklistPage.isCompanyInBlacklist(companyId), expectedMessage);
        
        logger.info("Тест №2: Скрытие вакансий компании завершён успешно");
    }
}
