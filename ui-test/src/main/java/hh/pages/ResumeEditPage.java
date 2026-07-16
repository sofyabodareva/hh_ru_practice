package hh.pages;

import hh.elements.Button;
import hh.elements.Image;
import hh.elements.InputFile;

public class ResumeEditPage extends BasePage {

    private static final String RESUME_EDIT_PAGE_TYPE = "resume";
    private static final String BASE_ELEMENT_XPATH = "//*[@data-qa='%s']";

    private static final String PHOTO_BUTTON_DATA_QA = "resume-avatar-edit-button"; 
    private static final String PHOTO_FILE_INPUT_DATA_QA = "magritte-single-upload-input";
    private static final String PHOTO_CONFIRM_BUTTON_DATA_QA = "resume-photo-editor-select";
    private static final String PHOTO_IMAGE_DATA_QA = "resume-avatar";

    private static final long DELAY = 10_000;

    private final Button photoButton = Button.byDataQa(PHOTO_BUTTON_DATA_QA);
    private final InputFile photoFileInput = InputFile.byDataQa(PHOTO_FILE_INPUT_DATA_QA);
    private final Button photoConfirmButton = Button.byDataQa(PHOTO_CONFIRM_BUTTON_DATA_QA);
    private final Image avatar = Image.addedPhotoInContainerByDataQa(PHOTO_IMAGE_DATA_QA);

    public ResumeEditPage() {
        super(ResumeEditPage.class, RESUME_EDIT_PAGE_TYPE, BASE_ELEMENT_XPATH);
    }

    public void clickPhotoButton() {
        photoButton.click();
    }

    public void uploadPhoto(String filePath) {
        photoFileInput.uploadFile(filePath);
    }

    public void confirmPhoto() {
        long end = System.currentTimeMillis() + DELAY;

        while (System.currentTimeMillis() < end) {
            photoConfirmButton.click();

            if (avatar.isDisplayed()) {
                return;
            }
        }
    }

    
    public boolean isPhotoAdded() {
        return avatar.isDisplayed();
    }
}