package hh;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import hh.pages.AutoSearchPage;
import hh.pages.LoginPage;
import hh.pages.MainPage;
import hh.pages.SearchResultsPage;

public class AutoSearchTest extends BaseTest {

    private static final String DESIRED_POSITION = "тестировщик";
    private static final String SEARCH_NOT_SAVED_MESSAGE = "Автопоиск по запросу '%s' не сохранён";

    /**
     * Тест №8: Автопоиск вакансии. Проверяет, что сохранённый поиск с выбранными
     * фильтрами появляется в разделе «Автопоиски вакансий».
     *
     * Шаги: нажать «поиск» на верхней панели -> ввести желаемую должность ->
     * открыть фильтры -> выбрать фильтры (опыт от 1 до 3 лет, полная занятость) ->
     * сохранить поиск -> выбрать способ оповещения (на почту) -> перейти
     * в автопоиск и проверить, сохранился ли поиск.
     *
     * Ожидаемый результат: автопоиск сохранён в избранном.
     */
    @Test
    @DisplayName("Тест №8: Автопоиск вакансии")
    public void testAutoSearch() {
        logger.info("Тест №8: Автопоиск вакансии");
        logger.info("1. Авторизация");
        MainPage mainPage = new LoginPage().login(hh.utils.Config.getEmail(), hh.utils.Config.getPassword());
        logger.info("2. Закрытие баннера cookie, если он есть");
        mainPage.closeCookieBannerIfExists();

        logger.info("3. Ввод желаемой должности и выбор фильтров");
        mainPage.clickSearchInput();
        mainPage.fillSearchQuery(DESIRED_POSITION);
        mainPage.openFiltersPanel();
        mainPage.selectExperience1To3Filter();
        mainPage.selectFullEmploymentFilter();

        logger.info("4. Показ результатов поиска с фильтрами");
        SearchResultsPage searchResultsPage = mainPage.showFilteredVacancies();
        searchResultsPage.clickSaveSearch();
        searchResultsPage.selectEmailNotification();

        logger.info("5. Переход в раздел автопоисков");
        AutoSearchPage autoSearchPage = searchResultsPage.goToAutoSearch();

        String errorMessage = String.format(SEARCH_NOT_SAVED_MESSAGE, DESIRED_POSITION);
        logger.info("6. Проверка: автопоиск '{}' сохранён в разделе автопоисков", DESIRED_POSITION);
        Assertions.assertTrue(autoSearchPage.isAutoSearchSaved(DESIRED_POSITION), errorMessage);
        logger.info("Тест №8: Автопоиск вакансии завершён успешно");
    }
}