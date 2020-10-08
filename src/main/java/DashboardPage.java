import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage extends AbstractPage {

    private static final String URL = "https://www.monsterworksdemo.com/dashboard/";
    private static final By BY_PHILIPS_JOBS = By.linkText("Philips Jobs");
    private SelenideElement philipsJobsBtn;

    @Override protected String getPageURL() {
        return URL;
    }

    @Override protected void tryOpenConditions() {
        philipsJobsBtn = $(BY_PHILIPS_JOBS).waitUntil(exist, 20000);
    }

    public void clickPhilipsJobs() {
        philipsJobsBtn.scrollIntoView(true).click();
    }

}
