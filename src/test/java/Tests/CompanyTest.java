package Tests;

import Forms.ContactUsForm;
import Pages.CompanyPage;
import Pages.FacebookPage;
import Pages.HomePage;
import Utils.BaseTestClass;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CompanyTest extends BaseTestClass {

    private HomePage homePage;
    private CompanyPage companyPage;
    private FacebookPage facebookPage;

    private static final String EXPECTED_URL = "https://www.musala.com/company/";
    private static final String EXPECTED_URL_FACEBOOK = "https://www.facebook.com/MusalaSoft?fref=ts";


    @Override
    protected void beforeClassExtended() {
        homePage = new HomePage(driver);
        homePage = (HomePage) homePage.navigateTo(baseUrl, homePage);
    }

    @Test(priority = 1, description = "Check if company page loads properly.")
    public void checkCompanyUrl() {
        companyPage = homePage.navigateToCompanyPage();
        Assert.assertEquals(driver.getCurrentUrl(), EXPECTED_URL, "The company page did not open!");
    }

    @Test(priority = 2, description = "Check if Leadership option is present.")
    public void checkLeadershipSectionExists() {
        Assert.assertTrue(companyPage.checkLeadershipMenu(), "Leadership option was not present");
    }

    @Test(priority = 3, description = "Check if the facebook profile picture is present.")
    public void checkFacebookLink() {
        facebookPage = companyPage.clickFacebookIcon();
        Assert.assertEquals(driver.getCurrentUrl(),EXPECTED_URL_FACEBOOK, "The company Facebook page did not open!");
        Assert.assertTrue(facebookPage.checkImageLoaded(),"Company profile image was not loaded");
    }

}
