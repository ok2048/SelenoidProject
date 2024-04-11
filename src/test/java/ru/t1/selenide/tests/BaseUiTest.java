package ru.t1.selenide.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import ru.t1.selenide.tests.helper.BrowserUtil;

import java.net.MalformedURLException;

import static com.codeborne.selenide.Selenide.closeWindow;

public abstract class BaseUiTest {

    @BeforeEach
    void setup() throws MalformedURLException {
        BrowserUtil.initBrowser();
    }

    @AfterEach
    void tearDown() {
        closeWindow();
    }

}
