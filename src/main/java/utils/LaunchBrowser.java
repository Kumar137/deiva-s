package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LaunchBrowser {

    public WebDriver driver;

    public WebDriver launchBrowser()
    {
        System.setProperty("webdriver.chrome.driver","C:\\NoCodeAutomation\\BitGoTest\\chromeServer\\chromedriver.exe");
        driver =new ChromeDriver();
        return driver;
    }

//    public static void main(String[] args) {
//        LaunchBrowser br= new LaunchBrowser();
//        br.launchBrowser().get("https://blockstream.info/block/000000000000000000076c036ff5119e5a5a74df77abf64203473364509f7732");
//
//    }

}
