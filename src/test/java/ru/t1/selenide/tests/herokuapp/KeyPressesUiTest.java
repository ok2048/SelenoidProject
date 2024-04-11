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
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import ru.t1.selenide.tests.helper.BrowserUtil;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.closeWindow;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static ru.t1.selenide.tests.constant.Constants.BASE_URL;
import static ru.t1.selenide.tests.helper.DataGenerator.generateKeyPressedTestData;

public class KeyPressesUiTest {
    private final SelenideElement keyPressesButton = $x("//a[@href='/key_presses']");
    private final SelenideElement hintElement = $x("//p[@id='result']");


    @Epic("Herokuapp UI tests")
    @Feature("Key Presses tests")
    @TestFactory
    List<DynamicTest> keyPressesPositive() {
        List<DynamicTest> result = new ArrayList<>();
        // Генератор тестовых данных возвращает тип Object,
        // т.к. буквы имеют тип String, а управляющие символы - Keys.
        // Соответственно для Keys в имя теста и в ожидаемый текст передаем Keys.name(),
        // а для букв - строку с буквой.

        List<Object> testData = generateKeyPressedTestData(10);

        for (Object key : testData) {

            String keyName = (key instanceof Keys) ? ((Keys) key).name() : key.toString();

            result.add(DynamicTest.dynamicTest(
                    "Positive scenario Key Pressed - <%s>"
                            .formatted(keyName),
                    () -> {
                        BrowserUtil.initBrowser();
                        keyPressesButton.should(visible).click();
                        Actions actions = new Actions(getWebDriver());
                        actions.sendKeys(key.toString()).perform();
                        hintElement.should(visible)
                                .should(text("You entered: " + keyName.toUpperCase()));
                        closeWindow();
                    }
            ));
        }
        return result;
    }
}
