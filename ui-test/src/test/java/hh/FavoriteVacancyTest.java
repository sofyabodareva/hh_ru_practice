package hh;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import hh.pages.FavoritesPage;
import hh.pages.LoginPage;
import hh.pages.MainPage;
import hh.pages.SearchResultsPage;
import hh.pages.VacancyPage;

public class FavoriteVacancyTest extends BaseTest {

    private static final String VACANCY_NOT_FOUND_ERR = "Вакансия '%s' не найдена в списке избранного";

    /**
     * Тест №3: Добавление вакансии в избранное. Авторизуется, выполняет пустой
     * поиск, переходит в первую вакансию, сохраняет название, добавляет в
     * избранное, скрывает вакансию, переходит на страницу избранных вакансий и
     * проверяет наличие сохраненной вакансии по названию.
     */
    @Test
    @DisplayName("Тест №3: Добавление вакансии в избранное")
    public void testFavoriteVacancy() {
        MainPage mainPage = new LoginPage().login(hh.utils.Config.getEmail(), hh.utils.Config.getPassword());

        SearchResultsPage searchResultsPage = mainPage.submitSearch();
        VacancyPage vacancyPage = searchResultsPage.openFirstVacancy();

        String vacancyTitle = vacancyPage.getVacancyTitle();

        vacancyPage.clickFavorite();

        vacancyPage.scrollToTop();
        vacancyPage.clickMoreOptions();
        vacancyPage.clickHideVacancy();

        FavoritesPage favoritesPage = vacancyPage.goToFavorites();

        String errorMessage = String.format(VACANCY_NOT_FOUND_ERR, vacancyTitle);
        Assertions.assertTrue(favoritesPage.isVacancyInFavorites(vacancyTitle), errorMessage);
    }
}
