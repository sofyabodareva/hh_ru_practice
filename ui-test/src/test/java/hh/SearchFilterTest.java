package hh;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import hh.pages.LoginPage;
import hh.pages.MainPage;
import hh.pages.SearchResultsPage;
import hh.pages.VacancyPage;

public class SearchFilterTest extends BaseTest {

    private static final String EXPECTED_EXPERIENCE = "не требуется";
    private static final String EXPERIENCE_MISMATCH_MESSAGE = "Требуемый опыт вакансии не соответствует выбранному фильтру";

    /**
     * Тест №4: Поиск по фильтрам. Проверяет, что вакансия, открытая из
     * результатов поиска, соответствует применённому фильтру по опыту работы.
     *
     * Шаги: нажать на поле поиска -> ввести запрос в поле поиска вакансий ->
     * открыть панель фильтров -> выбрать фильтр по опыту работы -> показать
     * отфильтрованные вакансии -> открыть первую вакансию из списка -> сравнить
     * требуемый опыт вакансии с выбранным фильтром.
     *
     * Ожидаемый результат: требования вакансии соответствуют выбранным
     * фильтрам.
     */
    @Test
    @DisplayName("Тест №4: Поиск по фильтрам")
    public void testSearchByFilters() {
        logger.info("Тест №4: Поиск по фильтрам");
        logger.info("1. Авторизация");
        MainPage mainPage = new LoginPage().login(hh.utils.Config.getEmail(), hh.utils.Config.getPassword());

        logger.info("2. Открытие панели фильтров");
        mainPage.openFiltersPanel();
        logger.info("3. Выбор фильтров");
        mainPage.selectNoExperienceFilter();
        
        logger.info("4. Показ результата поиска по фильтрам");
        SearchResultsPage searchResultsPage = mainPage.showFilteredVacancies();
        logger.info("5. Открытие вакансии для проверки");
        VacancyPage vacancyPage = searchResultsPage.openFirstVacancy();

        logger.info("6. Проверка соответствия вакансии фильтрам");
        Assertions.assertEquals(EXPECTED_EXPERIENCE, vacancyPage.getExperienceText(), EXPERIENCE_MISMATCH_MESSAGE);
        logger.info("Тест №4: Тест поиск по фильтрам успешно");
    }
}
