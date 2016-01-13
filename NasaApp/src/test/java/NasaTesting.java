import commonAPI.Base;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

/**
 * Created by Rashed on 1/12/2016.
 */
public class NasaTesting extends Base{
        @Test
        public void test() throws InterruptedException{
            System.out.println("The url at the beginning");
            System.out.println(driver.getCurrentUrl());
            driver.findElement(By.cssSelector(".bg-card-canvas")).click();
            System.out.println("The url at the end");
            System.out.println(driver.getCurrentUrl());
        }



}
