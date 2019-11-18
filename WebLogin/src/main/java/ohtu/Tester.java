package ohtu;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Tester {

    public static void main(String[] args) {
        WebDriver driver = new FirefoxDriver();
        final String URL = "http://localhost:4567";
        driver.get(URL);

        /*
         * WebElement element = driver.findElement(By.linkText("login"));
         * element.click();
         * 
         * sleep(2);
         * 
         * element = driver.findElement(By.name("username")); element.sendKeys("pekka");
         * element = driver.findElement(By.name("password")); element.sendKeys("akkep");
         * element = driver.findElement(By.name("login"));
         */
        // epäonnistunut kirjautuminen: oikea käyttäjätunnus, väärä salasana

        /*
         * element = driver.findElement(By.name("username")); element.sendKeys("pekka");
         * element = driver.findElement(By.name("password"));
         * element.sendKeys("akkep11"); element = driver.findElement(By.name("login"));
         */

        // uuden käyttäjätunnuksen luominen
        /*
         * WebElement element = driver.findElement(By.linkText("register new user"));
         * element.click();
         * 
         * sleep(2); Random r = new Random();
         * 
         * element = driver.findElement(By.name("username")); element.sendKeys("arto" +
         * r.nextInt(100000)); element = driver.findElement(By.name("password"));
         * element.sendKeys("salasana1234"); element =
         * driver.findElement(By.name("passwordConfirmation"));
         * element.sendKeys("salasana1234"); sleep(2); element.submit();
         */
        /*
         * element = driver.findElement(By.name("username")); element.sendKeys("pekka");
         * element = driver.findElement(By.name("password")); element.sendKeys("akkep");
         * element = driver.findElement(By.name("login"));
         */
        // uuden käyttäjätunnuksen luomisen jälkeen tapahtuva ulkoskirjautuminen
        // sovelluksesta

        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();

        sleep(2);
        Random r = new Random();

        element = driver.findElement(By.name("username"));
        element.sendKeys("arto" + r.nextInt(100000));
        element = driver.findElement(By.name("password"));
        element.sendKeys("salasana1234");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("salasana1234");
        sleep(2);
        element.submit();
        sleep(3);
        element = driver.findElement(By.linkText("continue to application mainpage"));
        sleep(3);
        element.click();
        sleep(2);
        element = driver.findElement(By.linkText("logout"));
        sleep(3);
        element.click();

        sleep(3);

        driver.quit();

    }

    private static void clickLinkWithText(String text, WebDriver driver) {
        int trials = 0;
        while (trials++ < 5) {
            try {
                WebElement element = driver.findElement(By.linkText(text));
                element.click();
                break;
            } catch (Exception e) {
                System.out.println("Ei toimi");
                System.out.println(e.getStackTrace());
            }
        }
    }

    private static void sleep(int n) {
        try {
            Thread.sleep(n * 1000);
        } catch (Exception e) {
        }
    }
}
