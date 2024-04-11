package ru.t1.selenide.tests.herokuapp;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.t1.selenide.tests.BaseUiTest;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class HoversUiTest extends BaseUiTest {
    private final SelenideElement hoversButton = $x("//a[@href='/hovers']");
    private final ElementsCollection images = $$x("//img[@alt='User Avatar']");
    private final ElementsCollection captions = $$x("//div[@class='figcaption']/h5");
    private final ElementsCollection profileLinks = $$x("//div[@class='figcaption']/a");

    @Epic("Herokuapp UI tests")
    @Feature("Hovers tests")
    @DisplayName("Positive scenario")
    @ParameterizedTest(name = "Hover{0} test")
    @ValueSource(ints = {1, 2, 3})
    void hoversPositive(int hoverNumber) {
        hoversButton.should(visible).click();
        images.get(hoverNumber - 1).should(visible).hover();
        captions.get(hoverNumber - 1)
                .should(visible)
                .should(text("name: user" + hoverNumber));
        profileLinks.get(hoverNumber - 1)
                .should(visible)
                .should(text("View profile"));
    }
}
