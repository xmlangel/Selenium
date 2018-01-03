package com.xmlangel.selenium.tests;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import com.xmlangel.selenium.DriverBase;
import com.xmlangel.selenium.utils.Log;
import com.xmlangel.selenium.utils.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.xmlangel.selenium.page_objects.AwsHomePage;
import com.xmlangel.selenium.utils.Table;
import com.xmlangel.selenium.utils.Wait;

public class TableDemoIT extends DriverBase {
  private static final Logger logger = LoggerFactory.getLogger(Log.class);

  private static final String awsID = "";
  private static final String awsPassword = "";
  private static final String awsLoginURL = "";
  private static final String awsBucketUrl = "";
  Table getTable = new Table();
  Wait wait = new Wait();

  @Test(enabled = false)
  public void tableTest_01() throws Exception {

    WebDriver driver = getDriver();
    Table getTable = new Table();
    System.out.println("TEST01");

    driver.get("http://elvery.net/demo/responsive-tables/");
    driver.manage().window().maximize();

    int tableNumber;
    tableNumber =
        getTable.table(driver, By.xpath("//table[contains(@class,'table-bordered')]"), "AAC");
    logger.debug("Table Number: " + String.valueOf(tableNumber));
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


  @Test(enabled = true)
  public void tableTest_awsTable() throws Exception {

    WebDriver driver = getDriver();
    System.out.println("awstest");

    driver.get(awsLoginURL);

    AwsHomePage awsHomePage = new AwsHomePage();

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
                                                                                                        // Page
                                                                                                        // 표시


    driver.get(awsBucketUrl);
    wait.waitElementbyXpath(driver, paginationNumbering);

    // AWS Table Name
    By awsTable = By.xpath("//div[@id='c']");
    // 전체 Table 의 갯수를 얻어온다.

    int totalTable = getTable.getTotalcountOfTable(driver, awsTable);
    // aws의 경우 3번째까지가기본, 4번째 라인이 Title 이다. 실제 데이터는 4번째 이후부터이므로 4를 빼준다.
    totalTable = totalTable - 4;
    logger.info(String.valueOf(totalTable));


    // div[@id='c']/div/div/awsui-tabs/div/div/div/span/div/ng-include/div/div[2]/table/tbody/tr[290]/td[5]

    awsHomePage.clickStorageClass();
    awsHomePage.clickStorageClass();

    String sColValue = "Glacier";

    // First loop will find the 'ClOCK TWER HOTEL' in the first column
    for (int i = 1; i <= totalTable; i++) {
      String sValue = null;
      By StorageClass = By.xpath(
          "//div[@id='c']/div/div/awsui-tabs/div/div/div/span/div/ng-include/div/div[2]/table/tbody/tr["
              + i + "]/td[5]");
      sValue = driver.findElement(StorageClass).getText();

      By S3FileName = By.xpath(
          "//div[@id='c']/div/div/awsui-tabs/div/div/div/span/div/ng-include/div/div[2]/table/tbody/tr["
              + i + "]/td[2]");
      logger.info(driver.findElement(S3FileName).getText());

      logger.info(String.valueOf(sValue));

      if (sValue.equalsIgnoreCase(sColValue)) {
        logger.info(String.valueOf(i));

        // div[@id='c']/div/div/awsui-tabs/div/div/div/span/div/ng-include/div/div[2]/table/tbody/tr[3]/td/awsui-checkbox/label/div
        By firstCheckBox = By.xpath(
            "// div[@id='c']/div/div/awsui-tabs/div/div/div/span/div/ng-include/div/div[2]/table/tbody/tr/td/awsui-checkbox/label/div");
        By NthCheckBox = By.xpath(
            "//div[@id='c']/div/div/awsui-tabs/div/div/div/span/div/ng-include/div/div[2]/table/tbody/tr["
                + i + "]/td/awsui-checkbox/label/div");

        driver.findElement(NthCheckBox).click();
      }
    }

    /*
     * Glacie 로 선택된 아이템들을 설정한다. archived Date : 30일 Retrieval option 리스토어 옵션 : "Bulk retrieval
     * (expected time: 5 - 12 hours)
     */

    By S3Morebtn = By.xpath("//button[@type='button']");
    driver.findElement(S3Morebtn).click();

    By S3MoreMenuInitiateRestore = By.linkText("Initiate restore");
    wait.waitElementbyXpath(driver, S3MoreMenuInitiateRestore);

    driver.findElement(S3MoreMenuInitiateRestore).click();

    By InitiateRestoreTitle = By.xpath("//div[@id='confirmation-modal']/div/div/div");
    wait.waitElementbyXpath(driver, InitiateRestoreTitle);

    By periodTextBox = By.id("awsui-textfield-0");
    driver.findElement(periodTextBox).clear();
    driver.findElement(periodTextBox).sendKeys("30");

    driver
        .findElement(
            By.xpath("//div[@id='confirmation-modal']/div/div[3]/dialog-modal-content/div[2]"))
        .click();
    // Retrieval Option ComboBox
    By RetrievalOption = By.xpath("//div[@id='dropdown']/div/div/div");
    driver.findElement(RetrievalOption).click();

    // "Bulk retrieval (expected time: 5 - 12 hours)");
    By RetrievalOptionText = By.xpath("//table[@id='current-displayed-table']/tbody/tr/td/div");
    logger.info(driver.findElement(RetrievalOptionText).getText());
    // 리스토어Summit버튼
    By RestoreSummitBtn = By.xpath("(//button[@type='submit'])[13]");
    driver.findElement(RestoreSummitBtn).click();

  }

}
