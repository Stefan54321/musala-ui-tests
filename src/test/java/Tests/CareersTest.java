package Tests;

import Pages.CareersPage;
import Pages.HomePage;
import Pages.JoinUsPage;
import Utils.BaseTestClass;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CareersTest extends BaseTestClass {

    private HomePage homePage;
    private CareersPage careersPage;
    private JoinUsPage joinUsPage;

    private static final String EXPECTED_URL = "https://www.musala.com/careers/join-us/";
    private static final List<String> EXPECTED_SECTIONS = new ArrayList<>(Arrays.asList("General description", "Requirements", "Responsibilities", "What we offer"));
    private static final String EXPECTED_APPLICATION_RESPONSE = "One or more fields have an error. Please check and try again.";

    @Override
    protected void beforeClassExtended() {
        homePage = new HomePage(driver);
        homePage = (HomePage) homePage.navigateTo(baseUrl, homePage);
    }

    @Test(priority = 1, description = "Check if the Join Us page loads successfully.")
    public void checkJoinUsPage() {
        careersPage = homePage.navigateToCareersPage();
        joinUsPage = careersPage.clickOpenPositions();
        Assert.assertEquals(driver.getCurrentUrl(), EXPECTED_URL, "The page did not load properly!");
    }

    @Test(priority = 2, description = "Check if all job description sections are present.")
    public void checkJobSections() {
        joinUsPage.selectLocation("Anywhere");
        joinUsPage.selectPosition("Experienced Automation QA Engineer");
        Assert.assertTrue(joinUsPage.getHeaders().containsAll(EXPECTED_SECTIONS), "Some of the sections were not present!");
    }

    @Test(priority = 3, description = "Check if an error message is displayed when only a CV document is uploaded")
    public void checkUpload() {
        joinUsPage.clickApply();
        joinUsPage.uploadCV("src/test/resources/CV.txt");
        Assert.assertTrue(joinUsPage.checkInvalidResponseMessage(), "An error occurred while sending application");
    }
}
