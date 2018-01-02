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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.xmlangel.selenium.page_objects.AWSAccountHomePage;

public class TableDemoIT extends DriverBase {
  private static final Logger logger = LoggerFactory.getLogger(Log.class);
  private static final String awsID="";
  private static final String awsPassword="";
  private static final String awsLoginURL="";
  private static final String awsBucketUrl="";
  
  @Test(enabled = false)
  public void tableTest_01() throws Exception {

    WebDriver driver = getDriver();
    driver.get("http://elvery.net/demo/responsive-tables/");
    driver.manage().window().maximize();

    int tableNumber;
    tableNumber = table(driver, By.xpath("//table[contains(@class,'table-bordered')]"), "AAC");
    logger.debug("Table Number: " + String.valueOf(tableNumber));
  }

  @Test(enabled = true)
  public void tableTest_02() throws Exception {

    WebDriver driver = getDriver();
    driver.get(awsLoginURL);

    AWSAccountHomePage awsAccountHomePage = new AWSAccountHomePage();

    // AWS console 에접속하여
    // 아이디와 패스워드를 입력하고 로그인한다.

    awsAccountHomePage.enterUserID(awsID);
    awsAccountHomePage.enterUserPassword(awsPassword);
    awsAccountHomePage.submitSigninForm();
    awsAccountHomePage.selectLanguage("en");

   //AWS 테이블의 마지막라인의 Viewing 을 기다리기위한엘리먼트 
    By endofviewing = By.xpath(
        "//div[@id='c']/div/div/awsui-tabs/div/div/div/span/div/ng-include/div/div[2]/div[2]/div/span");

    
    driver.get(awsBucketUrl);
    waitElementbyXpath(driver, endofviewing);
    
    //AWS Table Name
    By awsTable = By.xpath("//div[@id='c']");
    
    // 전체 Table 의 갯수를 얻어온다.
    
    int totalTable = getTotalcountOfTable(driver, awsTable);

    // aws의 경우 3번째까지가기본, 4번째 라인이 Title 이다. 실제 데이터는 4번째 이후부터이므로 4를 빼준다.
    totalTable = totalTable - 4;
    logger.info(String.valueOf(totalTable));


    // tableNumber = table(driver, By.xpath("//div[contains(@class,'awsui-tabs-panel')]"),
    // "Glacier");
    // logger.debug("Table Number: " + String.valueOf(tableNumber));
  }

  /**
   * AWS 테이블의 특정텍스트가 있는 Check int
   * 
   * @param WebDriver driver
   * @param By locator
   * @param String Name
   */
  public int getTotalcountOfTable(WebDriver driver, By locator) {
    int tableRowNumber = 0;
    WebElement table = driver.findElement(locator);
    List<WebElement> rowsList = table.findElements(By.tagName("tr"));
    List<WebElement> columnsList = null;
    for (WebElement row : rowsList) {
      columnsList = row.findElements(By.tagName("td"));

      for (WebElement column : columnsList) {
        tableRowNumber = rowsList.indexOf(row);
      }

    }
    return tableRowNumber;
  }

  /**
   * 특정텍스트가 나타날때까지 기다린다.
   * 
   * @param WebDriver driver
   * @param String Name
   */
  private void waitElementByText(WebDriver driver, String Name) {
    By byXpathText = By.xpath("//*[contains(text()," + Name + ")]");
    // 10초동안 해당 Xpath가 나타날때까지 기다린다.
    WebElement myDynamicElement = (new WebDriverWait(driver, 10))
        .until(ExpectedConditions.presenceOfElementLocated(byXpathText));
  }

  /**
   * 특정 Xpath가 나타날때까지 기다린다.
   * 
   * @param WebDriver driver
   * @param By Xpath
   */
  private void waitElementbyXpath(WebDriver driver, By byXpath) {
    WebElement myDynamicElement =
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(byXpath));
  }



  /**
   * 테이블의 특정텍스트가 있는 Check int
   * 
   * @param WebDriver driver
   * @param By locator
   * @param String Name
   */
  public int table(WebDriver driver, By locator, String Name) {
    int tableRowNumber = 0;
    WebElement table = driver.findElement(locator);
    List<WebElement> rowsList = table.findElements(By.tagName("tr"));
    List<WebElement> columnsList = null;
    for (WebElement row : rowsList) {
      columnsList = row.findElements(By.tagName("td"));

      for (WebElement column : columnsList) {

        boolean istext = checkTableTextExist(rowsList, row, column, Name);
        if (istext) {
          // logger.info(column.getText() + "\t");
          tableRowNumber = rowsList.indexOf(row);
        }
      }
    }
    return tableRowNumber;
  }

  /**
   * 테이블의 특정텍스트가 있는 Check boolean
   * 
   * @param List<WebElement> rowsList
   * @param WebElement row
   * @param WebElement column
   * @param String Name
   */
  public boolean checkTableTextExist(List<WebElement> rowsList, WebElement row, WebElement column,
      String Name) {

    String sCellValue = column.getText();
    boolean isText = sCellValue.equalsIgnoreCase(Name);
    // logger.debug(String.valueOf(isText));

    if (sCellValue.equalsIgnoreCase(Name)) {
      // logger.debug(String.valueOf(row));
      // logger.debug(Name + " exist in " + rowsList.indexOf(row) + " Row");
    }
    return isText;

  }

}
