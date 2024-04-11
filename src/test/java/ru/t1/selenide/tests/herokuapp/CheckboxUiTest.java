package ru.t1.selenide.tests.herokuapp;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.t1.selenide.tests.BaseUiTest;
import ru.t1.selenide.tests.helper.UiAssertions;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class CheckboxUiTest extends BaseUiTest {
    private final SelenideElement checkboxButton = $x("//a[@href='/checkboxes']");
    private final ElementsCollection checkboxes = $$x("//input[@type='checkbox']");

    @Epic("Herokuapp UI tests")
    @Feature("Checkbox tests")
    @DisplayName("Positive scenario")
    @ParameterizedTest(name = "Checkbox{0} pressed first")
    @ValueSource(ints = {1, 2})
    void checkboxPositive(int firstIndex) {
        checkboxButton.should(visible).click();
        // Приведем индекс к ИТ-шному виду :)
        firstIndex--;

        // Сначала кликаем на checkbox с индексом firstIndex, а потом на оставшийся
        checkboxes.get(firstIndex).click();
        checkboxes.get(1 - firstIndex).click();

        UiAssertions.assertThat(checkboxes.get(0))
                .isChecked();
        UiAssertions.assertThat(checkboxes.get(1))
                .isNotChecked();
    }
}
