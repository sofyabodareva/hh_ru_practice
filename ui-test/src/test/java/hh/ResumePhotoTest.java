package hh;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import hh.pages.LoginPage;
import hh.pages.MainPage;
import hh.pages.ProfilePage;
import hh.pages.ResumeEditPage;

public class ResumePhotoTest extends BaseTest {

    private static final String PHOTO_FILE_PATH = "src/test/resources/cat.jpg";
    private static final String PHOTO_NOT_ADDED_MESSAGE = "Фотография не была добавлена в резюме";

    /**
     * Тест №7: Добавление фотографии в резюме. Проверяет, что после загрузки и
     * подтверждения фотография отображается в резюме.
     *
     * Шаги: открыть список резюме в профиле -> открыть резюме на редактирование
     * -> открыть окно загрузки фотографии -> выбрать файл фотографии ->
     * подтвердить выбор -> проверить, что фотография добавлена.
     *
     * Ожидаемый результат: фотография добавлена в резюме.
     */
    @Test
    @DisplayName("Тест №7: Добавление фотографии в резюме")
    public void testAddPhotoToResume() {
        logger.info("Тест №7: Добавление фотографии в резюме");
        logger.info("1. Авторизация");
        MainPage mainPage = new LoginPage().login(hh.utils.Config.getEmail(), hh.utils.Config.getPassword());

        logger.info("2. Переход на страницу профиля и резюме");
        ProfilePage profilePage = mainPage.clickResumesAndProfile();
        logger.info("3. Открытие резюме для редактированиия");
        ResumeEditPage resumeEditPage = profilePage.openResume();

        logger.info("4. Нажатие кнопки добавления фотографии в профиль");
        resumeEditPage.clickPhotoButton();
        logger.info("5. Добавление фотографии из файлов системы");
        resumeEditPage.uploadPhoto(PHOTO_FILE_PATH);
        logger.info("6. Подтверждение выбора");
        resumeEditPage.confirmPhoto();
        
        logger.info("7. Проверка добавления фотографии в резюме");
        Assertions.assertTrue(resumeEditPage.isPhotoAdded(), PHOTO_NOT_ADDED_MESSAGE);
        logger.info("Тест №7: Тест добавление фотографии в резюме завершён успешно");
    }
}
