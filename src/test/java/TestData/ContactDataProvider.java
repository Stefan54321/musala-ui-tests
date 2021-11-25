package TestData;

import org.testng.annotations.DataProvider;

public class ContactDataProvider {

    @DataProvider(name = "invalidEmailsDataProvider")
    public Object[][] getInvalidEmails() {
        return new Object[][]{{"invalidEmail@test1"}, {"double@example@"}, {"someString"}, {"12345asd!@test"}, {"INVALID@EMAIL"}};
    }
}
