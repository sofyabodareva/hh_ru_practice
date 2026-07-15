package hh.pages;

import static com.codeborne.selenide.Selenide.$x;

// Страница откликов
public class ResponsesPage extends BasePage {

    private static final String RESPONSES_PAGE_TYPE = "negotiations";
    private static final String BASE_ELEMENT_XPATH = "//div[contains(@name,'%s')]";

    private static final String NEGOTIATION_ITEM_XPATH =
        "//*[@data-qa='negotiations-item-vacancy' and text()='%s']";

    public ResponsesPage() {
        super(ResponsesPage.class, RESPONSES_PAGE_TYPE, BASE_ELEMENT_XPATH);
    }

    // Проверка наличия вакансии с таким названием в списке
    public boolean isVacancyInList(String vacancyName) {
        // Ищем элемент с точным совпадением текста и data-qa
        return $x(String.format(NEGOTIATION_ITEM_XPATH, vacancyName)).isDisplayed();
    }
}

