// 1 - Imports
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

// 2 - Classe
public class InternetProject142 {

    // 2.1 - Atributos (objeto do Selenium)
    private WebDriver driver;

    // 2.2 - Funções e Métodos
    @BeforeEach
    public void initialize() {
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/login");
        driver.manage().window().maximize();
    }

    @AfterEach
    public void terminate() {
        driver.quit();
    }

    @Test
    public void loginSuccess() {
        driver.findElement(By.name("username")).sendKeys("tomsmith");
        driver.findElement(By.name("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.cssSelector("button.radius")).click();

        //assertEquals("You logged into a secure area!", driver.findElement(By.id("flash-messages")).getText());
        String successText = driver.findElement(By.id("flash-messages")).getText();
        assertTrue(successText.contains("You logged into a secure area!")); 
    }
    
    // NEGATIVE TESTS
    @Test
    public void invalidUser() {
        driver.findElement(By.name("username")).sendKeys("anotheruser");
        driver.findElement(By.name("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.cssSelector("button.radius")).click();

        String invalidUserText = driver.findElement(By.id("flash-messages")).getText();
        assertTrue(invalidUserText.contains("Your username is invalid!"));
    }
    
    @Test
    public void invalidPassword() {
        driver.findElement(By.name("username")).sendKeys("tomsmith");
        driver.findElement(By.name("password")).sendKeys("anotherpassword");
        driver.findElement(By.cssSelector("button.radius")).click();
        
        String invalidPasswordText = driver.findElement(By.id("flash-messages")).getText();
        assertTrue(invalidPasswordText.contains("Your password is invalid!"));
    }

    @Test
    public void invalidUserPassword() {
        driver.findElement(By.name("username")).sendKeys("anotheruser");
        driver.findElement(By.name("password")).sendKeys("anotherpassword");
        driver.findElement(By.cssSelector("button.radius")).click();
        
        String invalidUserText = driver.findElement(By.id("flash-messages")).getText();
        assertTrue(invalidUserText.contains("Your username is invalid!"));
    }

    @Test
    public void validUserNoPassword() {
        driver.findElement(By.name("username")).sendKeys("tomsmith");
        driver.findElement(By.name("password")).sendKeys("");
        driver.findElement(By.cssSelector("button.radius")).click();
        
        String invalidPasswordText = driver.findElement(By.id("flash-messages")).getText();
        assertTrue(invalidPasswordText.contains("Your password is invalid!"));
    }

    @Test
    public void invalidUserNoPassword() {
            driver.findElement(By.name("username")).sendKeys("anotheruser");
            driver.findElement(By.name("password")).sendKeys("");
            driver.findElement(By.cssSelector("button.radius")).click();
    
            String invalidUserText = driver.findElement(By.id("flash-messages")).getText();
            assertTrue(invalidUserText.contains("Your username is invalid!"));
    }

    @Test
    public void validPasswordNoUser () {
        driver.findElement(By.name("username")).sendKeys("");
        driver.findElement(By.name("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.cssSelector("button.radius")).click();

        String invalidUserText = driver.findElement(By.id("flash-messages")).getText();
        assertTrue(invalidUserText.contains("Your username is invalid!"));
    }

    @Test
    public void invalidPasswordNoUser () {
        driver.findElement(By.name("username")).sendKeys("");
        driver.findElement(By.name("password")).sendKeys("anotherpassword");
        driver.findElement(By.cssSelector("button.radius")).click();
        
        String invalidUserText = driver.findElement(By.id("flash-messages")).getText();
        assertTrue(invalidUserText.contains("Your username is invalid!"));
    }

    @Test
    public void noUserPassword () {
        driver.findElement(By.name("username")).sendKeys("");
        driver.findElement(By.name("password")).sendKeys("");
        driver.findElement(By.cssSelector("button.radius")).click();
        
        String invalidUserText = driver.findElement(By.id("flash-messages")).getText();
        assertTrue(invalidUserText.contains("Your username is invalid!"));
    }
}
