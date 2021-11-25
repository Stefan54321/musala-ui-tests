package Pages;

import Utils.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class JoinUsPage extends BasePage {


    private static final String EXPECTED_APPLICATION_RESPONSE = "One or more fields have an error. Please check and try again.";

    protected JoinUsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public BasePage newInstance(WebDriver driver) {
        return new JoinUsPage(driver);
    }

    public void selectLocation(String location) {
        WebElement dropDownLocator = findElementFromRoot(By.id("get_location"));
        dropDownLocator.findElements(By.tagName("option")).stream().filter(l -> l.getText().equals(location)).findFirst().get().click();
    }

    public void selectPosition(String position) {
        findElementFromRoot(By.cssSelector("img[alt='" + position + "']")).click();
        // WebElement openPositionsLocator = findElementFromRoot(By.id("join-us"));
        // openPositionsLocator.findElements(By.tagName("img")).stream().filter(p -> p.getText().equals(position)).findFirst().get().click();
    }

    public List<String> getHeaders() {
        List<WebElement> headerElements = findElementsFromRoot(By.tagName("h2"));
        List<String> headersList = new ArrayList<>();
        headerElements.stream().forEach(element -> headersList.add(element.getText()));
        return headersList;
    }

    public void clickApply() {
        findElementFromRoot(By.className("btn-join-us")).click();
    }

    public void uploadCV(String location) {
        WebElement uploadLink = findElementFromRoot(By.id("uploadtextfield"));
        uploadLink.sendKeys(location);
        findElementFromRoot(By.className("btn-cf-submit")).click();
        getWait().until(ExpectedConditions.presenceOfElementLocated(By.className("wpcf7-response-output")));
    }

    public Boolean checkInvalidResponseMessage() {
        return getWait().until(ExpectedConditions.textToBePresentInElementLocated(By.className("wpcf7-response-output"), EXPECTED_APPLICATION_RESPONSE)).equals(true);
    }

    public void printJobsFromCity(String city) {
        List<WebElement> jobs = findElementsFromRoot(By.className("card-jobsHot"));
        System.out.println(city);
        System.out.println();
        jobs.forEach(job -> {
            System.out.println("Position: " + job.findElement(By.tagName("img")).getAttribute("alt"));
            System.out.println();
            System.out.println("More info: " + job.findElement(By.className("card-jobsHot__link")).getAttribute("href"));
            System.out.println();
        });

    }
}
