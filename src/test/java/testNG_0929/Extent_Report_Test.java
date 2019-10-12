package testNG_0929;

import Reusable_classes.Reusable_Library_Loggers;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.IOException;

public class Extent_Report_Test {
    WebDriver driver = null;
    ExtentReports report = null;
    ExtentTest logger = null;


    @BeforeSuite
    public void openBrowser() throws IOException {
        driver = Reusable_Library_Loggers.navigate(driver,"https://.com");
    }//end

    @Test
    public void test(){

    }//end

    @AfterSuite
    public void closeBrowser(){
        //
        driver.quit();
    }//end

}//end of class
