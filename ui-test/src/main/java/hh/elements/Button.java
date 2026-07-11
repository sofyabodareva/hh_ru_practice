package hh.elements;

public class Button extends BaseElement{

    private static final String ID_XPATH = "//input[@id='%s']";
    private static final String NAME_XPATH = "//input[@name='%s']";
    private static final String DATA_QA_XPATH = "//*[@data-qa='%s']";

    private Button(String xpath, String param){
        super(xpath, param);
    }

    public void click(){
        baseElement.click();
    }

    public static Button byDataQa(String dataQa){
        return new Button(DATA_QA_XPATH, dataQa);
    }
}
