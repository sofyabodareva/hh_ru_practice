package hh;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import hh.pages.LoginPage;
import hh.pages.MainPage;
import hh.pages.ResponsesPage;
import hh.pages.SearchResultsPage;
import hh.pages.VacancyPage;

public class VacancyResponseTest extends BaseTest {

    private static final String COVER_LETTER_TEXT = "Здравствуйте! Меня очень заинтересовала ваша вакансия.";
    private static final String POPUP_NOT_CLOSED_MSG = "Окно отклика не закрылось после отправки";
    private static final String SUCCESS_MSG_NOT_FOUND = "Сообщение об успешном отклике не появилось на странице";
    private static final String VACANCY_NOT_IN_LIST_MSG = "Отклик на вакансию '%s' не найден в списке откликов";

    /**
     * Тест №1: Отклик на вакансию. Проверяет полный цикл отклика на первую
     * вакансию в поиске: авторизация, пустой поиск, переход в первую вакансию,
     * отправка отклика с сопроводительным письмом, скрытие вакансии, переход в
     * раздел откликов и проверка наличия этого отклика в списке.
     */
    @Test
    @DisplayName("Тест №1: Отклик на вакансию")
    public void testVacancyResponse() {
        MainPage mainPage = new LoginPage().login(hh.utils.Config.getEmail(), hh.utils.Config.getPassword());
        SearchResultsPage searchResultsPage = mainPage.submitSearch();
        VacancyPage vacancyPage = searchResultsPage.openFirstVacancy();

        String vacancyTitle = vacancyPage.getVacancyTitle();

        vacancyPage.clickRespond();
        vacancyPage.selectResumeByIndex(0);
        vacancyPage.clickAddCoverLetter();
        vacancyPage.fillCoverLetter(COVER_LETTER_TEXT);
        vacancyPage.submitResponse();

        Assertions.assertTrue(vacancyPage.isResponsePopupClosed(), POPUP_NOT_CLOSED_MSG);
        Assertions.assertTrue(vacancyPage.isResponseStatusSent(), SUCCESS_MSG_NOT_FOUND);

        vacancyPage.closeChatikIfExists();
        vacancyPage.scrollToTop();
        vacancyPage.clickMoreOptions();
        vacancyPage.clickHideVacancy();

        ResponsesPage responsesPage = vacancyPage.goToResponses();

        String expectedMessage = String.format(VACANCY_NOT_IN_LIST_MSG, vacancyTitle);
        Assertions.assertTrue(responsesPage.isVacancyInList(vacancyTitle), expectedMessage);
    }
}
