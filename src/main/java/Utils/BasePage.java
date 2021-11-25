package Utils;


import Pages.CareersPage;
import Pages.CompanyPage;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Optional;

@Getter
public abstract class BasePage {

    private WebDriver driver;
    private WebDriverWait wait;
    private AjaxElementLocatorFactory factory;
    private Actions actions;


    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.factory = new AjaxElementLocatorFactory(driver, ConfigurationConstants.MAX_RETRY_PERIOD_AJAX_FACTORY);
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, ConfigurationConstants.MAX_RETRY_FOR_LOCATING_ELEMENT);
        actions = new Actions(driver);
    }

    public abstract BasePage newInstance(WebDriver driver);

    protected void moveToElement(WebElement element) {
        actions.moveToElement(element);
        actions.perform();
    }

    public <T extends BasePage> BasePage navigateTo(String url, T type) {
        driver.get(url);
        return type.newInstance(driver);
    }

    protected WebElement findElement(WebElement root, By locator) {
        wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(root, locator));
        return driver.findElement(locator);
    }

    protected WebElement findElementFromRoot(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return driver.findElement(locator);
    }

    protected List<WebElement> findElementsFromRoot(By locator) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        return driver.findElements(locator);
    }

    protected List<WebElement> findElements(WebElement root, By locator) {
        wait.until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(root, locator));
        return root.findElements(locator);
    }

    protected Optional<WebElement> getElementIfPresent(WebElement root, By locator) {
        try {
            return Optional.of(root.findElement(locator));
        } catch (Exception e) {
            return Optional.empty();
        }
    }


    protected List<WebElement> readHeaderMenuOptions() {
        return findElements(findElementFromRoot(By.id("menu")), By.tagName("a"));
    }

    public CompanyPage navigateToCompanyPage() {
        WebElement companyBtn = readHeaderMenuOptions().stream().filter(e -> e.getText().contains("COMPANY")).findFirst().orElse(null);
        if (companyBtn != null) companyBtn.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("footerEl-wrapper"))); //waiting for the footer to indicate that the whole page has loaded
        return new CompanyPage(driver);
    }

    public CareersPage navigateToCareersPage() {
        WebElement companyBtn = readHeaderMenuOptions().stream().filter(e -> e.getText().contains("CAREERS")).findFirst().orElse(null);
        if (companyBtn != null) companyBtn.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("footerEl-wrapper"))); //waiting for the footer to indicate that the whole page has loaded
        return new CareersPage(driver);
    }

    public void clearAndSendKeys(WebElement webElement, String keys) {
        webElement.clear();
        webElement.sendKeys(keys);
    }
}
