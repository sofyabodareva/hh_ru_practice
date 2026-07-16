package hh.pages;

import static com.codeborne.selenide.Selenide.$$x;

/*** Страница результатов поиска вакансий с панелью фильтров. */
public class SearchResultsPage extends BasePage {

    private static final String SEARCH_RESULTS_PAGE_TYPE = "vacancy-serch_results";
    private static final String BASE_ELEMENT_XPATH = "//*[@data-qa='%s']";

    // Ссылки на карточки вакансий в списке результатов поиска
    private static final String VACANCY_TITLE_LINK_XPATH = "//a[@data-qa='serp-item__title']";
    private static final int FIRST_VACANCY_INDEX = 0;

    public SearchResultsPage() {
        super(SearchResultsPage.class, SEARCH_RESULTS_PAGE_TYPE, BASE_ELEMENT_XPATH);
    }

    /*** Открывает первую вакансию из списка результатов поиска. */
    public VacancyPage openFirstVacancy() {
        $$x(VACANCY_TITLE_LINK_XPATH).get(FIRST_VACANCY_INDEX).click();
        return new VacancyPage();
    }
}
