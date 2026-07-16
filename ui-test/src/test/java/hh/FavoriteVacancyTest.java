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
        logger.info("Тест №3: Добавление вакансии в избранное");
        logger.info("0) Авторизация");
        MainPage mainPage = new LoginPage().login(hh.utils.Config.getEmail(), hh.utils.Config.getPassword());

        logger.info("1) Нажать на поле поиска вакансии, 2) Ввести название вакансии, 3) Произвести поиск");
        SearchResultsPage searchResultsPage = mainPage.submitSearch();
        
        logger.info("Открытие первой найденной карточки вакансии");
        VacancyPage vacancyPage = searchResultsPage.openFirstVacancy();

        String vacancyTitle = vacancyPage.getVacancyTitle();

        logger.info("4) Нажать на кнопку «добавить в избранное» в карточке вакансии");
        vacancyPage.clickFavorite();

        logger.info("Дополнительный шаг: скрытие вакансии перед переходом");
        vacancyPage.scrollToTop();
        vacancyPage.clickMoreOptions();
        vacancyPage.clickHideVacancy();

        logger.info("5) Перейти во вкладку избранные вакансии");
        FavoritesPage favoritesPage = vacancyPage.goToFavorites();

        logger.info("6) Проверить наличие добавленной вакансии");
        String errorMessage = String.format(VACANCY_NOT_FOUND_ERR, vacancyTitle);
        Assertions.assertTrue(favoritesPage.isVacancyInFavorites(vacancyTitle), errorMessage);
        
        logger.info("Тест №3: Добавление вакансии в избранное завершён успешно");
    }
}
