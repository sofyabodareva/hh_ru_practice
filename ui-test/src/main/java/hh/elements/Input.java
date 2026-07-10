package hh.elements;

public class Input extends BaseElement{
    private static final String ID_XPATH = "//input[@id='%s']";
    private static final String NAME_XPATH = "//input[@name='%s']";

    private Input(String xpath, String param){
        // super(xpath, param);
        super(ID_XPATH, param, NAME_XPATH);
    }

    // public void fill(String value){
    //     clear();
    //     sendKeys(baseElement, value);
    // }

}
