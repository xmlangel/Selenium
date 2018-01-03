package com.xmlangel.selenium.utils;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Table {

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
}
