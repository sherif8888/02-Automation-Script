package tests;
import org.testng.annotations.DataProvider;


public class TestDataProvider {

    //  Registration DataProvider

    @DataProvider(name = "registrationData")
    public static Object[][] getRegistrationData() {
        return new Object[][]{
                {"Male", "Amr", "Ayman", RandomUtils.generateRandomEmail(), "Password123", "Password123", "successful"}, //all right
                {"Male", "", "Ayman", RandomUtils.generateRandomEmail(), "Password123", "Password123", "failed"},   //no first name
                {"Male", "Amr", "", RandomUtils.generateRandomEmail(), "Password123", "Password123", "failed"},   //no last name
                {"Male", "Omar", "Ahmed", "", "Pass@1234", "Pass@1234", "failed"}, // no email
                {"Male", "Amr", "Ahmed", RandomUtils.generateRandomEmail(), "", "Password123", "failed"}, //no password
                {"Male", "Amr", "Ahmed", RandomUtils.generateRandomEmail(), "Password123", "", "failed"}, //no confierme password
                {"Female", "ashe", "bow", RandomUtils.generateRandomEmail(), "1234", "1234", "failed"},   // password less than 6 char
                {"Female", "ashe", "bow", RandomUtils.generateRandomEmail(), "123456", "6543221", "failed"},   // Not Match passwords
                {"Male", "Omar", "Ahmed", "sherif888", "Pass@1234", "Pass@1234", "failed"},// wrong email adrress
                {"Male", "Omar", "Ahmed", "sherif8719999@gmail.com", "Pass@1234", "Pass@1234", "failed"} // exesting email adrress

        };

    }

    //  Log in  DataProvider
    @DataProvider(name = "loginData")
    public static Object[][] getLoginData() {
        return new Object[][]{



                {"sherif8719999@gmail.com", "123456", "successful"},
                {"amrsnow202020@gmail.com", "Password", "failed"}, // wrong password
                {"", "Password123", "failed"}, // Empty email
                {"amrsnow202020@gmail.com", "", "failed"}, // wrong Password
                {"amrsnow202020@gmail", "Password123", "failed"}, // wrong email

        };
    }



    // Search DataProvider
    @DataProvider(name = "searchData")
    public static Object[][] getSearchData() {
        return new Object[][]{
                {"computer", "found"},
                {"laptop", "found"},
                {"abcxyz", "not_found"},
                {"", "not_found"}
        };
    }


    @DataProvider(name = "cartData")
    public static Object[][] getCartData() {
        return new Object[][]{
                {"https://demowebshop.tricentis.com/books", "The product has been added to your shopping cart"},
                {"https://demowebshop.tricentis.com/computers", "The product has been added to your shopping cart"}
        };
    }


}
