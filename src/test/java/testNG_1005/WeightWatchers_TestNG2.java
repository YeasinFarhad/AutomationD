package testNG_1005;

import Reusable_classes.Abstract_Class;
import Reusable_classes.Reusable_Library;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class WeightWatchers_TestNG2 extends Abstract_Class {

    @Test
    public void Capture_Current_Opration_Hour() throws IOException {
        //define the driver here
        logger.log(LogStatus.INFO,"Navigating to weight watcher homepage");
       driver.navigate().to("https://www.weightwatchers.com/us");


        //verify the page title
        String title1 = driver.getTitle();
        if (title1.equals("Weight Loss Program, Recipes & Help | Weight Watchers")) {
            logger.log(LogStatus.PASS,"Home page title matches");

        } else {
            logger.log(LogStatus.FAIL,"Home page title doesn't match -- " + title1);
        }

        click(driver, "//*[@class='find-a-meeting']",logger, "Find a Studio");


            //verify the page title
            String title2 = driver.getTitle();
            if (title2.equals("Find a Studio & Meeting Near You |")) {
                System.out.println("Page title matches");
            } else {
                System.out.println("Page title doesn't match -- " + title2);
            }


            //entering zipcode in search field
            userInput(driver, "//*[@name='meetingSearch']", "11218", logger,"Enter location");
            //click on search button
            click(driver, "//*[text()='Submit']",logger, "Search Button");
            /*//capturing the information and store as string
            String Info = driver.findElements(By.xpath("//*[@class='location__container']")).get(0).getText();*/
            //or i can use
            //click on by index number
            clickByIndex(driver,"//*[@class='location__name']]",0,logger,"Locartion Name");

            //capture the first result meeting location
            String meetlocation = captureTextByIndex(driver,"//*[@class='Location']",0,logger,"Meeting location");

            //click on link name
            click(driver, "//*[@ng-if='!linkName']",logger,"Location name link");
            //capturing the information
            String Hour = null;
            //if the operation hour is not present for a zipcode then store the variable inside try catch
            try {
                Thread.sleep(2000);
                Hour = driver.findElements(By.xpath("//*[contains(@class,'currentday']")).get(0).getText();
            }catch (Exception e){
                Hour="Operation Hour is not available for this zip code";
            }
            System.out.println(Hour);



        driver.quit();

    }//end of test


}//end of class
