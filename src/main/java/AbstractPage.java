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
