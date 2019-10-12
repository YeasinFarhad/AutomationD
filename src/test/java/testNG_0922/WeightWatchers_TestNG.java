package testNG_0922;

import Reusable_classes.Reusable_Library;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WeightWatchers_TestNG {
    //declare driver variable outside so it can be reused on all annotation methods
    WebDriver driver = null;

    @BeforeMethod
    public void openBrowser(){
        //define the driver here
       driver = Reusable_Library.navigate(driver,"https://www.weightwatchers.com/us");
    }

    @Test(priority = 1)
    public void clickonStudio(){

        //verify the page title
        String title1 = driver.getTitle();
        if (title1.equals("Weight Loss Program, Recipes & Help | Weight Watchers")) {
            System.out.println("Home page title matches");
        } else {
            System.out.println("Home page title doesn't match -- " + title1);
        }

        Reusable_Library.click(driver, "//*[@class='find-a-meeting']", "Find a Studio");


    }

    @Test(priority = 2)
    public void testCase(){
            //verify the page title
            String title2 = driver.getTitle();
            if (title2.equals("Find a Studio & Meeting Near You |")) {
                System.out.println("Page title matches");
            } else {
                System.out.println("Page title doesn't match -- " + title2);
            }


            //entering zipcode in search field
            Reusable_Library.userInput(driver, "//*[@name='meetingSearch']", "11218", "Enter location");
            //click on search button
            Reusable_Library.click(driver, "//*[text()='Submit']", "Search Button");
            /*//capturing the information and store as string
            String Info = driver.findElements(By.xpath("//*[@class='location__container']")).get(0).getText();*/
            //or i can use
            //click on by index number
            Reusable_Library.clickByIndex(driver,"//*[@class='location__name']]",0,"Locartion Name");

            //capture the first result meeting location
            String meetlocation = Reusable_Library.captureTextByIndex(driver,"//*[@class='Location']",0,"Meeting location");

            //click on link name
            Reusable_Library.click(driver, "//*[@ng-if='!linkName']","Location name link");
            //capturing the information
            String Hour = null;
            //if the operation hour is not present for a zipcode then store the variable inside try catch
            try {
                Hour = driver.findElements(By.xpath("//*[contains(@class,'currentday']")).get(0).getText();
            }catch (Exception e){
                Hour="Operation Hour is not available for this zip code";
            }
            System.out.println(Hour);

    }

    @AfterMethod
    public void closeBrowser(){

        driver.quit();
    }


}//end of class
