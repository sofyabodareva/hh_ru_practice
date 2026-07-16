package hh.elements;

import com.codeborne.selenide.Condition;
import java.time.Duration;

public class Button extends BaseElement{

    private static final String DATA_QA_XPATH = "//*[@data-qa='%s']";
    private static final String XPATH = "%s";

    private Button(String xpath, String param){
        super(xpath, param);
    }

    public void click(){
        baseElement.shouldBe(Condition.interactable, Duration.ofSeconds(WAIT_SECONDS)).click();
    }

    public static Button byDataQa(String dataQa){
        return new Button(DATA_QA_XPATH, dataQa);
    }

    public static Button byXpath(String xpath){
        return new Button(XPATH, xpath);
    }
}
