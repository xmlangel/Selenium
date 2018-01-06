package com.xmlangel.selenium.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.xmlangel.selenium.utils.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Query {

  private static RemoteWebDriver driver;
  private static String currentBrowserName;

  /**
   * 모든 쿼리에 사용할 정적 드라이버 개체 설정
   *
   * @param driverObject
   */
  public static void initQueryObjects(RemoteWebDriver driverObject) {
    driver = driverObject;
    if (null != driver) {
      currentBrowserName = driver.getCapabilities().getBrowserName();
      Object automationName = driver.getCapabilities().getCapability("automationName");
    }
  }

  private final By defaultLocator;
  private HashMap<String, By> customLocators = new HashMap<>();

  public Query(By defaultLocator) {
    if (null == defaultLocator)
      throw new NullPointerException("Query locator cannot be null!");
    this.defaultLocator = defaultLocator;
  }

  /**
   * 특정 브라우저의 대체 로케이터를 지정
   * Specify a alternate locator for a specific browser.
   * 
   * <p>
   * By개체를 사용하는 모든 작업에서는 현재 드라이버의 'browserName'기능을 검사하며, 
   * 사용자가 여기서 지정한 기능과 일치하는 경우에는 이 로케이터를 대신 사용합니다.
   * Any actions that use a By object will examine the `browserName` capability of the current
   * driver, if it matches what you have specified here this locator will be used instead.
   * <p>
   * 정확성을 보장하기 위해 org.open.selenium.remonute.BrowserType을  통해 사용하는것이 좋다.
   * It is Suggested you pass in a org.openqa.selenium.remote.BrowserType field to ensure accuracy
   * 
   * example:
   * <p>
   * Query query = newQuery(By.id("foo"); 
   * query.addAlternateLocator(BrowserType.GOOGLECHROME,By.id("bar");
   * <p>
   * 
   *
   * @param browser String value matching a browsername capability
   * @param locator A By object used for locating webElements
   */

  public void addAlternateLocator(String browser, By locator) {
    customLocators.put(browser, locator);
  }

  /**
   * locator가 유효한 WebElement를 찾을 수 있으면 WebElement 객체를 반환한다.
   * This will return a WebElement object if the supplied locator could find a valid WebElement.
   *
   * @return WebElement
   */
  public WebElement findWebElement() {
    return driver.findElement(locator());
  }

  /**
   * WebElement 객체의 목록이 반환되며 locator가 화면의 요소와 일치하지 않으면 비어있을 수 있다.
   * This will return a list of WebElement objects, it may be empty if the supplied locator does not
   * match any elements on screen
   *
   * @return List&lt;WebElement>&gt;
   */
  public List<WebElement> findWebElements() {
    return driver.findElements(locator());
  }


  /**
   * 제공된 locator가 WebElement를 찾을 수있는 경우 Select 객체가 반환된다.
   * This will return a Select object if the supplied locator could find a valid WebElement.
   *
   * @return Select
   */
  public Select findSelectElement() {
    return new Select(findWebElement());
  }

  /**
   * 현재 개체와 연결된 locator 반환된다.
   * This will return the locator currently associated with your driver object. 
   *
   * @return By
   */
  public By locator() {
    checkDriverIsSet();
    return customLocators.getOrDefault(currentBrowserName, defaultLocator);
  }

  private void checkDriverIsSet() {
    if (null == driver) {
      throw new IllegalStateException(
          "Driver object has not been set... You must call 'Query.initQueryObjects(driver);'!");
    }
  }
  
  
}
