package steps;

import io.cucumber.java.ru.И;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.ListProductsPage;
import pages.MainPage;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class StepsForUITest {

    private ListProductsPage objListProductsPage;
    private MainPage objMainPage;
    protected static WebDriver driver;
    private static ChromeOptions co;

    @И("Инициализировать WebDriver")
    public void init() {
        String path = System.getProperty("user.dir");
        String chromeDriverPath = path + "\\src\\test\\resources\\chromedriver.exe";

        co = new ChromeOptions();
        co.setBinary(chromeDriverPath);

        driver = new ChromeDriver(co);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
    }

    @И("Инициализация objMainPage и objListProductsPage")
    public void initObjMainPage() {
        objMainPage = new MainPage(driver);
        objListProductsPage = new ListProductsPage(driver);
    }

    @И("Открыть Главную страницу")
    public void openMainPage() {
        driver.get("http://localhost:8080/");
    }

    @И("Открыть страницу Список товаров")
    public void openListProductsPage() {
        objMainPage.openListProductsPage();
    }
    @И("Нажать на кнопку Добавить")
    public void clickButtonProducts() {
        objListProductsPage.clickButtonAdd();
    }
    @И("Ввести наименование товара {string}")
    public void addName(String string) {
        objListProductsPage.addName(string);
    }
    @И("Выбрать тип Овощ")
    public void clickOptionTypeVegetable() {
        objListProductsPage.clickOptionTypeVegetable();
    }

    @И("Выбрать тип Фрукт")
    public void clickOptionTypeFruit() {
        objListProductsPage.clickOptionTypeFruit();
    }

    @И("Отметить чекбокс Экзотический")
    public void clickCheckboxExotic() {
        objListProductsPage.clickCheckboxExotic();
    }
    @И("Нажать на кнопку Сохранить")
    public void clickButtonSave() {
        objListProductsPage.clickButtonSave();
    }
    @И("Проверить наличие в таблице строки с атрибутами товара: {string} {string} {string}")
    public void checkNewProduct(String name, String type, String isExotic) {
        boolean isVegetableAdded = objListProductsPage.hasTableRow(name, type, isExotic);

        assertTrue(isVegetableAdded, "Ошибка! Овощ не добавлен.");
    }

    @И("Закрыть WebDriver")
    public void close() {
        driver.quit();
    }

}
