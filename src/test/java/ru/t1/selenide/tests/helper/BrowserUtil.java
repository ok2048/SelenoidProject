package ru.t1.selenide.tests.helper;

import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.experimental.UtilityClass;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static com.codeborne.selenide.Selenide.open;
import static ru.t1.selenide.tests.constant.Constants.BASE_URL;

@UtilityClass
public class BrowserUtil {
    public static void initBrowser() throws MalformedURLException {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true)
                .includeSelenideSteps(true));
        DesiredCapabilities capabilities = new DesiredCapabilities("chrome", "120.0", Platform.LINUX);
        RemoteWebDriver driver = new RemoteWebDriver(new URL("http://176.109.105.178:4444/wd/hub/"), capabilities);
        WebDriverRunner.setWebDriver(driver);
        open(BASE_URL);
    }
}
