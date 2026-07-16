package hh.elements;


public class Image extends BaseElement {

    private static final String DATA_QA_XPATH = "//*[@data-qa='%s']";
    private static final String AVATAR_MODE_IMAGE_XPATH = "//*[@data-qa='%s']//div[contains(@class, 'mode-image')]";

    private Image(String xpath, String param) {
        super(xpath, param);
    }



    public static Image byDataQa(String dataQa) {
        return new Image(DATA_QA_XPATH, dataQa);
    }

    public static Image addedPhotoInContainerByDataQa(String containerDataQa){
        return new Image(AVATAR_MODE_IMAGE_XPATH, containerDataQa);
    }
}
