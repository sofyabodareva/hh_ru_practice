package hh.elements;
import java.lang.reflect.UndeclaredThrowableException;
import java.time.Duration;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class BaseElement {
    protected static final int WAIT_SECONDS = 5;
    protected final SelenideElement baseElement;

    protected BaseElement(String xpath, String attributeValue){
        this.baseElement = $x(String.format(xpath, attributeValue));
    }

    public boolean isDisplayed(){
        try {
            return baseElement
                .shouldBe(visible, Duration.ofSeconds(WAIT_SECONDS))
                .isDisplayed();
        } catch (UndeclaredThrowableException | ElementNotFound e){
            return false;
        }
    }

    public String getText(){
        return baseElement.shouldBe(visible, Duration.ofSeconds(WAIT_SECONDS)).getText();
    }

    public boolean isEnabled(){
        return baseElement.isEnabled();
    }
}