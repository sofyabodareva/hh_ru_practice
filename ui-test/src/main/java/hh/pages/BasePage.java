package hh.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;
// import static com.codeborne.selenide.Selenide.page;

public class BasePage {
    private static final String BASE_ELEMENT_XPATH = "//div[contains(@name,'%s')]";
    protected final SelenideElement basePage;
    protected final Class<? extends BasePage> pageClass;

    protected BasePage(Class<? extends BasePage> pageClass, String type, String baseElementXpath){
        basePage = $x(String.format(baseElementXpath, type));
        this.pageClass = pageClass;
    }

    public <T extends BasePage> T refresh(){
        Selenide.refresh();
        return (T) page(pageClass);
    }

    public <T extends BasePage> T page(Class<T> pageClass){
        return page(pageClass);
    }
}
