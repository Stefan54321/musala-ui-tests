package Forms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Models.PersonDetails;
import Utils.BasePage;

public class ContactUsForm extends BasePage {

    @FindBy(id = "contact_form_pop")
    public WebElement popUpFormLocator;

    public ContactUsForm(WebDriver driver) {
        super(driver);
    }

    @Override
    public BasePage newInstance(WebDriver driver) {
        return new ContactUsForm(driver);
    }

    public void insertContactDataAndSend(PersonDetails details) {
        clearAndSendKeys(findElement(popUpFormLocator, By.id("cf-1")),details.getName());
        clearAndSendKeys(findElement(popUpFormLocator, By.id("cf-2")),details.getEmail());
        clearAndSendKeys(findElement(popUpFormLocator, By.id("cf-3")),details.getMobilePhone());
        clearAndSendKeys(findElement(popUpFormLocator, By.id("cf-4")),details.getSubject());
        clearAndSendKeys(findElement(popUpFormLocator, By.id("cf-5")),details.getMessage());
        findElement(popUpFormLocator, By.className("btn-cf-submit")).click();
    }

    public String getEmailErrorMessage() {
        return findElementFromRoot(By.className("wpcf7-not-valid-tip")).getText();
    }

}
