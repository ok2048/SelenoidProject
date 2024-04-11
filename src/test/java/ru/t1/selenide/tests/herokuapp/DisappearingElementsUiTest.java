package ru.t1.selenide.tests.herokuapp;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import ru.t1.selenide.tests.helper.BrowserUtil;

import java.net.MalformedURLException;

import static com.codeborne.selenide.CollectionCondition.sizeLessThanOrEqual;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class DisappearingElementsUiTest {
    private static final SelenideElement disappearingElementsButton = $x("//a[@href='/disappearing_elements']");
    private final ElementsCollection elements = $$x("//li");

    @BeforeAll
    static void setup() throws MalformedURLException {
        BrowserUtil.initBrowser();
        disappearingElementsButton.should(visible).click();
    }

    @Epic("Herokuapp UI tests")
    @Feature("Disappearing Elements tests")
    @DisplayName("Positive scenario Disappearing elements test")
    @RepeatedTest(value = 10, name = "repetition {currentRepetition} of {totalRepetitions}")
    void disappearingElementsPositive() {
        refresh();
        elements.should(sizeLessThanOrEqual(5));
    }

    @AfterAll
    static void tearDown() {
        closeWindow();
    }

}
