package hh.elements;

public class Button extends BaseElement{

    private static final String ID_XPATH = "//input[@id='%s']";
    private static final String NAME_XPATH = "//input[@name='%s']";

    private Button(String xpath, String param){
        super(xpath, param);
    }

    public void click(){
        baseElement.click();
    }
}
