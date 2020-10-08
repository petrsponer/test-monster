import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public abstract class AbstractPage {

		protected abstract String getPageURL();

		protected abstract void tryOpenConditions();

		public boolean isOpen() {
				try {
						tryOpenConditions();
						return true;
				} catch (Exception e) {
						// TODO - add logging error
						return false;
				}
		}
}
