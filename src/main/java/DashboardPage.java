import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage extends AbstractPage {

    private static final String URL = "https://www.monsterworksdemo.com/dashboard/";
    private static final By BY_PHILIPS_JOBS = By.linkText("Philips Jobs");
    private SelenideElement philipsJobsBtn;
    final static Logger LOG = LoggerFactory.getLogger(DashboardPage.class);

    @Override protected String getPageURL() {
        return URL;
    }

    @Override protected void tryOpenConditions() {
        philipsJobsBtn = $(BY_PHILIPS_JOBS).waitUntil(exist, 20000);
    }

    public void clickPhilipsJobs() {
        philipsJobsBtn.scrollIntoView(true).click();
        LOG.info("Leaving page DashboardPage to SearchPhilipsJobsPage");
    }

}
