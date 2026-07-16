package hh.elements;

import java.io.File;


public class InputFile extends BaseElement {

    private static final String DATA_QA_XPATH = "//input[@type='file' and @data-qa='%s']";

    private InputFile(String xpath, String param) {
        super(xpath, param);
    }

    public void uploadFile(String filePath) {
        baseElement.uploadFile(new File(filePath));
    }

    public static InputFile byDataQa(String dataQa) {
        return new InputFile(DATA_QA_XPATH, dataQa);
    }
}
