package com.xmlangel.selenium.tests;

import com.xmlangel.selenium.DriverBase;
import com.xmlangel.selenium.page_objects.AwsHomePage;
import com.xmlangel.selenium.utils.Log;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AWSCheckIT extends DriverBase {

  @Test(enabled = false)
  public void awsLoginTest() throws Exception {
    WebDriver driver = getDriver();
    AwsHomePage awsAccountHomePage = new AwsHomePage();

    // AWS console 에접속하여
    // 아이디와 패스워드를 입력하고 로그인한다.

    driver.get("https://aws.amazon.com/console/");
    awsAccountHomePage.enterUserID("ID");
    awsAccountHomePage.enterUserPassword("PASSWORD");
    awsAccountHomePage.submitSigninForm();
  }
}
