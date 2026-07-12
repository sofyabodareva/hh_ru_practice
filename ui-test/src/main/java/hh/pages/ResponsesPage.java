package hh.pages;

import static com.codeborne.selenide.Selenide.$x;

// Страница откликов
public class ResponsesPage extends BasePage {

    public ResponsesPage() {
        super(ResponsesPage.class, "body", "//%s");
    }

    // Проверка наличия вакансии с таким названием в списке
    public boolean isVacancyInList(String vacancyName) {
        // Ищем элемент с точным совпадением текста и data-qa
        String xpath = String.format("//*[@data-qa='negotiations-item-vacancy' and text()='%s']", vacancyName);
        return $x(xpath).isDisplayed();
    }
}
