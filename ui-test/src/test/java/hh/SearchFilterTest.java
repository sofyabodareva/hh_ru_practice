package hh;

import hh.pages.MainPage;
import hh.pages.SearchResultsPage;
import hh.pages.VacancyPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SearchFilterTest extends BaseTest {

    private static final String EXPECTED_EXPERIENCE = "не опыта";
    private static final String EXPERIENCE_MISMATCH_MESSAGE = "Требуемый опыт вакансии не соответствует выбранному фильтру";

    /**
     * Тест №2: Поиск по фильтрам.
     * Проверяет, что вакансия, открытая из результатов поиска, соответствует
     * применённому фильтру по опыту работы.
     * <p>
     * Шаги: нажать на поле поиска -> ввести запрос в поле поиска вакансий ->
     * открыть панель фильтров -> выбрать фильтр по опыту работы -> показать
     * отфильтрованные вакансии -> открыть первую вакансию из списка ->
     * сравнить требуемый опыт вакансии с выбранным фильтром.
     * <p>
     * Ожидаемый результат: требования вакансии соответствуют выбранным фильтрам.
     */
    @Test
    @DisplayName("Тест №2: Поиск по фильтрам")
    public void testSearchByFilters() {
        MainPage mainPage = MainPage.open();

        mainPage.openFiltersPanel();
        mainPage.selectNoExperienceFilter();

        SearchResultsPage searchResultsPage = mainPage.showFilteredVacancies();
        VacancyPage vacancyPage = searchResultsPage.openFirstVacancy();

        Assertions.assertEquals(EXPECTED_EXPERIENCE, vacancyPage.getExperienceText(), EXPERIENCE_MISMATCH_MESSAGE);
    }
}