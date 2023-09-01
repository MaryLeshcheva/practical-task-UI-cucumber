package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ListProductsPage {

    private final WebDriver driver;
    public ListProductsPage(WebDriver driver) {
        this.driver = driver;
    }
    private final By buttonAdd = By.xpath(".//button[text() = 'Добавить']"); //локатор кнопки Добавить

    private final By inputName = By.id("name"); // локатор поля Наименование

    private final By optionTypeFruit = By.xpath(".//option[@value = 'FRUIT']"); // локатор варианта Фрукт в селекторе Тип

    private final By optionTypeVegetable = By.xpath(".//option[@value = 'VEGETABLE']"); // локатор варианта Овощ в селекторе Тип

    private final By checkboxExotic = By.xpath(".//input[@id = 'exotic']"); // чекбокс Экзотический

    private final By buttonSave = By.xpath(".//button[@id = 'save']"); // локатор кнопки Сохранить


    private void waitPopUpOpened() { // метод ожидания открытия поп-ап Добавление товара
        new WebDriverWait(driver, Duration.ofSeconds(1))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("editModalLabel")));
    }
    public void clickButtonAdd() { // метод клика по кнопке Добавить
        driver.findElement(buttonAdd).click();
        waitPopUpOpened();
    }

    public void addName(String name) { // метод добавления названия овоща/фрукта
        driver.findElement(inputName).sendKeys(name);
    }

    public void clickOptionTypeFruit() { // метод клика по типу Фрукт
        driver.findElement(optionTypeFruit).click();
    }

    public void clickOptionTypeVegetable() { // метод клика по типу Овощ
        driver.findElement(optionTypeVegetable).click();
    }

    public void clickCheckboxExotic() { // метод клика по чекбоксу Экзотический
        driver.findElement(checkboxExotic).click();
    }

    public void clickButtonSave() { // метод клика по Сохранить
        driver.findElement(buttonSave).click();
    }

    public boolean hasTableRow(String name, String type, String isExotic) { // метод поиска строки таблицы с атрибутами добавленного овоща/фрукта
        By row = By.xpath(".//tr[td//text() = '" + name + "' and td//text() = '" + type + "' and td//text() = '" + isExotic + "']");

        try {
            driver.findElement(row);
        } catch (NoSuchElementException exception) {
            return false;
        }

        return true;
    }
}

