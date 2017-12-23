package com.xmlangel.selenium.tests;

import com.xmlangel.selenium.DriverBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class CheckAboutPageIT extends DriverBase {

  @Test
  public void goToTheAboutPage() throws Exception {
    WebDriver driver = getDriver();
    driver.get("http://127.0.0.1:8080/html/index.html");
    driver.findElement(By.cssSelector(".left-footer >a")).click();
    WebElement element = driver.findElement(By.cssSelector("h1"));
    assertThat(element.getText(), is(equalTo("About us!")));
  }

  @Test
  public void checkThatAboutPageHasText() throws Exception {
    WebDriver driver = getDriver();
    driver.get("http://127.0.0.1:8080/html/index.html");
    driver.findElement(By.cssSelector("footer div:nth-child(1) > a")).click();
    String titleText = driver.findElement(By.cssSelector(".container > div h1")).getText();
    assertThat(titleText, is(equalTo("About us!")));
  }
}
