package hh.pages;

import hh.elements.Text;

/** Страница «Автопоиски вакансий». */
public class AutoSearchPage extends BasePage {

    private static final String AUTOSEARCH_ITEM_XPATH = "//*[@data-qa='autosearch-item' and contains(., '%s')]";

    public AutoSearchPage() {
        super(AutoSearchPage.class, "body", "//%s");
    }

    /** Проверяет наличие сохранённого автопоиска по названию должности. */
    public boolean isAutoSearchSaved(String query) {
        String xpath = String.format(AUTOSEARCH_ITEM_XPATH, query);
        return Text.byXpath(xpath).isDisplayed();
    }
}