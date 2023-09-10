package steps;

import hooks.Hooks;
import io.cucumber.java.ru.И;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class StepsForUITest {

    @И("Открыть страницу Список товаров")
    public void openListProductsPage() {
        Hooks.getObjMainPage().openListProductsPage();
    }
    @И("Нажать на кнопку Добавить")
    public void clickButtonProducts() {
        Hooks.getObjListProductsPage().clickButtonAdd();
    }
    @И("Ввести наименование товара {string}")
    public void addName(String string) {
        Hooks.getObjListProductsPage().addName(string);
    }
    @И("Выбрать тип Овощ")
    public void clickOptionTypeVegetable() {
        Hooks.getObjListProductsPage().clickOptionTypeVegetable();
    }

    @И("Выбрать тип Фрукт")
    public void clickOptionTypeFruit() {
        Hooks.getObjListProductsPage().clickOptionTypeFruit();
    }

    @И("Отметить чекбокс Экзотический")
    public void clickCheckboxExotic() {
        Hooks.getObjListProductsPage().clickCheckboxExotic();
    }
    @И("Нажать на кнопку Сохранить")
    public void clickButtonSave() {
        Hooks.getObjListProductsPage().clickButtonSave();
    }
    @И("Проверить наличие в таблице строки с атрибутами товара: {string} {string} {string}")
    public void checkNewProduct(String name, String type, String isExotic) {
        boolean isVegetableAdded = Hooks.getObjListProductsPage().hasTableRow(name, type, isExotic);

        assertTrue(isVegetableAdded, "Ошибка! Овощ не добавлен.");
    }

}
