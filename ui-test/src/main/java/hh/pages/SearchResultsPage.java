package hh.pages;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;
import com.codeborne.selenide.WebDriverRunner;

/*** Страница результатов поиска вакансий с панелью фильтров. */
public class SearchResultsPage extends BasePage {

    private static final String SEARCH_RESULTS_PAGE_TYPE = "vacancy-serch_results";
    private static final String BASE_ELEMENT_XPATH = "//*[@data-qa='%s']";

    private static final String VACANCY_TITLE_LINK_XPATH = "//a[@data-qa='serp-item__title']";
    private static final int FIRST_VACANCY_INDEX = 0;
    private static final int LATEST_WINDOW_OFFSET = 1;

    public SearchResultsPage() {
        super(SearchResultsPage.class, SEARCH_RESULTS_PAGE_TYPE, BASE_ELEMENT_XPATH);
    }

    public VacancyPage openFirstVacancy() {
        $$x(VACANCY_TITLE_LINK_XPATH).get(FIRST_VACANCY_INDEX).click();

        List<String> windowHandles = new ArrayList<>(WebDriverRunner.getWebDriver().getWindowHandles());
        String latestWindowHandle = windowHandles.get(windowHandles.size() - LATEST_WINDOW_OFFSET);
        WebDriverRunner.getWebDriver().switchTo().window(latestWindowHandle);

        return new VacancyPage();
    }
}