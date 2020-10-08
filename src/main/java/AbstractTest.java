import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import static java.lang.String.format;

public abstract class AbstractTest {

		public AbstractTest() {
		}

		protected <T extends AbstractPage> T createPage(Class<T> clazz) {
				T page = null;
				try {
						page = clazz.newInstance();
				} catch (InstantiationException e) {
						e.printStackTrace();
				} catch (IllegalAccessException e) {
						e.printStackTrace();
				}
				return page;
		}

		protected <T extends AbstractPage> T createPageWithAssert(Class<T> clazz) {
				T page = createPage(clazz);
				assert(page.isOpen());
				return page;
		}


		protected <T extends AbstractPage> T openPage(Class<T> clazz) {
				T page = createPage(clazz);
				if (page != null) {
						return page;
				} else {
						throw new ExceptionInInitializerError(format("Something went wrong when creating %s",
								clazz.getCanonicalName()));
				}
		}

		protected <T extends AbstractPage> T openPageWithAssert(Class<T> clazz) {
				T page = openPage(clazz);
				assert(page.isOpen());
				return page;
		}
}
