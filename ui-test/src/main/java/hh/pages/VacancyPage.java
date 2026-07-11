package hh.pages;

import hh.elements.Button;
import hh.elements.Input;

import static com.codeborne.selenide.Selenide.$x;

// Страница карточки вакансии
public class VacancyPage extends BasePage {

    // --- Основные элементы ---
    private final Button respondButton = Button.byDataQa("vacancy-response-link-top"); // "Откликнуться"
    private final Button favoriteButton = Button.byDataQa("vacancy-body-mark-favorite_false"); // "В избранное" (сердечко)
    private final Button moreOptionsButton = Button.byDataQa("vacancy__more-actions"); // "Ещё" (три точки)
    private final Button hideCompanyButton = Button.byDataQa("vacancy__blacklist-menu-add-employer"); // "Скрыть вакансии компании"

    // --- Модальное окно отклика ---
    private final Button addCoverLetterButton = Button.byDataQa("add-cover-letter"); // Показать поле для письма
    private final Input coverLetterInput = Input.byDataQa("vacancy-response-popup-form-letter-input"); // Поле ввода письма
    private final Button submitResponseButton = Button.byDataQa("vacancy-response-submit-popup"); // Отправить отклик (в модалке)

    public VacancyPage() {
        super(VacancyPage.class, "body", "//%s");
    }

    public VacancyPage clickRespond() {
        respondButton.click();
        return this;
    }

    public VacancyPage clickFavorite() {
        favoriteButton.click();
        return this;
    }

    public VacancyPage clickMoreOptions() {
        moreOptionsButton.click();
        return this;
    }

    public VacancyPage clickHideCompany() {
        hideCompanyButton.click();
        return this;
    }

    public VacancyPage clickAddCoverLetter() {
        addCoverLetterButton.click();
        return this;
    }

    public VacancyPage fillCoverLetter(String text) {
        coverLetterInput.fill(text);
        return this;
    }

    public VacancyPage submitResponse() {
        submitResponseButton.click();
        return this;
    }

    // Проверяем закрытие модалки по исчезновению её элемента
    public boolean isResponsePopupClosed() {
        return !$x("//*[@data-qa='modal-header-image']").isDisplayed(); 
    }

    // Проверяем отклик по плашке "Резюме доставлено"
    public boolean isResponseStatusSent() {
        return $x("//div[text()='Резюме доставлено']").isDisplayed();
    }
}
