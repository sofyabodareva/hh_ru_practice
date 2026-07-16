package hh.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class BasePage {
    protected final SelenideElement basePage;
    protected final Class<? extends BasePage> pageClass;

    protected BasePage(Class<? extends BasePage> pageClass, String type, String baseElementXpath){
        basePage = $x(String.format(baseElementXpath, type));
        this.pageClass = pageClass;
    }

   protected void refresh(){
        Selenide.refresh();
    }
}
