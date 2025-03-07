package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SelenideWikiTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 5000; // default 4000
    }

    @Test
    void FillFormTest() {
        open("https://github.com/");
        $("button[aria-label='Search or jump to…']").click();
        $("#query-builder-test").setValue("selenide").pressEnter();
        $$("div.Box-sc-g0xbh4-0.JcuiZ div div a").first().click();
        $("#repository-container-header").shouldHave(text("selenide / selenide"));
        $("#wiki-tab").click();
        $("div.Layout-sidebar").$(byText("Pages")).hover();
        $("#wiki-pages-box")
                .$$("li.Box-row.wiki-more-pages-link")
                .findBy(text("Show 3 more pages…"))
                .$("button")
                .click();
        $("#wiki-pages-box")
                .$$("ul li a")
                .findBy(text("SoftAssertions"))
                .shouldBe(visible)
                .click();
        $("#wiki-body .markdown-body")
                .$$("div")
                .findBy(text("Using JUnit5 extend test class"))
                .shouldBe(visible);
    }
}