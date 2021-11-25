package Pages;


import Forms.ContactUsForm;
import Utils.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {


    public HomePage(WebDriver driver) {
        super(driver);
    }


    @Override
    public BasePage newInstance(WebDriver driver) {
        return new HomePage(driver);
    }

    public ContactUsForm clickContactUs() {
        findElementFromRoot(By.className("contact-label ")).click();
        return new ContactUsForm(getDriver());
    }


}
