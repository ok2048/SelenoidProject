package ru.t1.selenide.tests.herokuapp;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import ru.t1.selenide.tests.constant.Constants;
import ru.t1.selenide.tests.helper.BrowserUtil;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static ru.t1.selenide.tests.constant.Constants.BASE_URL;
import static ru.t1.selenide.tests.helper.DataGenerator.generateInputTestData;

public class InputsUiTest {
    private final SelenideElement inputsButton = $x("//a[@href='/inputs']");
    private final SelenideElement input = $x("//input[@type='number']");

    @Epic("Herokuapp UI tests")
    @Feature("Inputs tests")
    @TestFactory
    List<DynamicTest> inputsPositive() {
        List<DynamicTest> result = new ArrayList<>();
        List<Integer> testData = generateInputTestData(10);

        for (int i = 0; i < testData.size(); i++) {
            final int index = i;
            result.add(DynamicTest.dynamicTest(
                    "Positive scenario Input - \"%d\"".formatted(testData.get(i)),
                    () -> {
                        BrowserUtil.initBrowser();
                        inputsButton.should(visible).click();
                        input.should(visible).sendKeys("" + testData.get(index));
                        input.should(value("" + testData.get(index)));
                        closeWindow();
                    }
            ));
        }
        return result;
    }

}
