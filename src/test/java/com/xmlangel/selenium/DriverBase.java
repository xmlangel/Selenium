package com.xmlangel.selenium;

import com.xmlangel.selenium.config.DriverFactory;
import com.xmlangel.selenium.listeners.ScreenshotListener;
import com.xmlangel.selenium.utils.Log;
import com.xmlangel.selenium.utils.Utils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Listeners(ScreenshotListener.class)
public class DriverBase {
  
  private String sTestCaseName;
  private String sMethodName;
  
  private static List<DriverFactory> webDriverThreadPool =
      Collections.synchronizedList(new ArrayList<DriverFactory>());
  private static ThreadLocal<DriverFactory> driverFactory;

  @BeforeSuite(alwaysRun = true)
  public static void instantiateDriverObject() {
    driverFactory = new ThreadLocal<DriverFactory>() {
      @Override
      protected DriverFactory initialValue() {
        DriverFactory driverFactory = new DriverFactory();
        webDriverThreadPool.add(driverFactory);
        return driverFactory;
      }
    };
  }
  
  public static WebDriver getDriver() throws Exception {
    return driverFactory.get().getDriver();
  }
  @BeforeMethod
  public void beforeMethod() throws Exception {
 // Getting the Test Case name, as it will going to use in so many places
    // The main use is to get the TestCase row from the Test Data Excel sheet
    sTestCaseName = this.toString();
    // From above method we get long test case name including package and class name etc.
    // The below method will refine your test case name, exactly the name use have used
    sTestCaseName = Utils.getTestCaseName(this.toString());
    
    // Start printing the logs and printing the Test Case name
    Log.startTestCase(sTestCaseName);
 
  }
  @AfterMethod(alwaysRun = true)
  
  public void afterMethod() {
        // Printing beautiful logs to end the test case
        Log.endTestCase(sTestCaseName);
        }
  public static void clearCookies() throws Exception {
    getDriver().manage().deleteAllCookies();
  }

  @AfterSuite(alwaysRun = true)
  public static void closeDriverObjects() {
    for (DriverFactory driverFactory : webDriverThreadPool) {
      driverFactory.quitDriver();
    }
  }
  
  
}
