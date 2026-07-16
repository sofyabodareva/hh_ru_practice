package hh;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.WebDriverRunner;

import hh.pages.LoginPage;
import hh.pages.MainPage;
import hh.pages.SearchResultsPage;
import hh.pages.VacancyPage;

public class SearchVacancyTest extends BaseTest {

    private static final String SEARCH_QUERY = "qa-инженер";
    private static final String QUERY_PARAM_NAME = "query=";
    private static final String VACANCY_NOT_RELEVANT_MESSAGE =
        "Открытая вакансия не помечена как результат поиска по запросу '%s'. URL: %s";

    /**
     * Тест №5: Поиск вакансии. Проверяет, что вакансия, открытая из результатов
     * поиска, действительно является результатом введённого поискового запроса.
     * Релевантность проверяется через параметр query в URL вакансии, который
     * hh.ru сам проставляет при переходе из выдачи поиска — это надёжнее, чем
     * сравнение текста заголовка, так как hh.ru использует поиск с синонимами
     * (например, по запросу «QA-инженер» может найтись вакансия «Тестировщик»).
     *
     * Шаги: нажать на поле поиска -> ввести название вакансии -> нажать
     * «Найти» -> открыть вакансию -> проверить релевантность.
     *
     * Ожидаемый результат: вакансия соответствует искомой.
     */
    @Test
    @DisplayName("Тест №5: Поиск вакансии")
    public void testSearchVacancy() {
        MainPage mainPage = new LoginPage().login(hh.utils.Config.getEmail(), hh.utils.Config.getPassword());

        mainPage.clickSearchInput();
        mainPage.fillSearchQuery(SEARCH_QUERY);

        SearchResultsPage searchResultsPage = mainPage.submitSearch();
        searchResultsPage.openFirstVacancy();

        boolean isFromSearch = isVacancyFromSearchQuery(SEARCH_QUERY);

        String currentUrl = WebDriverRunner.url();
        String errorMessage = String.format(VACANCY_NOT_RELEVANT_MESSAGE, SEARCH_QUERY, currentUrl);
        Assertions.assertTrue(isFromSearch, errorMessage);
    }

    /** Проверяет, что текущий URL содержит параметр query с тем же поисковым запросом. */
    private boolean isVacancyFromSearchQuery(String searchQuery) {
        String currentUrl = WebDriverRunner.url();
        String decodedUrl = URLDecoder.decode(currentUrl, StandardCharsets.UTF_8);
        int queryIndex = decodedUrl.indexOf(QUERY_PARAM_NAME);

        if (queryIndex == -1) {
            return false;
        }

        String queryValue = decodedUrl.substring(queryIndex + QUERY_PARAM_NAME.length());
        int ampersandIndex = queryValue.indexOf('&');
        if (ampersandIndex != -1) {
            queryValue = queryValue.substring(0, ampersandIndex);
        }

        return queryValue.equalsIgnoreCase(searchQuery);
    }
}