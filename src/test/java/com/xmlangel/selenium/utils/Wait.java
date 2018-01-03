package com.xmlangel.selenium.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Wait {
  /**
   * 특정텍스트가 나타날때까지 기다린다.
   * 
   * @param WebDriver driver
   * @param String Name
   */
  public void waitElementByText(WebDriver driver, String Name) {
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
  public void waitElementbyXpath(WebDriver driver, By byXpath) {
    WebElement myDynamicElement =
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(byXpath));
  }
}
