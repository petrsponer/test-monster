import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class CreateAccountPage extends AbstractPage {

    private static final String URL = "https://www.monsterworksdemo.com/account/account-lite";
    private static final String PASSWORD = "Gogol-483";
    private static final String EMAIL_BASE = "@pojem.cz";
    private static final String WHERE_DID_YOU_HEAR_ITEM_NAME = "number:89314";
    private static final String CREATE_ACCOUNT_SELECTOR = "#main-content > div > div.ng-scope > div > ng-form > div > div > div:nth-child(8) > div > button:nth-child(2)";
    final static Logger LOG = LoggerFactory.getLogger(SavedJobsPage.class);

    @Override protected String getPageURL() {
        return URL;
    }

    @Override protected void tryOpenConditions() {
        $(By.id("c_elem_0")).waitUntil(visible, 20000);
        $(CREATE_ACCOUNT_SELECTOR).shouldBe(disabled);
    }

    public void clickCreateAccout() {
        $(CREATE_ACCOUNT_SELECTOR).shouldBe(enabled);
        $(CREATE_ACCOUNT_SELECTOR).click();
        LOG.info("Creating Account");
    }

    public void prepareAccountData() {
        String email = UUID.randomUUID().toString() + EMAIL_BASE;
        $(By.id("c_elem_0")).setValue(email);
        $(By.id("a_elem_1")).setValue(PASSWORD);
        $(By.id("a_elem_2")).setValue(PASSWORD);
        $(By.id("elem_3")).selectOptionByValue(WHERE_DID_YOU_HEAR_ITEM_NAME);
        $(By.id("id_option_label_elem_5-true")).click(); // consent

        LOG.info("Filled data for a new account. Email: {}, Password: {}", email, PASSWORD);
    }
}
