package hh.pages;

import com.codeborne.selenide.Selenide;

import hh.elements.Button;
import hh.elements.Input;

public class MainPage extends BasePage {
    private static final String MAIN_PAGE_TYPE = "supernova-navi-dashboard";
    private static final String BASE_ELEMENT_XPATH = "//*[@data-qa='%s']";
    private static final String LOGIN_BUTTON_DATA_QA = "login";

    private static final String COOKIE_ACCEPT_BUTTON_DATA_QA = "cookies-policy-informer-accept";
    private final Button cookieAcceptButton = Button.byDataQa(COOKIE_ACCEPT_BUTTON_DATA_QA);

    private static final String EXPERIENCE_1_TO_3_FILTER_DATA_QA = "search-filter-experience-value-between1And3";
    private static final String FULL_EMPLOYMENT_FILTER_DATA_QA = "search-filter-employment_form-value-FULL";


    private static final String SEARCH_INPUT_DATA_QA = "search-input"; // Поле поиска вакансий
    private static final String SEARCH_SUBMIT_BUTTON_DATA_QA = "search-button"; // Кнопка "Найти"

    private static final String FILTERS_BUTTON_DATA_QA = "header-search-filters-button"; // Кнопка открытия панели фильтров
    private static final String NO_EXPERIENCE_FILTER_DATA_QA = "search-filter-experience-value-noExperience"; // Чекбокс в блоке "Опыт работы"
    private static final String SHOW_VACANCIES_BUTTON_DATA_QA = "search-drawer-filters-submit"; // "Показать N вакансий"

    private static final String RESUMES_AND_PROFILE_LINK_DATA_QA = "profileAndResumes-button";

    private final Button loginButton = Button.byDataQa(LOGIN_BUTTON_DATA_QA);
    private final Input searchInput = Input.byDataQa(SEARCH_INPUT_DATA_QA);
    private final Button searchSubmitButton = Button.byDataQa(SEARCH_SUBMIT_BUTTON_DATA_QA);
    private final Button filtersButton = Button.byDataQa(FILTERS_BUTTON_DATA_QA);
    private final Button noExperienceFilter = Button.byDataQa(NO_EXPERIENCE_FILTER_DATA_QA);
    private final Button showVacanciesButton = Button.byDataQa(SHOW_VACANCIES_BUTTON_DATA_QA);
    private final Button resumesAndProfileLink = Button.byDataQa(RESUMES_AND_PROFILE_LINK_DATA_QA);
    private final Button experience1To3Filter = Button.byDataQa(EXPERIENCE_1_TO_3_FILTER_DATA_QA);
    private final Button fullEmploymentFilter = Button.byDataQa(FULL_EMPLOYMENT_FILTER_DATA_QA);

    public MainPage() {
        super(MainPage.class, MAIN_PAGE_TYPE, BASE_ELEMENT_XPATH);
    }

    public static MainPage open() {
        Selenide.open("/");
        return new MainPage();
    }

    public LoginPage clickLogin() {
        loginButton.click();
        return new LoginPage();
    }

    public void clickSearchInput() {
        searchInput.click();
    }

    public void fillSearchQuery(String query) {
        searchInput.fill(query);
    }

    public SearchResultsPage submitSearch() {
        searchSubmitButton.click();
        return new SearchResultsPage();
    }

    public void openFiltersPanel() {
        filtersButton.click();
    }

    public void selectNoExperienceFilter() {
        noExperienceFilter.click();
    }

    public void selectExperience1To3Filter() {
        experience1To3Filter.click();
    }

    public void selectFullEmploymentFilter() {
        fullEmploymentFilter.click();
    }

    public SearchResultsPage showFilteredVacancies() {
        showVacanciesButton.click();
        return new SearchResultsPage();
    }

    public ProfilePage clickResumesAndProfile() {
        resumesAndProfileLink.click();
        return new ProfilePage();
    }

    public void closeCookieBannerIfExists() {
    if (cookieAcceptButton.isDisplayed()) {
        cookieAcceptButton.click();
    }
}
}
