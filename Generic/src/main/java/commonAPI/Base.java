package commonAPI;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by Rashed on 1/12/2016.
 */
public class Base {

    public WebDriver driver = null;

    @Parameters({"useCloudEnv","userName","key","os","browser","browserVersion","url"})
    @BeforeMethod
    public void setUp(@Optional("false")Boolean useCloudEnv, @Optional("rahmanww") String userName, @Optional("gdfsd")
    String key, @Optional("Windows 8")String OS, @Optional("firefox") String browser,
                      @Optional("40.0") String browserVersion,
                      @Optional("http://www.cnn.com") String url)throws IOException {

        if(useCloudEnv==true){
            //run on cloud
            getCloudDriver(userName,key,OS,browser,browserVersion);
            System.out.println("Tests is running on Saucelabs, please wait for result");

        }else{
            //run on local
            getLocalDriver(OS,browser,browserVersion);
        }
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.navigate().to(url);
        driver.manage().window().maximize();
    }

    public WebDriver getCloudDriver(String userName,String key,
                                    String OS,String browser,String browserVersion)throws IOException{
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("platform", OS);
        cap.setBrowserName(browser);
        cap.setCapability("version",browserVersion);
        this.driver = new RemoteWebDriver(new URL("http://"+userName+":"+key+"@ondemand.saucelabs.com:80/wd/hub"), cap);

        return driver;
    }
    public WebDriver getLocalDriver(String OS, String browser, String browserVersion){
        if(browser.equalsIgnoreCase("firefox")){
            driver = new FirefoxDriver();
        }else if(browser.equalsIgnoreCase("chrome")){
            if(OS.equalsIgnoreCase("mac")){
                System.setProperty("webdriver.chrome.driver","Generic/selenium-browser-driver/chromedriver");driver = new ChromeDriver();
            }else if(OS.equalsIgnoreCase("windows")){
                System.setProperty("webdriver.chrome.driver","Generic/selenium-browser-driver/chromedriver.exe");
                driver = new ChromeDriver();
            }
        }else if(browser.equalsIgnoreCase("ie")){
            System.setProperty("webdriver.ie.driver","Generic/selenium-browser-driver/IEDriverServer.exe");
            driver = new ChromeDriver();
            driver = new InternetExplorerDriver();
        }else if(browser.equalsIgnoreCase("safari")){
            driver = new SafariDriver();
        }

        return driver;
    }

    @AfterMethod
    public void cleanUp(){driver.close();}



}
