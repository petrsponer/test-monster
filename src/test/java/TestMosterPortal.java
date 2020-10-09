import com.codeborne.selenide.ElementsCollection;
import org.testng.annotations.Test;
/*
* This is a just an example how to do things. Best be the tests independent (create account, login to pre-existing account,
* save jobs...)
* however for the test purpose it is intentionly created as sequence of dependent tests.
* */
public class TestMosterPortal extends AbstractTest {

    String secondJobName;
    String lastJobName;

    @Test public void getToCreateAccount() {
        EntryPage entryPage = openPageWithAssert(EntryPage.class);
        entryPage.clickCreateAccout();
    }

    @Test(dependsOnMethods = "getToCreateAccount") public void createAccount() {
        CreateAccountPage createAccountPage = createPageWithAssert(CreateAccountPage.class);
        createAccountPage.prepareAccountData();
        createAccountPage.clickCreateAccout();
    }

    @Test(dependsOnMethods = "createAccount") public void getToSearchPhilipsJobsPage() throws InterruptedException {
        DashboardPage dashboardPage = createPageWithAssert(DashboardPage.class);
        Thread.sleep(4000); // this is a bad practice, should be better checking in a loop but it seems not to work for unknown reason
        dashboardPage.clickPhilipsJobs();
    }

    @Test(dependsOnMethods = "getToSearchPhilipsJobsPage") public void saveJobs() {
       SearchPhilipsJobsPage searchPhilipsJobsPage = createPageWithAssert(SearchPhilipsJobsPage.class);
        ElementsCollection jobs = searchPhilipsJobsPage.getJobsCollection();

        secondJobName = searchPhilipsJobsPage.clickItem(jobs.get(1));
        searchPhilipsJobsPage.saveJob();
        searchPhilipsJobsPage.assertJobSaved();

        lastJobName = searchPhilipsJobsPage.clickItem(jobs.last());
        searchPhilipsJobsPage.saveJob();
        searchPhilipsJobsPage.assertJobSaved();

        searchPhilipsJobsPage.goToSavedJobs();
    }

    @Test(dependsOnMethods = "saveJobs") public void checkJobsSaved() {
        SavedJobsPage savedJobsPage = createPageWithAssert(SavedJobsPage.class);
        savedJobsPage.checkJobName(secondJobName);
        savedJobsPage.checkJobName(lastJobName);
    }
}
