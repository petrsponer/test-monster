import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class EntryPage extends AbstractPage {

    private static final String URL = "https://www.monsterworksdemo.com/home/";
    private static final By BY_CREATE_ACCOUNT = By.linkText("CREATE ACCOUNT");
    private SelenideElement createAccoutBtn;
    final static Logger LOG = LoggerFactory.getLogger(EntryPage.class);

    @Override protected String getPageURL() {
        return URL;
    }

    @Override protected void tryOpenConditions() {
        createAccoutBtn = $(BY_CREATE_ACCOUNT);
        createAccoutBtn.waitUntil(visible, DEFAULT_WAIT_TIME);
    }

    public void clickCreateAccout() {
        createAccoutBtn.click();
        LOG.info("Leaving page SearchPhilipsJobsPage to SavedJobsPage");
    }

}
