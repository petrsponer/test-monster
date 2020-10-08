import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SearchPhilipsJobsPage extends AbstractPage {

    private static final String URL = "https://browse.monsterworksdemo.com/search/?cn=Philips";
    private static final By BY_PHILIPS_JOBS = By.linkText("Philips Jobs");
    private SelenideElement philipsJobsBtn;

    @Override protected String getPageURL() {
        return URL;
    }

    @Override protected void tryOpenConditions() {
        $(byText("Philips Jobs & Careers")).waitUntil(visible, 20000);
    }

    public String clickItem(int position) {
        ElementsCollection jobs = $$(By.xpath("//div[@class='mux-search-results']/div/section/div"));
        return clickJobAndGetName(jobs.get(position));
    }

    public String clickLastItem() {
        ElementsCollection jobs = $$(By.xpath("//div[@class='mux-search-results']/div/section/div"));
        return clickJobAndGetName(jobs.get(jobs.size()));
    }

    public void saveJob() {
        $(By.linkText("Save")).waitUntil(visible, 20000);
        $(By.linkText("Save")).click();
    }

    public void assertJobSaved() {
        $(byText("Job has been saved to your account.")).waitUntil(visible, 40000);
        $(byClassName("icon icon-save")).waitUntil(visible, 40000);
    }

    public void goToSavedJobs() {
        $(By.id("dropdown-My-job-search")).hover();
        $(By.xpath("//a[contains(text(),'Saved Jobs')]")).click();
    }

    private String clickJobAndGetName(SelenideElement job) {
        job.click();
        String jobText = job.getText();
        String[] parts = jobText.split("\n");
        jobText = parts[0];
        return jobText;
    }

}
