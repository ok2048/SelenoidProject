package ru.t1.selenide.tests.herokuapp;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import ru.t1.selenide.tests.BaseUiTest;

import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class InputsUiNegativeTest extends BaseUiTest {
    private final SelenideElement inputsButton = $x("//a[@href='/inputs']");
    private final SelenideElement input = $x("//input[@type='number']");

    @Epic("Herokuapp UI tests")
    @Feature("Inputs tests")
    @DisplayName("Negative scenario")
    @ParameterizedTest(name = "Input - {2} (\"{0}\")")
    @CsvFileSource(resources = "/testDataNegative.csv")
    @Tag("negative")
    void inputsNegative(String inputText, String expected, String testName) {
        inputsButton.should(visible).click();
        input.should(visible).sendKeys(inputText);
        input.should(value(expected));
    }
}
