package hh.elements;

import java.lang.reflect.UndeclaredThrowableException;
import java.time.Duration;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;

public class BaseElement {

    protected static final int WAIT_SECONDS = 15;
    protected final SelenideElement baseElement;

    protected BaseElement(String xpath, String attributeValue) {
        this.baseElement = $x(String.format(xpath, attributeValue));
    }

    public boolean isDisplayed() {
        try {
            return baseElement
                    .shouldBe(visible, Duration.ofSeconds(WAIT_SECONDS))
                    .isDisplayed();
        } catch (UndeclaredThrowableException | ElementNotFound e) {
            return false;
        }
    }

    public boolean isHidden() {
        try {
            baseElement.shouldBe(hidden, Duration.ofSeconds(WAIT_SECONDS));
            return true;
        } catch (UndeclaredThrowableException | com.codeborne.selenide.ex.ElementShould e) {
            return false;
        }
    }

    public boolean isEnabled() {
        return baseElement.isEnabled();
    }
}
