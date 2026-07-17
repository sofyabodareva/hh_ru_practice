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
        logger.info("Тест №1: Отклик на вакансию");
        logger.info("0) Авторизация и поиск вакансии");
        MainPage mainPage = login(hh.utils.Config.getEmail(), hh.utils.Config.getPassword());
        SearchResultsPage searchResultsPage = mainPage.submitSearch();

        logger.info("1) Зайти в карточку вакансии");
        VacancyPage vacancyPage = searchResultsPage.openFirstVacancy();

        String vacancyTitle = vacancyPage.getVacancyTitle();

        logger.info("2) Нажать на кнопку «откликнуться»");
        vacancyPage.clickRespond();

        logger.info("3) Нажать на кнопку «добавить сопроводительное письмо»");
        vacancyPage.clickAddCoverLetter();

        logger.info("4) Ввести в текстовое поле текст сопроводительного письма");
        vacancyPage.fillCoverLetter(COVER_LETTER_TEXT);

        logger.info("5) Нажать на кнопку «откликнуться»");
        vacancyPage.submitResponse();

        logger.info("6) Убедиться, что всплывающее окно закрылось");
        Assertions.assertTrue(vacancyPage.isResponsePopupClosed(), POPUP_NOT_CLOSED_MSG);

        logger.info("7) Убедиться, что статус изменился на «резюме отправлено»");
        Assertions.assertTrue(vacancyPage.isResponseStatusSent(), SUCCESS_MSG_NOT_FOUND);

        logger.info("Дополнительный шаг: скрытие вакансии перед переходом");
        vacancyPage.closeChatikIfExists();
        vacancyPage.scrollToTop();
        vacancyPage.clickMoreOptions();
        vacancyPage.clickHideVacancy();

        logger.info("8) Перейти в раздел «отклики»");
        ResponsesPage responsesPage = vacancyPage.goToResponses();

        logger.info("9) Проверить наличие отклика на вакансию в списке");
        String expectedMessage = String.format(VACANCY_NOT_IN_LIST_MSG, vacancyTitle);
        Assertions.assertTrue(responsesPage.isVacancyInList(vacancyTitle), expectedMessage);

        logger.info("Тест №1: Отклик на вакансию завершён успешно");
    }
}
