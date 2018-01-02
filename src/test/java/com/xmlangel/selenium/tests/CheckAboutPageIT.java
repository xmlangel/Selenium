package com.xmlangel.selenium.tests;

import com.xmlangel.selenium.DriverBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import com.xmlangel.selenium.utils.Query;


public class CheckAboutPageIT extends DriverBase {

  private static final String Home_URL = "http://127.0.0.1:8080/html/index.html";

  @Test(enabled = false)
  public void goToTheAboutPage() throws Exception {
    WebDriver driver = getDriver();
    driver.get(Home_URL);

    driver.findElement(By.cssSelector(".left-footer >a")).click();

    WebElement element = driver.findElement(By.cssSelector("h1"));

    assertThat(element.getText(), is(equalTo("About us!")));
  }

  @Test(enabled = false)
  public void checkThatAboutPageHasText() throws Exception {
    WebDriver driver = getDriver();
    driver.get(Home_URL);
    driver.findElement(By.cssSelector("footer div:nth-child(1) > a")).click();
    String titleText = driver.findElement(By.cssSelector(".container > div h1")).getText();
    assertThat(titleText, is(equalTo("About us!")));
  }

  @Test(enabled = false)
  public void goToTheAboutPage2() throws Exception {
    WebDriver driver = getDriver();
    driver.get(Home_URL);

    WebElement aboutLink = driver.findElement(By.cssSelector(".left-footer > a"));
    aboutLink.click();

    WebElement aboutHeading = driver.findElement(By.cssSelector("h1"));

    assertThat(aboutHeading.getText(), is(equalTo("About us!")));
  }

  @Test(enabled = false)
  public void goToTheAboutPage3() throws Exception {
    WebDriver driver = getDriver();
    driver.get(Home_URL);
    WebElement aboutLink = driver.findElement(SampleIndexPage.aboutLinkLocator);
    aboutLink.click();
    WebElement aboutHeading = driver.findElement(SampleAboutPage.aboutHeadingLocator);
    assertThat(aboutHeading.getText(), is(equalTo("About us!")));
  }

  @Test(enabled = false)
  public void logInToTheWebsite() throws Exception {
    WebDriver driver = getDriver();
    driver.get(Home_URL);
    WebElement username = driver.findElement(SampleLoginPage.usernameLocator);
    WebElement password = driver.findElement(SampleLoginPage.passwordLocator);
    WebElement submitButton = driver.findElement(SampleLoginPage.loginButtonLocator);
    username.sendKeys("foo");
    password.sendKeys("bar");
    submitButton.click();
    assertThat(driver.getTitle(), is(equalTo("Logged in")));
  }

  @Test(enabled = false)
  public void logInToTheWebsite2() throws Exception {
    WebDriver driver = getDriver();
    driver.get(Home_URL);
    SampleLoginPage.logInWithUsernameAndPassword("foo", "bar", driver);
    assertThat(driver.getTitle(), is(equalTo("Logged in")));
  }
}
