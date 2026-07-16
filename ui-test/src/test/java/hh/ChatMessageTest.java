package hh;

import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import hh.pages.ChatPage;
import hh.pages.LoginPage;

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
        new LoginPage().login(hh.utils.Config.getEmail(), hh.utils.Config.getPassword());

        ChatPage chatPage = new ChatPage();

        chatPage.openChatModal();
        chatPage.expandToNewTab();
        chatPage.openFirstAvailableChat();

        String messageText = MESSAGE_PREFIX + UUID.randomUUID().toString().substring(0, 8);

        chatPage.sendMessage(messageText);

        String errorMessage = String.format(MESSAGE_NOT_FOUND_ERR, messageText);
        Assertions.assertTrue(chatPage.isMessageDisplayed(messageText), errorMessage);

    }
}
