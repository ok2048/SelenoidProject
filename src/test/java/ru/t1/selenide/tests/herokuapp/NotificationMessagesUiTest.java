package ru.t1.selenide.tests.herokuapp;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import ru.t1.selenide.tests.helper.BrowserUtil;

import java.net.MalformedURLException;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class NotificationMessagesUiTest {
    private static final SelenideElement notificationMessagesButton =
            $x("//a[@href='/notification_message']");
    private final SelenideElement clickArea = $x("//a[@href='/notification_message']");
    private final SelenideElement alertMessage = $x("//div[@id='flash']");
    private final SelenideElement closeAlertButton = $x("//a[@href='#' and @class='close']");

    @BeforeAll
    static void setup() throws MalformedURLException {
        BrowserUtil.initBrowser();
        notificationMessagesButton.should(visible).click();
    }

    @Epic("Herokuapp UI tests")
    @Feature("Notification Messages tests")
    @DisplayName("Positive scenario Notification Messages test")
    void notificationMessagesPositive() {
        closeAlertButton.should(visible).click();
        clickArea.should(visible).click();
        alertMessage.should(visible);
    }

    @AfterAll
    static void tearDown() {
        closeWindow();
    }
}
