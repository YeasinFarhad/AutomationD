package Actionitem;

import Reusable_classes.Reusable_Library;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class Yahoo {
    //WebDriver driver;
    //or i can use
    WebDriver driver = null;

    @BeforeSuite
    public void openBrowser(){
        //navigate to yohoo.com
        driver = Reusable_Library.navigate(driver,"https://www.yahoo.com");
    }//end of before suit
    @Test
    public void yahoo() throws InterruptedException {
        SoftAssert sa = new SoftAssert();
        sa.assertEquals(driver.getTitle(),"Yahoo");
        Thread.sleep(2500);
        List<WebElement>elementCount = driver.findElements(By.xpath("//*[contains(@class,'Mstart(21px)')]"));
        System.out.println("The count of link is " + elementCount.size());

        //enter a keyword on yahoo search field
        Reusable_Library.userInput(driver,"//*[@name='p']","Nutrition","Search Field");

        //click on search button
        Reusable_Library.click(driver,"//*[@title='Search Web']","Search");

        //timeout for load the page
        Thread.sleep(7000);

        //scroll down
        ((JavascriptExecutor)driver).executeScript("scroll(0,2100)");

        //timeout
        Thread.sleep(3000);

        //capturing the result
        String result = Reusable_Library.captureTextByIndex(driver,"//*[@class='compPagination']",0,"Result");
        String[] resultArray = result.split("Next");
        System.out.println("My Search Number is " + resultArray[1]);

        //timeout
        Thread.sleep(2000);

        //scroll up
        ((JavascriptExecutor)driver).executeScript("scroll(0,-2000)");

        //click on sign in
        Reusable_Library.click(driver,"//*[@id='yucs-login_signIn']","Sign in");

        //timeout
        Thread.sleep(3500);
        boolean elementState = driver.findElement(By.xpath("//*[@type='checkbox']")).isSelected();
        System.out.println("is element selected? "+elementState);

        //enter user name
        Reusable_Library.userInput(driver,"//*[@name='username']","000@yahoo.com","user name");

        //click on next button
        Reusable_Library.click(driver,"//*[@name='signin']","Next");

        //timeout
        Thread.sleep(2000);

        //capture the message
        String Message = driver.findElements(By.xpath("//*[@id='username-error']")).get(0).getText();
        System.out.println(Message);

        //to catch exception from softassert
        sa.assertAll();
    }//end of test annotation

    @AfterSuite
    public void closeBrowser(){

        driver.quit();
    }//end of after suit

}//end of class
