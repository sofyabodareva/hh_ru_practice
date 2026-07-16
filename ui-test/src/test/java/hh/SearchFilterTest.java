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
     * Тест №2: Поиск по фильтрам.
     * Проверяет, что вакансия, открытая из результатов поиска, соответствует
     * применённому фильтру по опыту работы.
     * 
     * Шаги: нажать на поле поиска -> ввести запрос в поле поиска вакансий ->
     * открыть панель фильтров -> выбрать фильтр по опыту работы -> показать
     * отфильтрованные вакансии -> открыть первую вакансию из списка ->
     * сравнить требуемый опыт вакансии с выбранным фильтром.
     * 
     * Ожидаемый результат: требования вакансии соответствуют выбранным фильтрам.
     */
    @Test
    @DisplayName("Тест №2: Поиск по фильтрам")
    public void testSearchByFilters() {
        new LoginPage().login("julialev1206@gmail.com", "Buldak322");
        MainPage mainPage = MainPage.open();

        mainPage.openFiltersPanel();
        mainPage.selectNoExperienceFilter();

        SearchResultsPage searchResultsPage = mainPage.showFilteredVacancies();
        VacancyPage vacancyPage = searchResultsPage.openFirstVacancy();
        Assertions.assertEquals(EXPECTED_EXPERIENCE, vacancyPage.getExperienceText(), EXPERIENCE_MISMATCH_MESSAGE);
    }
}