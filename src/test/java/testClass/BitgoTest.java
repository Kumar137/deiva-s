

package testClass;

import Pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.LaunchBrowser;

import java.time.Duration;
import java.util.List;

public class BitgoTest {

    HomePage homePage;
    WebDriver driver;
    LaunchBrowser launchBrowser;
    @BeforeMethod
    public void init()
    {
        driver= new LaunchBrowser().launchBrowser();
        homePage=new HomePage(driver);
    }

    @Test
    public void verifyCount_TC1()
    {
        driver.get("https://blockstream.info/block/000000000000000000076c036ff5119e5a5a74df77abf64203473364509f7732");
        Assert.assertEquals(homePage.getTxnHeader(),"25 of 2875 Transactions");
    }

    @Test
    public void verifyHashList_TC2()
    {
        try {
            driver.manage().window().maximize();
            driver.get("https://blockstream.info/block/000000000000000000076c036ff5119e5a5a74df77abf64203473364509f7732");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='txn font-p2']/a")));

            List<WebElement> transactions = driver.findElements(By.xpath("//div[@class='txn font-p2']/a"));

            // Limit to first 25
            for (int i = 0; i < Math.min(transactions.size(), 25); i++) {
                WebElement txLink = transactions.get(i);
                System.out.println("Link are: ->" + txLink.getText());
                String clickLink = Keys.chord(Keys.CONTROL, Keys.ENTER);
                txLink.sendKeys(clickLink);

                try {
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='vin']")));

                    System.out.println("Inside Try");
                    // Count Inputs
                    List<WebElement> inputs = driver.findElements(By.xpath("//div[@class='vin']"));
                    // Count Outputs
                    List<WebElement> outputs = driver.findElements(By.xpath("//div[@class='vout']"));

                    if (inputs.size() == 1 && outputs.size() == 2) {
                        String hash = driver.findElement(By.xpath("//div[@class='txn font-p2']/a")).getText();
                        System.out.println("Transaction with 1 input & 2 outputs: " + hash);
                    }

                } catch (Exception e) {
                    System.out.println("Failed to process transaction tab: " + e.getMessage());
                }
                driver.navigate().back(); // switch back to main tab
            }

        }
        catch (Exception ex)
        {

        }
    }

    @AfterMethod
    public void tearDown()
    {
        driver.close();
    }
}

