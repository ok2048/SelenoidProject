package ru.t1.selenide.tests.herokuapp;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import ru.t1.selenide.tests.BaseUiTest;

import static com.codeborne.selenide.CollectionCondition.allMatch;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class DropdownUiTest extends BaseUiTest {
    private final SelenideElement dropdownButton = $x("//a[@href='/dropdown']");
    private final SelenideElement dropdown = $x("//select[@id='dropdown']");
    private final SelenideElement option1 = $x("//option[@value='1']");
    private final SelenideElement option2 = $x("//option[@value='2']");
    private final ElementsCollection options = $$x("//option");

    @Epic("Herokuapp UI tests")
    @Feature("Dropdown tests")
    @Test
    @DisplayName("Positive scenario Dropdown test")
    void dropdownPositive() {
        dropdownButton.should(visible).click();

        dropdown.should(visible).click();
        options.should(allMatch(
                "Check that options are visible",
                WebElement::isDisplayed
        ));
        option1.should(visible).click();
        dropdown.should(text("Option 1"));

        dropdown.should(visible).click();
        options.should(allMatch(
                "Check that options are visible",
                WebElement::isDisplayed
        ));
        option2.should(visible).click();
        dropdown.should(text("Option 2"));
    }
}
