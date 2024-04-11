package ru.t1.selenide.tests.helper;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebElement;

public class UiAssertions extends AbstractAssert<UiAssertions, WebElement> {

    private static final String CHECKED = "checked";

    private UiAssertions(WebElement actual) {
        super(actual, AbstractAssert.class);
    }

    public static UiAssertions assertThat(WebElement actual) {
        return new UiAssertions(actual);
    }

    public UiAssertions isChecked() {
        Assertions.assertThat(actual.getAttribute(CHECKED))
                .as("Check that element is checked")
                .isNotNull(); // Признаком взведенного checkbox'а является наличие взведенного атрибута checked
        return this;
    }

    public UiAssertions isNotChecked() {
        Assertions.assertThat(actual.getAttribute(CHECKED))
                .as("Check that element is not checked")
                .isNull();
        return this;
    }

}
