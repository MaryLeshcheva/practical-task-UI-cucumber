package hooks;
import io.cucumber.java.Before;
import io.cucumber.java.After;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.ListProductsPage;
import pages.MainPage;

import java.util.concurrent.TimeUnit;


public class Hooks {

    private static ListProductsPage objListProductsPage;
    private static MainPage objMainPage;
    private static WebDriver driver;

    @Before(value = "@UI", order = 1)
    public void init() {
        String path = System.getProperty("user.dir");
        String chromeDriverPath = path + "\\src\\test\\resources\\chromedriver.exe";

        ChromeOptions co = new ChromeOptions();
        co.setBinary(chromeDriverPath);

        driver = new ChromeDriver(co);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
    }

    @Before(value = "@UI", order = 2)
    public void initObjMainPage() {
        objMainPage = new MainPage(driver);
        objListProductsPage = new ListProductsPage(driver);
    }

    @Before(value = "@UI", order = 3)
    public void openMainPage() {
        driver.get("http://localhost:8080/");
    }

    @After(value = "@UI")
    public void close() {
        driver.quit();
    }

    public static MainPage getObjMainPage() {
        return objMainPage;
    }

    public static ListProductsPage getObjListProductsPage() {
        return objListProductsPage;
    }
}
