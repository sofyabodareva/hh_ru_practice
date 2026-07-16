package hh.pages;

import static com.codeborne.selenide.Selenide.$x;


public class ProfilePage extends BasePage {

    private static final String PROFILE_PAGE_TYPE = "resumes";
    private static final String BASE_ELEMENT_XPATH = "//*[@data-qa='%s']";

    private static final String RESUME_CARD_LINK_XPATH = "//a[starts-with(@data-qa, 'resume-card-link-')]";

    public ProfilePage() {
        super(ProfilePage.class, PROFILE_PAGE_TYPE, BASE_ELEMENT_XPATH);
    }

    public ResumeEditPage openResume() {
        $x(RESUME_CARD_LINK_XPATH).click();
        return new ResumeEditPage();
    }
}
