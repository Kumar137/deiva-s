package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage {

    WebDriver driver;

    String vinInput="//div[@class='ins-and-outs']//div[@class='vin']";

    public HomePage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//div[@class='transactions']/h3")
    public WebElement wbTxnHeader;

    @FindBys({@FindBy(xpath = "//div[@class='txn font-p2']/a")})
    List<WebElement> lsthash;

    public String getTxnHeader()
    {
        return wbTxnHeader.getText();
    }

    public List<WebElement> getHashList()
    {
        return lsthash;
    }

}
