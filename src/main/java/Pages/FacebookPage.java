package Pages;

import Utils.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FacebookPage extends BasePage {
    protected FacebookPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public BasePage newInstance(WebDriver driver) {
        return new FacebookPage(driver);
    }

    public Boolean checkImageLoaded() {
        return findElementFromRoot(By.cssSelector("a[aria-label='Profile picture']")).isDisplayed();
    }

}
