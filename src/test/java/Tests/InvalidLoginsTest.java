package Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import Forms.ContactUsForm;
import Models.PersonDetails;
import Pages.HomePage;
import TestData.ContactDataProvider;
import Utils.BaseTestClass;

public class InvalidLoginsTest extends BaseTestClass {

    private HomePage homePage;
    private ContactUsForm contactUsForm;

    private static final String TEST_USER = "Stefan";
    private static final String TEST_MOBILE = "+38977123456";
    private static final String TEST_SUBJECT = "Testing";
    private static final String TEST_MESSAGE = "Test Automation with Selenium";
    private static final String EXPECTED_ERROR_MESSAGE = "The e-mail address entered is invalid.";

    @Override
    protected void beforeClassExtended() {

        homePage = new HomePage(driver);
        homePage = (HomePage) homePage.navigateTo(baseUrl, homePage);
        contactUsForm = homePage.clickContactUs();
    }

    @Test(dataProvider = "invalidEmailsDataProvider", dataProviderClass = ContactDataProvider.class, description = "Attempt to login with invalid email addresses")
    public void goToContactUsPage(String email) {
        contactUsForm.insertContactDataAndSend(PersonDetails.builder()
                .name(TEST_USER)
                .email(email)
                .mobilePhone(TEST_MOBILE)
                .subject(TEST_SUBJECT)
                .message(TEST_MESSAGE)
                .build());
        Assert.assertEquals(contactUsForm.getEmailErrorMessage(), EXPECTED_ERROR_MESSAGE, "The email response message was wrong!");
    }
}
