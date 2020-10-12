import com.codeborne.selenide.ElementsCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import static java.lang.String.format;
import static org.testng.Assert.assertTrue;

/*
* This is a just an example how to do things. Best be the tests independent (create account, login to pre-existing account,
* save jobs...)
* however for the test purpose it is intentionly created as sequence of dependent tests.
* */
public class TestMosterPortal extends AbstractTest {

    String secondJobName;
    String lastJobName;

    final static Logger LOG = LoggerFactory.getLogger(TestMosterPortal.class);

// todo - add some negative tests
    @Test public void getToCreateAccount() {
        EntryPage entryPage = openPageWithAssert(EntryPage.class);
        entryPage.clickCreateAccount();
    }

    @Test(dependsOnMethods = "getToCreateAccount") public void createAccount() {
        CreateAccountPage createAccountPage = createPageWithAssert(CreateAccountPage.class);
        createAccountPage.prepareAccountData().clickCreateAccout();
    }

    @Test(dependsOnMethods = "createAccount") public void getToSearchPhilipsJobsPage() throws InterruptedException {
        DashboardPage dashboardPage = createPageWithAssert(DashboardPage.class);
        dashboardPage.clickPhilipsJobs();
    }

    @Test(dependsOnMethods = "getToSearchPhilipsJobsPage") public void saveJobs() {
       SearchPhilipsJobsPage searchPhilipsJobsPage = createPageWithAssert(SearchPhilipsJobsPage.class);
        ElementsCollection jobs = searchPhilipsJobsPage.getJobsCollection();

        secondJobName = searchPhilipsJobsPage.selectJob(jobs.get(1));
        searchPhilipsJobsPage.clickSaveJob();
        assertTrue(searchPhilipsJobsPage.isJobSaved());

        lastJobName = searchPhilipsJobsPage.selectJob(jobs.last());
        searchPhilipsJobsPage.clickSaveJob();
        assertTrue(searchPhilipsJobsPage.isJobSaved());

        searchPhilipsJobsPage.goToSavedJobs();
    }

    @Test(dependsOnMethods = "saveJobs") public void checkJobsSaved() {
        SavedJobsPage savedJobsPage = createPageWithAssert(SavedJobsPage.class);
        evalIsJobSaved(savedJobsPage.isJobSaved(secondJobName), secondJobName);
        evalIsJobSaved(savedJobsPage.isJobSaved(lastJobName), lastJobName);
    }

    private void evalIsJobSaved(boolean saved, String jobName) {
        if (saved) {
            LOG.info("{} exist on saved page", jobName);
        } else {
            String message = format("%s does not exist on saved page", jobName);
            LOG.error(message);
            Assert.fail(message);
        }
    }
}
