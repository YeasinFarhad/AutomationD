package Yahoo_Test_Page_Object;

import Page_Object.Yahoo_Base_Class;
import Reusable_classes.Abstract_Class;
import Reusable_classes.Reusable_Library_Loggers_POM;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class TC02_Yahoo_Registration extends Abstract_Class {


    Workbook readFile = null;
    WritableWorkbook writeFile = null;
    Sheet readSheet = null;
    WritableSheet writeSheet = null;
    int rows = 0;

    @Test
    public void Yahoo() throws InterruptedException, IOException, BiffException, WriteException {

        //access readable excel workbook
        readFile = Workbook.getWorkbook(new File("src\\main\\resources\\YREGPAGE.xls"));
        // create duplicate workbook to input new data
        writeFile = Workbook.createWorkbook(new File("src\\main\\resources\\YREGPAGE_results.xls"), readFile);
        // create writable sheet
        writeSheet = writeFile.getSheet(0);
        // access readable sheet to retrieve data
        readSheet = readFile.getSheet(0);
        rows = readSheet.getRows();


        //navigate to yahoo create page
        driver.navigate().to("https://login.yahoo.com/account/create");
        Thread.sleep(1500);
        //verify the title of the page
        Reusable_Library_Loggers_POM.comparePageTitle(driver, "Yahoo", logger, "Registration Page");

        // begin iteration
        for (int i = 1; i < rows; i++) {
            //navigate to yahoo create page
            driver.navigate().to("https://login.yahoo.com/account/create");

            // store excel values in string
            String FirstName = readSheet.getCell(0, i).getContents();
            String LastName = readSheet.getCell(1, i).getContents();
            String Email = readSheet.getCell(2, i).getContents();
            String Password = readSheet.getCell(3, i).getContents();
            String PhoneNum = readSheet.getCell(4, i).getContents();
            String BirthMonth = readSheet.getCell(5, i).getContents();
            String BirthDay = readSheet.getCell(6, i).getContents();
            String BirthYear = readSheet.getCell(7, i).getContents();

            Yahoo_Base_Class.registrationPage().FirstName(FirstName);
            Yahoo_Base_Class.registrationPage().LastName(LastName);
            Yahoo_Base_Class.registrationPage().Email(Email);
            Yahoo_Base_Class.registrationPage().Password(Password);
            Yahoo_Base_Class.registrationPage().PhoneNum(PhoneNum);
            Yahoo_Base_Class.registrationPage().BirthMonth(BirthMonth);
            Yahoo_Base_Class.registrationPage().BirthDay(BirthDay);
            Yahoo_Base_Class.registrationPage().BirthYear(BirthYear);
            Yahoo_Base_Class.registrationPage().ContinueButton();
            String Message = Yahoo_Base_Class.registrationPage().captchaMessage();


            Label label = new Label(8, i, Message);
            writeSheet.addCell(label);

            //delete all cookies
            driver.manage().deleteAllCookies();
        }//end of loop

            writeFile.write();
            writeFile.close();
            readFile.close();

    }//end of test
}//end of class
