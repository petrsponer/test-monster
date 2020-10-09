import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$;
import static java.lang.String.format;

public class SavedJobsPage extends AbstractPage {

    private static final String URL = "https://www.monsterworksdemo.com/savedJobs/";
    final static Logger LOG = LoggerFactory.getLogger(SavedJobsPage.class);

    @Override protected String getPageURL() {
        return URL;
    }

    @Override protected void tryOpenConditions() {
    }

    public void checkJobName(String jobName) {
            if ($(By.xpath("//*[contains(text(),'" + jobName + "'")).exists()) {
                LOG.info("{} exist on saved page", jobName);
            } else {
                String message = format("%s does not exist on saved page", jobName);
                LOG.error(message);
                Assert.fail(message);
            }
    }

}
