import org.testng.annotations.Test;

public class Test1 extends AbstractTest {

    String secondJobName;
    String lastJobName;

    @Test public void getToCreateAccount() {
        EntryPage entryPage = createPageWithAssert(EntryPage.class);
        entryPage.clickCreateAccout();
    }

    @Test(dependsOnMethods = "getToCreateAccount") public void createAccount() {
        CreateAccountPage createAccountPage = createPageWithAssert(CreateAccountPage.class);
        createAccountPage.prepareAccountData();
        createAccountPage.clickCreateAccout();
    }

    @Test(dependsOnMethods = "createAccount") public void getToSearchPhilipsJobsPage() {
        DashboardPage dashboardPage = createPageWithAssert(DashboardPage.class);
        dashboardPage.clickPhilipsJobs();
    }

    @Test(dependsOnMethods = "getToSearchPhilipsJobsPage") public void save2ndJob() {
        SearchPhilipsJobsPage searchPhilipsJobsPage = createPageWithAssert(SearchPhilipsJobsPage.class);
        secondJobName = searchPhilipsJobsPage.clickItem(1);
        searchPhilipsJobsPage.saveJob();
        searchPhilipsJobsPage.goToSavedJobs();
    }

    @Test(dependsOnMethods = "getToSearchPhilipsJobsPage") public void saveLastJob() {
        SearchPhilipsJobsPage searchPhilipsJobsPage = createPageWithAssert(SearchPhilipsJobsPage.class);
        lastJobName = searchPhilipsJobsPage.clickLastItem();
        searchPhilipsJobsPage.saveJob();
        searchPhilipsJobsPage.goToSavedJobs();
    }

    @Test(dependsOnMethods = "save2ndJob") public void check2ndJobSaved() {
        SavedJobsPage savedJobsPage = createPageWithAssert(SavedJobsPage.class);
        savedJobsPage.checkJobName(secondJobName);
    }

    @Test(dependsOnMethods = "saveLastJob") public void checkLastJobSaved() {
        SavedJobsPage savedJobsPage = createPageWithAssert(SavedJobsPage.class);
        savedJobsPage.checkJobName(lastJobName);
    }
}
