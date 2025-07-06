import Pages.LoginPage;
import io.qameta.allure.testng.AllureTestNg;
import org.example.Main;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.Excelutility;

import java.io.IOException;
@Listeners({AllureTestNg.class})
public class GoogleSearchTest {

    WebDriver driver;
    LoginPage login;

    static Logger log = LoggerFactory.getLogger(Main.class);

    @BeforeMethod
    public void Launch() {
        String url = ConfigReader.get("url");

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(); // ✅ assign to class-level driver

        driver.get(url);
        driver.manage().window().maximize();

        login = new LoginPage(driver); // ✅ Initialize after driver is ready

        System.out.println("Page Title: " + driver.getTitle());
    }

    @Test(dataProvider = "loginData")
    public void testLogin(String user, String pass) {
        login.login(user, pass);
        // Add assertion if needed
        log.info("INFO: Application started");
        log.debug("DEBUG: Executing business logic");
        log.warn("WARNING: Something may go wrong");
        log.error("ERROR: Something went wrong");
        System.out.println("✅ Logging completed. Check console.");
    }

    @DataProvider(name = "loginData")
    public Object[][] loginData() throws IOException {
        return Excelutility.getLoginData("src/test/resources/testdata.xlsx", "Sheet1");
    }
}
