/*ТЕСТ ПРОВЕРЯЕТ ТЕГИ МЕТА И ЗАГОЛОВОК НА ГЛАВНОЙ, ПЕРЕХОДИТ ВО ВКЛАДКУ ПРИЛОЖЕНИЯ, ПЕРЕХОДИТ В ВИТАМИН, ПРОВЕРЯЕТ
ТЕГИ МЕТА И ЗАГОЛОВОК СТРАНИЦЫ ВИТАМИН. ДВЕ ЛОКАЛИЗАЦИИ РУ и EN.*/

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.AssertJUnit.assertEquals;

public class MartspecTest {

    @BeforeAll
    public static void setUp() {
        // Настройка Selenide
        Configuration.baseUrl = "https://martspec.com";
        Configuration.holdBrowserOpen = false;

    }

    @Test
    public void testMartSpecPageRu() {
        // Открытие страницы
        Selenide.open("https://martspec.com/ru");

        // Проверка тега На Главной title
        $("title").shouldHave(attribute("text", "Martspec — трекер твоего здоровья"));

        // Проверка тега на Главной meta
        String expectedMetaContent = "Приложения для тех, кто заботится о своём здоровье. Удобный и простой способ следить за параметрами тела и своим эмоциональным состоянием.";
        String actualMetaContent = $("meta[name='description']").getAttribute("content");

        // Замена неразрывного пробела на обычный пробел
        expectedMetaContent = StringUtils.replace(expectedMetaContent, " ", " ");
        actualMetaContent = StringUtils.replace(actualMetaContent, " ", " ");

        // Сравнение значений с игнорированием пробелов и переносов строк
        assertEqualsIgnoreWhitespace(expectedMetaContent, actualMetaContent);

        // Явное ожидание перед поиском элемента "Приложения"
        // waitUntil($(".nav-link.show#navbarDropdown[data-bs-toggle='dropdown'][aria-expanded='true']"), 5000).click();

        // Клик на "Приложения"
        $(By.linkText("Приложения")).click();

        // Клик на "Витамин" в выпадающем списке
        $(".nav-link.dropdown-item[href='https://martspec.com/ru/vitamin']").click();

        // Проверка тега title
        $("title").shouldHave(attribute("text", "Витамин – трекер сбалансированного и здорового питания"));

        // Проверка тега meta
        $("meta[name='description']").shouldHave(attribute("content", "Отслеживайте витамины, минералы и другие добавки на Apple Watch, iPhone, iPad или Mac с помощью нашего удобного приложения. Бесплатная синхронизация данных с Apple Health."));
    }


    @Test
    public void testMartSpecPageEn() {
        // Открытие страницы в английской локализации
        Selenide.open("https://martspec.com/en");

        // Проверка тега На Главной title
        $("title").shouldHave(attribute("text", "Simplify health & wellness" +
                " tracking"));

        // Проверка тега на Главной meta
        String expectedMetaContent = "Control your health and happiness with beautifully simple Martspec apps. " +
                "Track your physical and mental wellness with ease as your well-being improves.";
        String actualMetaContent = $("meta[name='description']").getAttribute("content");

        // Замена неразрывного пробела на обычный пробел
        expectedMetaContent = StringUtils.replace(expectedMetaContent, " ", " ");
        actualMetaContent = StringUtils.replace(actualMetaContent, " ", " ");

        // Сравнение значений с игнорированием пробелов и переносов строк
        assertEqualsIgnoreWhitespace(expectedMetaContent, actualMetaContent);

        // Клик на "Applications" (английская локализация кнопки "Приложения")
        $(By.linkText("Applications")).click();

        // Клик на "Vitamin Book" (английская локализация кнопки "Витамин")
        $(".nav-link.dropdown-item[href='https://martspec.com/vitamin']").click();

        // Проверка тега title
        $("title").shouldHave(attribute("text", "Vitamin - Track all your" +
                " nutrition"));

        // Проверка тега meta
        $("meta[name='description']").shouldHave(attribute("content", "Easily track your vitamins, minerals, pills, and supplements on your Apple Watch, iPhone, iPad, or Mac with our user-friendly app. Free data sync with Apple Health."));
    }

    /**
     * Сравнивает две строки, игнорируя пробелы и переносы строк.
     *
     * @param expected Ожидаемая строка
     * @param actual   Фактическая строка
     */
    public void assertEqualsIgnoreWhitespace(String expected, String actual) {
        assertEquals(StringUtils.deleteWhitespace(expected), StringUtils.deleteWhitespace(actual));
    }
}




