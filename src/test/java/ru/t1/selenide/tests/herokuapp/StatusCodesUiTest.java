package ru.t1.selenide.tests.herokuapp;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.t1.selenide.tests.BaseUiTest;

import static com.codeborne.selenide.Condition.partialText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class StatusCodesUiTest extends BaseUiTest {
    private final SelenideElement statusCodesButton = $x("//a[@href='/status_codes']");
    private final String STATUS_LINK_XPATH = "//a[@href='status_codes/%d']";
    private final SelenideElement statusCodeText = $x("//h3/following-sibling::p");

    @Epic("Herokuapp UI tests")
    @Feature("Status Codes tests")
    @DisplayName("Positive scenario")
    @ParameterizedTest(name = "{0} status code")
    @ValueSource(ints = {200, 301, 404, 500})
    void statusCodeUiPositive(int statusCode) {
        statusCodesButton.should(visible).click();

        SelenideElement statusLink = $x(STATUS_LINK_XPATH.formatted(statusCode));
        statusLink.should(visible).click();
        statusCodeText.should(partialText("This page returned a %d status code.".formatted(statusCode)));
    }
}
