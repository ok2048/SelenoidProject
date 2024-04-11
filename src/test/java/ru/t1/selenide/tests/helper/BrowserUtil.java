package ru.t1.selenide.tests.helper;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.experimental.UtilityClass;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static ru.t1.selenide.tests.constant.Constants.*;

@UtilityClass
public class BrowserUtil {

    public static void initBrowser() throws MalformedURLException {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true)
                .includeSelenideSteps(true));
        DesiredCapabilities capabilities = new DesiredCapabilities(BROWSER, BROWSER_VERSION, Platform.LINUX);
        RemoteWebDriver driver = new RemoteWebDriver(new URL(SELENOID_URL), capabilities);
        WebDriverRunner.setWebDriver(driver);
        Selenide.open(BASE_URL);
    }
}
