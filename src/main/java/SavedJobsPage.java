import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.$;

public class SavedJobsPage extends AbstractPage {

    private static final String URL = "https://www.monsterworksdemo.com/savedJobs/";
    final static Logger LOG = LoggerFactory.getLogger(SavedJobsPage.class);

    @Override protected String getPageURL() {
        return URL;
    }

    @Override protected void tryOpenConditions() {
    }

    public boolean isJobSaved(String jobName) {
        return $(By.xpath("//*[contains(text(),'" + jobName + "'")).exists();
    }

}
