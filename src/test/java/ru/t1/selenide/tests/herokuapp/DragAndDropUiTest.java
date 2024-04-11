package ru.t1.selenide.tests.herokuapp;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.interactions.Actions;
import ru.t1.selenide.tests.BaseUiTest;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class DragAndDropUiTest extends BaseUiTest {
    private final SelenideElement dragAndDropButton = $x("//a[@href='/drag_and_drop']");
    private final SelenideElement draggableA = $x("//div[@id='column-a']");
    private final SelenideElement draggableAHeader = $x("//div[@id='column-a']/header");
    private final SelenideElement draggableB = $x("//div[@id='column-b']");
    private final SelenideElement draggableBHeader = $x("//div[@id='column-b']/header");

    @Epic("Herokuapp UI tests")
    @Feature("Drag and Drop tests")
    @DisplayName("Positive scenario Drag and Drop test")
    @Test
    void dragAndDropPositive() {
        dragAndDropButton.should(visible).click();

        Actions actions = new Actions(getWebDriver());
        actions.clickAndHold(draggableA)
                .moveToElement(draggableB)
                .release()
                .perform();

        draggableAHeader.should(text("B"));
        draggableBHeader.should(text("A"));
    }
}
