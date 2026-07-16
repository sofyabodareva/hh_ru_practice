package hh.pages;

import hh.elements.Text;

/**
 * Страница избранных вакансий.
 */
public class FavoritesPage extends BasePage {

    private static final String VACANCY_TITLE_XPATH = "//*[@data-qa='serp-item__title-text' and text()='%s']";

    public FavoritesPage() {
        super(FavoritesPage.class, "body", "//%s");
    }

    /**
     * Проверяет, отображается ли вакансия с заданным названием в списке избранного.
     *
     * @param title название вакансии
     * @return true, если вакансия найдена, иначе false
     */
    public boolean isVacancyInFavorites(String title) {
        String xpath = String.format(VACANCY_TITLE_XPATH, title);
        return Text.byXpath(xpath).isDisplayed();
    }
}
