package hh.pages;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;
import com.codeborne.selenide.WebDriverRunner;

import hh.elements.Button;

/*** Страница результатов поиска вакансий с панелью фильтров. */
public class SearchResultsPage extends BasePage {

    private static final String SEARCH_RESULTS_PAGE_TYPE = "vacancy-serch_results";
    private static final String BASE_ELEMENT_XPATH = "//*[@data-qa='%s']";

    private static final String SAVE_SEARCH_BUTTON_DATA_QA = "vacancy-saved-search-create";
    private static final String EMAIL_NOTIFICATION_OPTION_DATA_QA = "autosearch-subscribe__email-button";
    private static final String GO_TO_AUTOSEARCH_LINK_DATA_QA = "snackbar-user-action";

    private static final String VACANCY_TITLE_LINK_XPATH = "//a[@data-qa='serp-item__title']";
    private static final int FIRST_VACANCY_INDEX = 0;
    private static final int LATEST_WINDOW_OFFSET = 1;

    private final Button saveSearchButton = Button.byDataQa(SAVE_SEARCH_BUTTON_DATA_QA);
    private final Button emailNotificationOption = Button.byDataQa(EMAIL_NOTIFICATION_OPTION_DATA_QA);
    private final Button goToAutoSearchLink = Button.byDataQa(GO_TO_AUTOSEARCH_LINK_DATA_QA);

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

    public void clickSaveSearch() {
        saveSearchButton.click();
    }

    public void selectEmailNotification() {
        emailNotificationOption.click();
    }

    public AutoSearchPage goToAutoSearch() {
        goToAutoSearchLink.click();
        return new AutoSearchPage();
    }
}