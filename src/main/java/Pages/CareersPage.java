package Pages;

import Utils.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CareersPage extends BasePage {

    public CareersPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public BasePage newInstance(WebDriver driver) {
        return new CareersPage(driver);
    }

    public JoinUsPage clickOpenPositions() {
        findElementFromRoot(By.className("contact-label-code")).click();
        return new JoinUsPage(getDriver());
    }
}
