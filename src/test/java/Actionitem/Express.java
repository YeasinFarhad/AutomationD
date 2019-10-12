package Actionitem;

import Reusable_classes.Reusable_Library;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class Express {

    //declare driver variable outside so it can be reused on all annotation methods
    WebDriver driver = null;
    Workbook readableFile = null;
    WritableWorkbook writableFile = null;
    WritableSheet wSheet = null;
    Sheet readableSheet = null;
    int rows = 0;

    @BeforeSuite
    public void openBrowser() throws IOException, BiffException {

        //access the excel readable file path.... choose java.io File for new File()
        readableFile = Workbook.getWorkbook(new File("src\\main\\resources\\Express.xls"));
        //now create a new workbook to replicate readable file
        writableFile = Workbook.createWorkbook(new File("src\\main\\resources\\Express_results.xls"),readableFile);
        //create the writable sheet
        wSheet = writableFile.getSheet(0);
        //access the readable sheet for the rows
        readableSheet = readableFile.getSheet(0);
        //get the existing row count from the readable sheet
        rows = readableSheet.getRows();

        //define the driver here
        driver = Reusable_Library.navigate(driver, "https://www.express.com");
    }//end of before method

    @Test()
    public void testScenario() throws InterruptedException, WriteException {

        Reusable_Library.comparePageTitle(driver,"Men's and Women's Clothing","Express");

        /*//timeout to capture the page title
        Thread.sleep(3000);
        //verifying the page title
        String title = driver.getTitle();
        if (title.equals("Men's and Women's Clothing")) {
            System.out.println("Title matches");
        } else {
            System.out.println("Title doesn't match " + title);
        }*/

        for(int i=1;i<rows;i++) {

            //store the Quantity as a string to use it on scenario
            String Sizetext = readableSheet.getCell(0, i).getContents();

            //store the Size as a string to use it on scenario
            String SizeNum = readableSheet.getCell(1, i).getContents();
            //store the Quantity as a string to use it on scenario
            String Quantity = readableSheet.getCell(2, i).getContents();
            //store the First name as a string to use it on scenario
            String FirstName = readableSheet.getCell(3, i).getContents();
            //store the Last name as a string to use it on scenario
            String LastName = readableSheet.getCell(4, i).getContents();
            //store the Email Data as a string to use it on scenario
            String Email = readableSheet.getCell(5, i).getContents();
            //store the Phone number as a string to use it on scenario
            String PhoneNumber = readableSheet.getCell(6, i).getContents();
            //store the Address as a string to use it on scenario
            String Address = readableSheet.getCell(7, i).getContents();
            //store the Zipcode as a string to use it on scenario
            String ZipCode = readableSheet.getCell(8, i).getContents();
            //store the City as a string to use it on  scenario
            String City = readableSheet.getCell(9, i).getContents();
            //store the Card number as a string to use it on scenario
            String CardNumber = readableSheet.getCell(10, i).getContents();

            //
            driver.navigate().to("https://www.express.com");

            //hovering on women tab
            Reusable_Library.mouseHover(driver, "//*[@href='/womens-clothing']", "Women");

            //hovering on dresses tab
            Reusable_Library.mouseHover(driver, "//*[@href='/womens-clothing/dresses/cat550007']", "Dresses");

            //click on Jumpsuits & Rompers link
            Reusable_Library.click(driver, "//*[contains(text(),'Jumpsuits & Rompers')]", "Jumsuits & Rompers");

            //timeout for load the page
            Thread.sleep(7000);

            //scroll down
            ((JavascriptExecutor)driver).executeScript("scroll(0,400)");

            //
            Thread.sleep(3000);

            //click on the first image
            Reusable_Library.click(driver, "//*[contains(@src,'https://images.express.com/is/image/expressfashion')]", "First Image");


            //select size
            try{
                System.out.println("Click onSize by Text");
                driver.findElement(By.xpath("//*[@value='"+Sizetext+"']")).click();
            }catch(Exception e){
                System.out.println("Click on Size by number");
                driver.findElement(By.xpath("//*[@value='"+SizeNum+"']")).click();
            }

            //add to bag
            Reusable_Library.click(driver, "//*[text()='Add to Bag']", "Add to Bag");

            //click on check out
            Reusable_Library.click(driver, "//*[text()='CHECKOUT']", "CHECKOUT");

            //select quantity
            Reusable_Library.dropDownByText(driver, "//*[@role='listbox']", Quantity, "Quantity");

            //click on checkout
            Reusable_Library.click(driver, "//*[text()='Checkout']", "CHECKOUT");

            //timeout
            Thread.sleep(2000);

            //click as a guest
            Reusable_Library.click(driver, "//*[text()='Continue as Guest']", "As Guest");

            //timeout for loading the page
            Thread.sleep(8000);

            //enter first name
            Reusable_Library.userInput(driver, "//*[@name='firstname']", FirstName, "FirstName");

            //enter lastname
            Reusable_Library.userInput(driver, "//*[@name='lastname']", LastName, "Lastname");

            //enter email address
            Reusable_Library.userInput(driver, "//*[@name='email']", Email, "Enter email");

            //Re enter to confirm
            Reusable_Library.userInput(driver, "//*[@name='confirmEmail']", Email, "Confirmemail");

            //enter phone number
            Reusable_Library.userInput(driver, "//*[@name='phone']", PhoneNumber, "Phonenumber");

            //click continue
            Reusable_Library.click(driver, "//*[text()='Continue']", "Continue");

            //click continue for shiping address
            Reusable_Library.click(driver, "//*[text()='Continue']", "Continue");

            //timeout
            Thread.sleep(4000);


            //enter street address
            Reusable_Library.userInput(driver, "//*[@name='shipping.line1']", Address, "Street Address");

            //enter Zipcode
            Reusable_Library.userInput(driver, "//*[@name='shipping.postalCode']", ZipCode, "Zipcode");

            //enter city
            Reusable_Library.userInput(driver, "//*[@name='shipping.city']", City, "City");

            //select state
            Reusable_Library.dropDownByText(driver, "//*[@name='shipping.state']", "New York", "State");

            //click continue
            Reusable_Library.click(driver, "//*[text()='Continue']", "Continue");

            //timeout
            Thread.sleep(4000);

            //enter credit card number
            Reusable_Library.userInput(driver, "//*[@name='creditCardNumber']", CardNumber, "CardNumber");

            //Place the order
            Reusable_Library.click(driver, "//*[text()='Place Order']", "Place Order");

            //
            Thread.sleep(2000);

            //capture the message
            String Message = null;
            //
            try {
                Message = driver.findElements(By.xpath("//*[@class='_13uVj jwBXt']")).get(0).getText();
            } catch (Exception e) {
                Message = "Credit card not valid";
            }
            System.out.println(Message);

            //create label and add value to excel for Meeting location
            Label Err = new Label(11,i,Message);
            wSheet.addCell(Err);

            //
            driver.manage().deleteAllCookies();
        }


    } //end of test

    @AfterSuite
    public void closeBrowser() throws IOException, WriteException {
        writableFile.write();
        writableFile.close();
        readableFile.close();
        driver.close();
    }
}//end of class
