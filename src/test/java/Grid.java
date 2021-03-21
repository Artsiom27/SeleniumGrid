import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import java.net.MalformedURLException;
import java.net.URL;

public class Grid {
    private WebDriver driver;
    private static final By ENTER_BUTTON = By.xpath("//a[@class = 'enter']");
    private static final By LOGIN_BUTTON = By.xpath("//*[@class=\"button m-green auth__enter\"]");
    private static final By USERNAME = By.name("login");
    private static final By PASSWORD = By.name("password");
    private static final By USERMENU = By.xpath("//span[@class = 'uname']");
    private static final String login = "seleniumtests@tut.by";
    private static final String parole = "123456789zxcvbn";
    private static final String USER_NAME_EXPECTED = "Selenium Test";


    @BeforeTest
    @Parameters("node")
    public void driverStart(String value) throws MalformedURLException {
        driver = new RemoteWebDriver(new URL(value), DesiredCapabilities.chrome());
    }

    @Test
    void loginTest() {
        driver.get("https://www.tut.by/");
        driver.findElement(ENTER_BUTTON).click();
        driver.findElement(USERNAME).sendKeys(login);
        driver.findElement(PASSWORD).sendKeys(parole);
        driver.findElement(LOGIN_BUTTON).click();
        Assert.assertEquals(USER_NAME_EXPECTED, driver.findElement(USERMENU).getText());
    }

    @AfterTest
    public void close() {
        driver.quit();
    }
}
