package hh;

import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import hh.pages.ChatPage;

public class ChatMessageTest extends BaseTest {

    private static final String MESSAGE_PREFIX = "Тестовое сообщение ";
    private static final String MESSAGE_NOT_FOUND_ERR = "Отправленное сообщение '%s' не найдено в чате";

    /**
     * Тест №6: Отправка сообщения в чате. Авторизуется, открывает модалку
     * чатов, переходит в полноэкранный режим (в новой вкладке), перебирает чаты
     * в поисках доступного (где не отключена переписка), отправляет случайное
     * сообщение и проверяет его появление в истории.
     */
    @Test
    @DisplayName("Тест №6: Отправка сообщения в чате")
    public void testSendChatMessage() {
        logger.info("Тест №6: Отправка сообщения в чате");
        logger.info("0) Авторизация");
        login(hh.utils.Config.getEmail(), hh.utils.Config.getPassword());

        ChatPage chatPage = new ChatPage();

        logger.info("1) На нижнем панели навигации нажать на кнопку «Сообщения»");
        chatPage.openChatModal();

        logger.info("2) Проверить, что экран переписки открылся (переход в полноэкранный режим)");
        chatPage.expandToNewTab();

        logger.info("3) Проверить, что открылась переписка с нужной компанией (выбор доступного чата)");
        chatPage.openFirstAvailableChat();

        String messageText = MESSAGE_PREFIX + UUID.randomUUID().toString().substring(0, 8);

        logger.info("4) Нажать на поле ввода сообщения, 5) Ввести сообщение, 6) Отправить сообщение");
        chatPage.sendMessage(messageText);

        logger.info("7) Убедиться, что сообщение появилось в диалоге, а поле ввода стало пустым");
        String errorMessage = String.format(MESSAGE_NOT_FOUND_ERR, messageText);
        Assertions.assertTrue(chatPage.isMessageDisplayed(messageText), errorMessage);

        logger.info("Тест №6: Отправка сообщения в чате завершён успешно");
    }
}
