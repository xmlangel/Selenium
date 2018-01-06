package com.xmlangel.selenium.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utils {
  public static String getTestCaseName(String sTestCase)throws Exception{
    String value = sTestCase;
    try{
        int posi = value.indexOf("@");
        value = value.substring(0, posi);
        posi = value.lastIndexOf(".");  
        value = value.substring(posi + 1);
        return value;
            }catch (Exception e){
        Log.error("Class Utils | Method getTestCaseName | Exception desc : "+e.getMessage());
        throw (e);
                }
        }
  public static void waitForElement(WebDriver driver, WebElement element) {
    WebDriverWait wait = new WebDriverWait(driver, 10);
    wait.until(ExpectedConditions.elementToBeClickable(element));
  }
  /**
   * 특정텍스트가 나타날때까지 기다린다.
   * 
   * @param WebDriver driver
   * @param String Name
   */
  public static void waitForElementByText(WebDriver driver, String Name) {
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
  public static void waitForElementByXpath(WebDriver driver, By Xpath) {
    WebElement myDynamicElement =
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(Xpath));
  }
}
