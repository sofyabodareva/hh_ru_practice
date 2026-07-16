package hh.pages;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

import hh.elements.Button;
import hh.elements.Input;
import hh.elements.Text;

// Страница карточки вакансии
public class VacancyPage extends BasePage {

    // --- Основные элементы ---
    private static final String RESPOND_BUTTON_DATA_QA = "vacancy-response-link-top"; // "Откликнуться"
    private static final String FAVORITE_BUTTON_DATA_QA = "vacancy-body-mark-favorite_false"; // "В избранное" (сердечко)
    private static final String MORE_OPTIONS_BUTTON_DATA_QA = "vacancy__more-actions"; // "Ещё" (три точки)
    private static final String HIDE_COMPANY_BUTTON_DATA_QA = "vacancy__blacklist-menu-add-employer"; // "Скрыть вакансии компании"
    private static final String HIDE_VACANCY_BUTTON_DATA_QA = "vacancy__blacklist-menu-add-vacancy"; // "Скрыть вакансию"

    // --- Модальное окно отклика ---
    private static final String ADD_COVER_LETTER_BUTTON_DATA_QA = "add-cover-letter"; // Показать поле для письма
    private static final String COVER_LETTER_INPUT_DATA_QA = "vacancy-response-popup-form-letter-input"; // Поле ввода письма
    private static final String SUBMIT_RESPONSE_BUTTON_DATA_QA = "vacancy-response-submit-popup"; // Отправить отклик (в модалке)
    private static final String MODAL_HEADER_DATA_QA = "modal-header-image";
    private static final String RESPONSE_STATUS_SENT_TEXT = "Резюме доставлено";

    private static final String VACANCY_TITLE_DATA_QA = "vacancy-title"; // Название вакансии
    private static final String EXPERIENCE_DATA_QA = "vacancy-experience"; // Требуемый опыт работы
    private static final String COMPANY_NAME_DATA_QA = "vacancy-company-name"; // Название компании
    private static final String PROFILE_MENU_BUTTON_DATA_QA = "applicantProfileDesktopDrop-button";
    private static final String BLACKLIST_MENU_ITEM_DATA_QA = "mainmenu_vacancyBlackList";
    private static final String RESPONSES_MENU_ITEM_DATA_QA = "vacancyResponses-button";
    private static final String FAVORITES_MENU_ITEM_DATA_QA = "favVacancies-button";
    private static final String NOTIFICATION_CLOSE_BUTTON_DATA_QA = "notification-close-button";

    private static final String RESUME_SELECT_CELL_XPATH = "//*[@data-qa='cell']"; // Элементы выпадающего списка имеют data-qa, начинающийся с 'magritte-select-option-'.
    // Исключаем 'magritte-select-option-list', т.к это не отдельная опция.
    private static final String RESUME_OPTION_XPATH = "//*[starts-with(@data-qa, 'magritte-select-option-') and @data-qa != 'magritte-select-option-list']";

    private final Button respondButton = Button.byDataQa(RESPOND_BUTTON_DATA_QA);
    private final Button favoriteButton = Button.byDataQa(FAVORITE_BUTTON_DATA_QA);
    private final Button moreOptionsButton = Button.byDataQa(MORE_OPTIONS_BUTTON_DATA_QA);
    private final Button hideCompanyButton = Button.byDataQa(HIDE_COMPANY_BUTTON_DATA_QA);
    private final Button hideVacancyButton = Button.byDataQa(HIDE_VACANCY_BUTTON_DATA_QA);
    private final Button profileMenuButton = Button.byDataQa(PROFILE_MENU_BUTTON_DATA_QA);
    private final Button blacklistMenuItem = Button.byDataQa(BLACKLIST_MENU_ITEM_DATA_QA);
    private final Button responsesMenuItem = Button.byDataQa(RESPONSES_MENU_ITEM_DATA_QA);
    private final Button favoritesMenuItem = Button.byDataQa(FAVORITES_MENU_ITEM_DATA_QA);
    private final com.codeborne.selenide.SelenideElement notificationCloseBtn = $x("//*[@data-qa='" + NOTIFICATION_CLOSE_BUTTON_DATA_QA + "']");

    private final Button addCoverLetterButton = Button.byDataQa(ADD_COVER_LETTER_BUTTON_DATA_QA);
    private final Input coverLetterInput = Input.byDataQa(COVER_LETTER_INPUT_DATA_QA);
    private final Button submitResponseButton = Button.byDataQa(SUBMIT_RESPONSE_BUTTON_DATA_QA);
    private final Text modalHeader = Text.byDataQa(MODAL_HEADER_DATA_QA);
    private final Text responseStatusSent = Text.byExactText(RESPONSE_STATUS_SENT_TEXT);
    private final Text vacancyTitleText = Text.byDataQa(VACANCY_TITLE_DATA_QA);
    private final Text experienceText = Text.byDataQa(EXPERIENCE_DATA_QA);
    private final Text companyNameText = Text.byDataQa(COMPANY_NAME_DATA_QA);

    public VacancyPage() {
        super(VacancyPage.class, "body", "//%s");
    }

    public VacancyPage clickRespond() {
        respondButton.click();
        return this;
    }

    public void clickFavorite() {
        favoriteButton.click();
    }

    public void clickMoreOptions() {
        moreOptionsButton.click();
    }

    public void clickHideCompany() {
        hideCompanyButton.click();
    }

    public void clickHideVacancy() {
        hideVacancyButton.click();
    }

    public void scrollToTop() {
        com.codeborne.selenide.Selenide.sleep(3000);
        com.codeborne.selenide.Selenide.executeJavaScript("window.scrollTo(0, 0);");
        com.codeborne.selenide.Selenide.sleep(500);
    }

    // Открывает выпадающий список и выбирает резюме по индексу.
    public void selectResumeByIndex(int index) {
        $x(RESUME_SELECT_CELL_XPATH).shouldBe(com.codeborne.selenide.Condition.visible).scrollIntoView("{block: \"center\"}").click();
        
        com.codeborne.selenide.SelenideElement option = $$x(RESUME_OPTION_XPATH).get(index);
        option.shouldBe(com.codeborne.selenide.Condition.visible).scrollIntoView("{block: \"center\"}").click();
        
        if ($x(RESUME_OPTION_XPATH).isDisplayed()) {
            $x(RESUME_SELECT_CELL_XPATH).shouldBe(com.codeborne.selenide.Condition.visible).scrollIntoView("{block: \"center\"}").click();
        }
    }

    public void clickAddCoverLetter() {
        addCoverLetterButton.click();
    }

    public void fillCoverLetter(String text) {
        coverLetterInput.fill(text);
    }

    public void submitResponse() {
        submitResponseButton.click();
    }

    // Проверяем закрытие модалки по исчезновению её элемента
    public boolean isResponsePopupClosed() {
        return modalHeader.isHidden();
    }

    // Проверяем отклик по плашке "Резюме доставлено"
    public boolean isResponseStatusSent() {
        return responseStatusSent.isDisplayed();
    }

    public String getVacancyTitle() {
        return vacancyTitleText.getText();
    }

    public String getExperienceText() {
        return experienceText.getText();
    }

    public String getCompanyName() {
        return companyNameText.getText();
    }

    public String getCompanyId() {
        String href = companyNameText.getAttribute("href");
        if (href != null && href.contains("/employer/")) {
            String[] parts = href.split("/employer/");
            if (parts.length > 1) {
                String idPart = parts[1];
                if (idPart.contains("?")) {
                    idPart = idPart.substring(0, idPart.indexOf("?"));
                }
                return idPart;
            }
        }
        return null;
    }

    public BlacklistPage goToBlacklist() {
        profileMenuButton.click();
        blacklistMenuItem.click();
        return new BlacklistPage();
    }

    public ResponsesPage goToResponses() {
        responsesMenuItem.click();
        return new ResponsesPage();
    }

    public FavoritesPage goToFavorites() {
        favoritesMenuItem.click();
        return new FavoritesPage();
    }

    public void closeNotificationIfExists() {
        com.codeborne.selenide.Selenide.sleep(1000);
        if (notificationCloseBtn.isDisplayed()) {
            notificationCloseBtn.click();
        }
    }

    public void closeChatikIfExists() {
        com.codeborne.selenide.SelenideElement chatikCloseBtn = $x("//*[@data-qa='chatik-close-chatik']");
        if (chatikCloseBtn.isDisplayed()) {
            chatikCloseBtn.click();
        }
    }
}
