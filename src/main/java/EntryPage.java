import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class EntryPage extends AbstractPage {

    private static final String URL = "https://www.monsterworksdemo.com/home/";
    private static final By BY_CREATE_ACCOUNT = By.linkText("CREATE ACCOUNT");
    private SelenideElement createAccoutBtn;

    @Override protected String getPageURL() {
        return URL;
    }

    @Override protected void tryOpenConditions() {
        createAccoutBtn = $(BY_CREATE_ACCOUNT);
        createAccoutBtn.waitUntil(visible, 20000);
    }

    public void clickCreateAccout() {
        createAccoutBtn.click();
    }

}
