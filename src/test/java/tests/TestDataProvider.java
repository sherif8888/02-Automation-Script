package tests;
import org.testng.annotations.DataProvider;


public class TestDataProvider {

    //  Registration DataProvider

    @DataProvider(name = "registrationData")
    public static Object[][] getRegistrationData() {
        return new Object[][]{
                {"Male", "Amr", "Ayman", RandomUtils.generateRandomEmail(), "Password123", "Password123", "successful"},
                {"Female", "ashe", "bow", RandomUtils.generateRandomEmail(), "1234", "1234", "failed"},
                {"Male", "sherif", "Ahmed", RandomUtils.generateRandomEmail(), "Pass@1234", "Fail@1234", "failed"},
                {"Male", "Omar", "Ahmed", "", "Pass@1234", "Pass@1234", "failed"},
                {"Male", "Omar", "Ahmed", "sherif888", "Pass@1234", "Pass@1234", "failed"}

        };

    }

    //  Log in  DataProvider
    @DataProvider(name = "loginData")
    public static Object[][] getLoginData() {
        return new Object[][]{
                {"amrsnow202020@gmail.com", "Password123", "successful"},
                {"ashesnow10989@gmail.com", "1234", "failed"},
                {"sherifsnow9999@gmail.com", "wrongPassword", "failed"},
                {"Omarsnow9999@gmail.com", "pass@1234", "failed"}

        };
    }

}
