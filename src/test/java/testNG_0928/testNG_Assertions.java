package testNG_0928;

import Reusable_classes.Reusable_Library;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class testNG_Assertions {
    WebDriver driver;

    @BeforeSuite
    public  void openBrowser(){
        //navigate command to open url
        driver = Reusable_Library.navigate(driver,"https://www.google.com");

    }//end of before suite

    @Test
    public void googleSearch(){
        //verify google title matches using Hard Assert
       // Assert.assertEquals(driver.getTitle(),"Google");
        //using soft assert to verify the title
        SoftAssert sa = new SoftAssert();
        sa.assertEquals(driver.getTitle(),"google");
        //enter a keyword on google search field
        Reusable_Library.userInput(driver,"//*[@name='q']","Cars","Search Field");

        //to catch exception from softassert
        sa.assertAll();
    }//end of test annotation

    @AfterSuite
    public void close(){
        //quit the driver
        driver.quit();
    }//end of afer suite
}//end of class
