package ru.t1.selenide.tests.herokuapp;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.interactions.Actions;
import ru.t1.selenide.tests.BaseUiTest;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class InfiniteScrollUiTest extends BaseUiTest {
    private final SelenideElement infiniteScrollButton = $x("//a[@href='/infinite_scroll']");
    private final String JSCROLL_XPATH = "//div[@class='jscroll-added' and contains(text(), '%s')]";

    @Epic("Herokuapp UI tests")
    @Feature("Infinite Scroll tests")
    @DisplayName("Positive scenario Infinite Scroll test")
    @Test
    void infiniteScrollPositive() {
        infiniteScrollButton.should(visible).click();

        String wordToFind = "eius";
        SelenideElement jScrollToFind = $x(JSCROLL_XPATH.formatted(wordToFind));
        Actions actions = new Actions(getWebDriver());
        int tries = 0;
        // Во избежание бесконечного цикла будем скроллить максимум 500 раз
        while (!jScrollToFind.isDisplayed() && tries < 500) {
            actions.scrollByAmount(0, 100)
                    .perform();
            tries++;
        }

        jScrollToFind.should(visible);
    }
}
