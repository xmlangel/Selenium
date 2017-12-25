package com.xmlangel.selenium.tests;

import com.xmlangel.selenium.DriverBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import org.testng.annotations.Test;

public class SampleLoginPage extends DriverBase {
  public static By usernameLocator = By.id("username");
  public static By passwordLocator = By.id("password");
  public static By loginButtonLocator = By.id("login");

  public static void logInWithUsernameAndPassword(String username, String password,
      WebDriver driver) {
    driver.findElement(SampleLoginPage.usernameLocator).sendKeys(username);
    driver.findElement(SampleLoginPage.passwordLocator).sendKeys(password);
    driver.findElement(SampleLoginPage.loginButtonLocator).click();
  }
}
