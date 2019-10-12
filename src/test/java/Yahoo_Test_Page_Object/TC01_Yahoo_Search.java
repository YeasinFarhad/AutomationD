package Yahoo_Test_Page_Object;


import Page_Object.Yahoo_Base_Class;
import Reusable_classes.Abstract_Class;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC01_Yahoo_Search extends Abstract_Class {

    @Test
    public static void Yahoo() throws IOException, InterruptedException {

        driver.navigate().to("https://www.yahoo.com");

        //
        Yahoo_Base_Class.yahoo_homepage().countTabLink(8);

        //
        Yahoo_Base_Class.yahoo_homepage().searchKey("Cars");
        Yahoo_Base_Class.yahoo_homepage().clickOnSearchIcon();

        Yahoo_Base_Class.yahoo_searchResultPage().scrollDown("3100");
        Yahoo_Base_Class.yahoo_searchResultPage().searchNumber();

        Thread.sleep(1111);

        driver.quit();

    }

}
