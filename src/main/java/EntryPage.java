import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class EntryPage extends AbstractPage {

    private static final String URL = "https://www.monsterworksdemo.com/home/";
    private static final By BY_CREATE_ACCOUNT = By.linkText("CREATE ACCOUNT");
    private SelenideElement createAccountBtn;
    final static Logger LOG = LoggerFactory.getLogger(EntryPage.class);

    @Override protected String getPageURL() {
        return URL;
    }

    @Override protected void tryOpenConditions() {
        createAccountBtn = $(BY_CREATE_ACCOUNT);
        createAccountBtn.waitUntil(visible, DEFAULT_WAIT_TIME);
    }

    public void clickCreateAccount() {
        createAccountBtn.click();
        LOG.info("Leaving page SearchPhilipsJobsPage to SavedJobsPage");
    }

}
