package com.xmlangel.selenium.tests;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import com.xmlangel.selenium.DriverBase;
import com.xmlangel.selenium.utils.Log;
import com.xmlangel.selenium.utils.Utils;
import com.xmlangel.selenium.page_objects.AwsHomePage;
import com.xmlangel.selenium.utils.Table;
import org.openqa.selenium.support.ui.Select;

public class AwsHomePageIT extends DriverBase {
  private static final String awsID = "";
  private static final String awsPassword = "";
  private static final String awsLoginURL = "";
  private static final String awsBucketUrl = "";
  private String name;


  @Test(enabled = false)
  public void tableTest_01() throws Exception {
    name = new Object() {}.getClass().getEnclosingMethod().getName();
    Log.info(name);

    WebDriver driver = getDriver();
    Table getTable = new Table();


    driver.get("http://elvery.net/demo/responsive-tables/");
    driver.manage().window().maximize();

    int tableNumber;
    tableNumber =
        getTable.table(driver, By.xpath("//table[contains(@class,'table-bordered')]"), "AAC");
    Log.debug("Table Number: " + String.valueOf(tableNumber));
  }

  @Test(enabled = false)
  public void tableTestÏ_table3() throws Exception {
    WebDriver driver = getDriver();
    System.out.println("TEST03");

    driver.get("http://toolsqa.wpengine.com/automation-practice-table");
    driver.manage().window().maximize();

    // Here we are storing the value from the cell in to the string variable
    String sCellValue =
        driver.findElement(By.xpath(".//*[@id='content']/table/tbody/tr[1]/td[2]")).getText();
    System.out.println(sCellValue);

    // Here we are clicking on the link of first row and the last column
    driver.findElement(By.xpath(".//*[@id='content']/table/tbody/tr[1]/td[6]/a")).click();
    System.out.println("Link has been clicked otherwise an exception would have thrown");
  }

  @Test(enabled = false)
  public void ComboBoxText() throws Exception {
    WebDriver driver = getDriver();
    driver.get("http://toolsqa.wpengine.com/automation-practice-form");
    // Step 3 : Select the deselected Radio button (female) for category Sex (Use IsSelected method)
    // Storing all the elements under category 'Sex' in the list of WebLements

    List<WebElement> rdBtn_Sex = driver.findElements(By.name("sex"));
    System.out.println(rdBtn_Sex.get(0));
    System.out.println(rdBtn_Sex.get(1));

    // Create a boolean variable which will hold the value (True/False)
    boolean bValue = false;

    // This statement will return True, in case of first Radio button is selected
    bValue = rdBtn_Sex.get(0).isSelected();
    System.out.println(bValue);

    // This will check that if the bValue is True means if the first radio button is selected
    if (bValue == true) {
      // This will select Second radio button, if the first radio button is selected by default
      rdBtn_Sex.get(1).click();
    } else {
      // If the first radio button is not selected by default, the first will be selected
      rdBtn_Sex.get(0).click();
    }

    // Step 4: Select the Third radio button for category 'Years of Exp' (Use Id attribute to select
    // Radio button)
    WebElement rdBtn_Exp = driver.findElement(By.id("exp-2"));
    rdBtn_Exp.click();

    // STep 5: Check the Check Box 'Automation Tester' for category 'Profession'( Use Value
    // attribute to match the selection)
    // Find the Check Box or radio button element by Name
    List<WebElement> chkBx_Profession = driver.findElements(By.name("profession"));

    // This will tell you the number of Check Boxes are present
    int iSize = chkBx_Profession.size();

    System.out.println(iSize);

    // Start the loop from first Check Box to last Check Boxe
    for (int i = 0; i < iSize; i++) {
      // Store the Check Box name to the string variable, using 'Value' attribute
      String sValue = chkBx_Profession.get(i).getAttribute("value");
      System.out.println(sValue);
      // Select the Check Box it the value of the Check Box is same what you are looking for
      if (sValue.equalsIgnoreCase("Automation Tester")) {
        chkBx_Profession.get(i).click();
        // This will take the execution out of for loop
        break;
      }
    }
    // Step 6: 'Automation Tool' 에있는 'Selenium IDE' Check
    WebElement oCheckBox = driver.findElement(By.cssSelector("input[value='Selenium IDE']"));
    oCheckBox.click();
  }

  @Test(enabled = false)
  public void MultiSelectCommands() throws Exception {
    name = new Object() {}.getClass().getEnclosingMethod().getName();
    Log.info(name);


    WebDriver driver = getDriver();
    // Put an Implicit wait
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    // Launch the URL
    driver.get("http://toolsqa.wpengine.com/automation-practice-form");

    // Step 3: Select 'Selenium Commands' Multiple select box ( Use Name locator to identify the
    // element )
    Select oSelect = new Select(driver.findElement(By.name("selenium_commands")));

    // Step 4: Select option 'Browser Commands' and then deselect it (Use selectByIndex and
    // deselectByIndex)
    oSelect.selectByIndex(0);
    Thread.sleep(2000);
    oSelect.deselectByIndex(0);

    // Step 5: Select option 'Navigation Commands' and then deselect it (Use selectByVisibleText and
    // deselectByVisibleText)
    oSelect.selectByVisibleText("Navigation Commands");
    Thread.sleep(2000);
    oSelect.deselectByVisibleText("Navigation Commands");

    // Step 6: Print and select all the options for the selected Multiple selection list.
    List<WebElement> oSize = oSelect.getOptions();
    int iListSize = oSize.size();

    // Setting up the loop to print all the options
    for (int i = 0; i < iListSize; i++) {
      // Storing the value of the option
      String sValue = oSelect.getOptions().get(i).getText();

      // Printing the stored value
      System.out.println(sValue);

      // Selecting all the elements one by one
      oSelect.selectByIndex(i);
      Thread.sleep(2000);
    }

    // Step 7: Deselect all
    oSelect.deselectAll();
  }


  @Test(enabled = true)
  public void tableTest_awsTable() throws Exception {

    WebDriver driver = getDriver();

    name = new Object() {}.getClass().getEnclosingMethod().getName();
    Log.info(name);

    driver.get(awsLoginURL);

    AwsHomePage awsHomePage = new AwsHomePage();
    Table getTable = new Table();

    // AWS console 에접속하여
    // 아이디와 패스워드를 입력하고 로그인한다.

    awsHomePage.enterUserID(awsID);
    awsHomePage.enterUserPassword(awsPassword);
    awsHomePage.submitSigninForm();
    awsHomePage.selectLanguage("en");

    // AWS 테이블의 마지막라인의 Viewing 을 기다리기위한엘리먼트
    By paginationNumbering = By.id("paginationNumbering");// PabinationNumbering
    By prePage = By.xpath("//button[@type='submit'])[6]");// < 버튼.
    By nextPage = By.xpath("//button[@type='submit'])[7]");// > 버튼.
    By endofviewing = By.xpath(
        "//div[@id='c']/div/div/awsui-tabs/div/div/div/span/div/ng-include/div/div[2]/div[2]/div/span");// 맨하단의
    // AWS Table Name
    By awsTable = By.xpath("//div[@id='c']");


    driver.get(awsBucketUrl);

    Utils.waitForElementByXpath(driver, paginationNumbering);

    // 전체 Table 의 갯수를 얻어온다.
    int totalTable = getTable.getTotalcountOfTable(driver, awsTable);
    Log.debug(String.valueOf(totalTable));
    // aws의 경우 3번째까지가기본, 4번째 라인이 Title 이다. 실제 데이터는 4번째 이후부터이므로 4를 빼준다.
    totalTable = totalTable - 4;
    Log.info("Total Table :" + String.valueOf(totalTable));
    awsHomePage.clickStorageClass();
    awsHomePage.clickStorageClass();

    awsHomePage.selectItemInText(driver, "Glacier", totalTable);

    /*
     * Glacie 로 선택된 아이템들을 설정한다. archived Date : 30일 Retrieval option 리스토어 옵션 : "Bulk retrieval
     * (expected time: 5 - 12 hours)
     */
    awsHomePage.selectMoreBtn();

    // S3MoreMenu
    By S3MoreMenuInitiateRestore = By.linkText("Initiate restore");

    Utils.waitForElementByXpath(driver, S3MoreMenuInitiateRestore);
    driver.findElement(S3MoreMenuInitiateRestore).click();

    // restoreTitle
    By InitiateRestoreTitle = By.xpath("//div[@id='confirmation-modal']/div/div/div");
    Utils.waitForElementByXpath(driver, InitiateRestoreTitle);

    // archerPiod
    awsHomePage.enterArchiverdDays("30");
    //
    awsHomePage.clickGlacierConfirmBtn();

    // Retrieval Option ComboBox
    By RetrievalOption = By.xpath("//div[@id='dropdown']/div/div/div");
    driver.findElement(RetrievalOption).click();

    // "Bulk retrieval (expected time: 5 - 12 hours)");
    By RetrievalOptionText = By.xpath("//table[@id='current-displayed-table']/tbody/tr/td/div");
    Log.info(driver.findElement(RetrievalOptionText).getText());

    // 리스토어Summit버튼
    awsHomePage.clickGlacierRestoreSummitBtn();
  }

}
