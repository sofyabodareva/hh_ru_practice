package hh.elements;

import com.codeborne.selenide.Condition;
import java.time.Duration;

public class Input extends BaseElement{
    private static final String ID_XPATH = "//input[@id='%s']";
    private static final String NAME_XPATH = "//input[@name='%s']";
    private static final String DATA_QA_XPATH = "//*[@data-qa='%s']";

    private Input(String xpath, String param){
        super(xpath, param);
    }

    public void fill(String value){
        baseElement.shouldBe(Condition.interactable, Duration.ofSeconds(WAIT_SECONDS)).clear();
        baseElement.sendKeys(value);
    }

    public static Input byId(String id){
        return new Input(ID_XPATH, id);
    }

    public static Input byName(String name){
        return new Input(NAME_XPATH, name);
    }

    public static Input byDataQa(String dataQa){
        return new Input(DATA_QA_XPATH, dataQa);
    }
}
