package hh;

import hh.pages.ResumeEditPage;
import hh.pages.MainPage;
import hh.pages.ProfilePage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ResumePhotoTest extends BaseTest {

    private static final String PHOTO_FILE_PATH = "src/test/resources/photo.jpg";
    private static final String PHOTO_NOT_ADDED_MESSAGE = "Фотография не была добавлена в резюме";

    /**
     * Тест №3: Добавление фотографии в резюме.
     * Проверяет, что после загрузки и подтверждения фотография
     * отображается в резюме.
     * <p>
     * Шаги: открыть список резюме в профиле -> открыть резюме на
     * редактирование -> открыть окно загрузки фотографии -> выбрать файл
     * фотографии -> подтвердить выбор -> проверить, что фотография
     * добавлена.
     * <p>
     * Ожидаемый результат: фотография добавлена в резюме.
     */
    @Test
    @DisplayName("Тест №3: Добавление фотографии в резюме")
    public void testAddPhotoToResume() {
        MainPage mainPage = MainPage.open();

        ProfilePage profilePage = mainPage.clickResumesAndProfile();

        ResumeEditPage resumeEditPage = profilePage.openResume();

        resumeEditPage.clickPhotoButton();
        resumeEditPage.uploadPhoto(PHOTO_FILE_PATH);
        resumeEditPage.confirmPhoto();

        Assertions.assertTrue(resumeEditPage.isPhotoAdded(), PHOTO_NOT_ADDED_MESSAGE);
    }
}