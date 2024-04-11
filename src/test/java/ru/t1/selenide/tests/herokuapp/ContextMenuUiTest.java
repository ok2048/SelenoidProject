package ru.t1.selenide.tests.herokuapp;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.interactions.Actions;
import ru.t1.selenide.tests.BaseUiTest;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class ContextMenuUiTest extends BaseUiTest {
    private final SelenideElement contextMenuButton = $x("//a[@href='/context_menu']");
    private final SelenideElement clickableArea = $x("//div[@id='hot-spot']");


    @Epic("Herokuapp UI tests")
    @Feature("Context Menu tests")
    @DisplayName("Positive scenario Context Menu test")
    @Test
    void contextMenuPositive() {
        contextMenuButton.should(visible).click();

        Actions actions = new Actions(getWebDriver());
        actions.contextClick(clickableArea)
                .perform();
        Alert alert = Selenide.switchTo().alert();
        Assertions.assertThat(alert.getText())
                .as("Check the text of alert message")
                .isEqualTo("You selected a context menu");
        alert.accept();
    }
}
