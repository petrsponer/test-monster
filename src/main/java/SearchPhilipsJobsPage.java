import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SearchPhilipsJobsPage extends AbstractPage {

    private static final String URL = "https://browse.monsterworksdemo.com/search/?cn=Philips";
    private static final String SAVED = "Saved";
    final static Logger LOG = LoggerFactory.getLogger(SearchPhilipsJobsPage.class);

    @Override protected String getPageURL() {
        return URL;
    }

    @Override protected void tryOpenConditions() {
        $(byText("Philips Jobs & Careers")).waitUntil(visible, DEFAULT_WAIT_TIME);
    }

    public String clickItem(SelenideElement job) {
        return clickJobAndGetName(job);
    }

    public ElementsCollection getJobsCollection() {
         return $$(By.xpath("//div[@class='mux-search-results']/div/section/div"));
    }

    public void saveJob() {
        $(By.linkText("Save")).waitUntil(visible, DEFAULT_WAIT_TIME);
        $(By.linkText("Save")).click();
    }

    public void assertJobSaved() {
        $(By.id("SaveJob")).$(By.className("label")).waitUntil(visible, DEFAULT_WAIT_TIME);
        assertEquals($(By.id("SaveJob")).$(By.className("label")).getText(), SAVED);
    }

    public void goToSavedJobs() {
        $(By.id("dropdown-My-job-search")).hover();
        $(By.xpath("//a[contains(text(),'Saved Jobs')]")).click();
        LOG.info("Leaving page SearchPhilipsJobsPage to SavedJobsPage");
    }

    private String clickJobAndGetName(SelenideElement job) {
        job.click();
        String jobText = job.getText();
        String[] parts = jobText.split("\n");
        jobText = parts[0];
        return jobText;
    }

}
