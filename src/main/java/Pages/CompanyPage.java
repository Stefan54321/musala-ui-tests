package Pages;

import Utils.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

public class CompanyPage extends BasePage {
    public CompanyPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public BasePage newInstance(WebDriver driver) {
        return new CompanyPage(driver);
    }

    public Boolean checkLeadershipMenu() {
        WebElement companyPanel = findElementFromRoot(By.className("company-members"));
        return companyPanel.findElement(By.tagName("h2")).getText().equals("Leadership"); //checking if there is a header titled as Leadership
    }

    public FacebookPage clickFacebookIcon() {
        findElementFromRoot(By.className("musala-icon-facebook")).click();
        ArrayList<String> tabs = new ArrayList<String>(getDriver().getWindowHandles());
        getDriver().switchTo().window(tabs.get(1));
        return new FacebookPage(getDriver());
    }
}
