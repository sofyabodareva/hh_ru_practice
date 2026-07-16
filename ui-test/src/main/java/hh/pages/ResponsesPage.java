package hh.pages;

import hh.elements.Text;

/**
 * Страница откликов кандидата
 */
public class ResponsesPage extends BasePage {

    private static final String RESPONSES_PAGE_TYPE = "negotiations";
    private static final String BASE_ELEMENT_XPATH = "//div[contains(@name,'%s')]";

    private static final String NEGOTIATION_ITEM_XPATH =
        "//*[@data-qa='negotiations-item-vacancy' and text()='%s']";

    public ResponsesPage() {
        super(ResponsesPage.class, RESPONSES_PAGE_TYPE, BASE_ELEMENT_XPATH);
    }

    /**
     * Проверка наличия вакансии с точным совпадением названия в списке откликов.
     */
    public boolean isVacancyInList(String vacancyName) {
        String xpath = String.format(NEGOTIATION_ITEM_XPATH, vacancyName);
        return Text.byXpath(xpath).isDisplayed();
    }
}

