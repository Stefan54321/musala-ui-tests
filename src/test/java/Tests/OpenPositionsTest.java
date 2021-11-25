package Tests;

import Pages.CareersPage;
import Pages.HomePage;
import Pages.JoinUsPage;
import Utils.BaseTestClass;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OpenPositionsTest extends BaseTestClass {

    private HomePage homePage;
    private CareersPage careersPage;
    private JoinUsPage joinUsPage;

    @Override
    protected void beforeClassExtended() {
        homePage = new HomePage(driver);
        homePage = (HomePage) homePage.navigateTo(baseUrl, homePage);
    }

    @Test(priority = 1,description = "List all jobs available in Sofia and Skopje")
    public void printAvailableJobs() {
        careersPage = homePage.navigateToCareersPage();
        joinUsPage = careersPage.clickOpenPositions();
        joinUsPage.selectLocation("Sofia");
        joinUsPage.printJobsFromCity("Sofia");
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.musala.com/careers/join-us/?location=Sofia"));
        joinUsPage.selectLocation("Skopje");
        joinUsPage.printJobsFromCity("Skopje");
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.musala.com/careers/join-us/?location=Skopje"));
    }
}
