package hh.pages;

import hh.elements.Button;
import hh.elements.Input;
import hh.elements.Text;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

/**
 * Страница чатов (открывается в новом окне/вкладке).
 */
public class ChatPage extends BasePage {

    private static final String CHAT_ACTIVATOR_DATA_QA = "chatikActivator-button";
    private static final String OPEN_NEW_TAB_DATA_QA = "chatik-open-in-new-tab-button";
    private static final String CHAT_ITEMS_XPATH = "//*[starts-with(@data-qa, 'chatik-open-chat-')]";
    private static final String DISABLED_CHAT_XPATH = "//*[contains(text(), 'Работодатель отключил переписку')]";
    private static final String NEW_MESSAGE_INPUT_DATA_QA = "chatik-new-message-text";
    private static final String BUBBLE_TEXT_XPATH = "//*[@data-qa='chat-bubble-text']//p[text()='%s']";
    private static final String NO_AVAILABLE_CHATS_ERR = "Не найдено ни одного доступного чата для отправки сообщения!";

    private final Button chatActivator = Button.byDataQa(CHAT_ACTIVATOR_DATA_QA);
    private final Button openNewTabButton = Button.byDataQa(OPEN_NEW_TAB_DATA_QA);
    private final Input messageInput = Input.byDataQa(NEW_MESSAGE_INPUT_DATA_QA);
    private final ElementsCollection chatItems = $$x(CHAT_ITEMS_XPATH);
    private final com.codeborne.selenide.SelenideElement disabledChatMsg = $x(DISABLED_CHAT_XPATH);

    public ChatPage() {
        super(ChatPage.class, "body", "//%s");
    }

    /**
     * Открывает модалку чатов на текущей странице.
     */
    public void openChatModal() {
        Selenide.sleep(2000);
        chatActivator.click();
    }

    /**
     * Нажимает кнопку раскрытия чата на весь экран и переключается на новую вкладку.
     */
    public void expandToNewTab() {
        openNewTabButton.click();
        Selenide.switchTo().window(1);
        Selenide.sleep(3000);
    }

    /**
     * Проходит по списку чатов и выбирает первый, где не отключена переписка.
     */
    public void openFirstAvailableChat() {
        int chatCount = chatItems.size();
        
        for (int i = 0; i < chatCount; i++) {
            chatItems.get(i).click();
            Selenide.sleep(1500);
            
            boolean isDisabled = disabledChatMsg.is(Condition.visible);
            
            if (!isDisabled) {
                return;
            }
        }
        
        throw new RuntimeException(NO_AVAILABLE_CHATS_ERR);
    }

    /**
     * Отправляет сообщение в текущий открытый чат.
     *
     * @param text текст сообщения
     */
    public void sendMessage(String text) {
        messageInput.fill(text);
        messageInput.pressEnter();
    }

    /**
     * Проверяет, отображается ли в чате сообщение с заданным текстом.
     */
    public boolean isMessageDisplayed(String text) {
        String xpath = String.format(BUBBLE_TEXT_XPATH, text);
        return Text.byXpath(xpath).isDisplayed();
    }
}
