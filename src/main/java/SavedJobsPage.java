import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;

public class SavedJobsPage extends AbstractPage {

    private static final String URL = "https://www.monsterworksdemo.com/savedJobs/";
    private static final By BY_PHILIPS_JOBS = By.linkText("Philips Jobs");
    private SelenideElement philipsJobsBtn;

    @Override protected String getPageURL() {
        return URL;
    }

    @Override protected void tryOpenConditions() {
    }

    public void checkJobName(String jobName) {
        try {
            $(By.xpath("//*[contains(text(),'" + jobName + "'")).exists();
            System.out.println(jobName + "exist on saved page");
        } catch (Exception e) {
            System.out.println(jobName + "does not exist on saved page");
        }
    }

}
