package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage {

    private final WebDriver driver;
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }
    private final By dropdownSandbox = By.id("navbarDropdown"); //локатор выпадающего списка Песочница

    private final By buttonProducts = By.xpath(".//a[text() = 'Товары']"); //локатор кнопки Товары

    public void clickDropdownSandbox() { // метод клика по выпадающему списку Песочница
        WebElement element = driver.findElement(dropdownSandbox);
        element.click();
    }
    public void clickButtonProducts() { // метод клика по кнопке Товары
        driver.findElement(buttonProducts).click();
    }

    public void openListProductsPage() { // метод открытия страницы Список товаров
        clickDropdownSandbox();
        clickButtonProducts();
    }
}
