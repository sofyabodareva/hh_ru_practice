package hh;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Selenide;

import hh.pages.LoginPage;
import hh.pages.ResponsesPage;
import hh.pages.VacancyPage;

public class VacancyResponseTest extends BaseTest {

    @Test
    @DisplayName("Тест №1: Отклик на вакансию")
    public void testVacancyResponse() {
        VacancyPage vacancyPage = new VacancyPage();
        ResponsesPage responsesPage = new ResponsesPage();

        String vacancyUrl = "/vacancy/135202729";
        String vacancyTitle = "Бизнес-аналитик - стажер";

        new LoginPage().login("почта", "пароль");

        // 1) Зайти в карточку вакансии
        Selenide.open(vacancyUrl);

        // 2) Нажать на кнопку «откликнуться»
        vacancyPage.clickRespond();

        // 3) В появившемся окне выбрать резюме
        vacancyPage.selectResumeByIndex(0);

        // 4) Нажать на кнопку «добавить сопроводительное письмо»
        vacancyPage.clickAddCoverLetter();

        // 5) Ввести в текстовое поле текст сопроводительного письма
        vacancyPage.fillCoverLetter("Здравствуйте! Меня очень заинтересовала ваша вакансия.");

        // 6) Нажать на кнопку «откликнуться» внутри окна
        vacancyPage.submitResponse();

        // 7) Убедиться, что всплывающее окно закрылось
        Assertions.assertTrue(vacancyPage.isResponsePopupClosed(), "Окно отклика не закрылось после отправки");

        // 8) Убедиться, что появилась плашка "Резюме отправлено"
        Assertions.assertTrue(vacancyPage.isResponseStatusSent(), "Сообщение об успешном отклике не появилось на странице");

        // 9) Перейти в раздел «отклики»
        Selenide.open("/applicant/negotiations");

        // 10) Проверить наличие отклика на вакансию в списке
        Assertions.assertTrue(responsesPage.isVacancyInList(vacancyTitle), "Отклик на вакансию '" + vacancyTitle + "' не найден в списке откликов");
    }
}
