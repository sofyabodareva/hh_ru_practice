package hh.elements;
import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;

public class Text extends BaseElement{

    private static final String DATA_QA_XPATH = "//*[@data-qa='%s']";
    private static final String EXACT_TEXT_XPATH = "//*[text()='%s']";
    private static final String XPATH = "%s";

    private Text(String xpath, String param){
        super(xpath, param);
    }

    public String getText(){
        return baseElement.shouldBe(visible, Duration.ofSeconds(WAIT_SECONDS)).getText();
    }

    public static Text byDataQa(String dataQa){
        return new Text(DATA_QA_XPATH, dataQa);
    }

    public static Text byExactText(String text) {
        return new Text(EXACT_TEXT_XPATH, text);
    }

    public static Text byXpath(String xpath) {
        return new Text(XPATH, xpath);
    }

}
