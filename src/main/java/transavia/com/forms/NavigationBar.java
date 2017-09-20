package transavia.com.forms;


import transavia.com.core.BasePage;
import transavia.com.pages.DestinationsPage;
import transavia.com.pages.LogInPage;

public abstract class NavigationBar extends BasePage {
    public abstract LogInPage goToLoginPage();
    public abstract DestinationsPage goToDestinationsPage();
}
