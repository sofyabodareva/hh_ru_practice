package hh.elements;



public class Input extends BaseElement{
    private static final String ID_XPATH = "//input[@id='%s']";
    private static final String NAME_XPATH = "//input[@name='%s']";

    private Input(String xpath, String param){
        super(xpath, param);
    }

    public void fill(String value){
        baseElement.clear();
        baseElement.sendKeys(value);
    }

    public static Input byld(String id){
        return new Input(ID_XPATH, id);
    }

    public static Input byName(String name){
        return new Input(NAME_XPATH, name);
    }

}
