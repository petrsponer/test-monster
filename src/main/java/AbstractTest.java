import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.Arrays;

import static com.codeborne.selenide.Selenide.open;
import static java.lang.String.format;
import static org.testng.Assert.assertTrue;

public abstract class AbstractTest {

    protected final Logger LOG = LoggerFactory.getLogger(getClass());

    public AbstractTest() {
        System.setProperty("selenide.browser", "Chrome");
    }

    protected <T extends AbstractPage> T createPage(Class<T> clazz) {
        T page = null;
        try {
            page = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            LOG.error("Class {} cannot be created becouse: {}", clazz.getCanonicalName(),
                Arrays.toString(e.getStackTrace()));
            Assert.fail();
        }
        return page;
    }

    protected <T extends AbstractPage> T createPageWithAssert(Class<T> clazz) {
        T page = createPage(clazz);
        assertTrue(page.isOpen());
        return page;
    }

    protected <T extends AbstractPage> T openPage(Class<T> clazz) {
        T page = createPage(clazz);
        if (page != null) {
            open(page.getPageURL());
            return page;
        } else {
            throw new ExceptionInInitializerError(format("Something went wrong when creating %s",
                clazz.getCanonicalName()));
        }
    }

    protected <T extends AbstractPage> T openPageWithAssert(Class<T> clazz) {
        T page = openPage(clazz);
        assertTrue(page.isOpen());
        return page;
    }
}
