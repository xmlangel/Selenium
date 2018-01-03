package com.xmlangel.selenium.page_objects;

import org.openqa.selenium.By;
import com.xmlangel.selenium.utils.Query;
import com.xmlangel.selenium.utils.Wait;

public class AwsHomePage {

  Query awsUserName = new Query(By.id("username"));
  Query awsPassword = new Query(By.id("password"));
  Query awsSigninForm = new Query(By.id("signin_form"));
  // AWS Language
  Query awsLanguage = new Query(By.id("awsc-language"));
  // AWS en
  Query awsLanguageEn = new Query(By.id("en"));
  // AWS 테이블의 마지막라인의 Viewing 을 기다리기위한엘리먼트
  Query awsS3viewing = new Query(By.xpath(
      "//div[@id='c']/div/div/awsui-tabs/div/div/div/span/div/ng-include/div/div[2]/div[2]/div/span"));
  Query awsS3TableStorageClass = new Query(By.linkText("Storage class"));


  public AwsHomePage clickStorageClass() {
    awsS3TableStorageClass.findWebElement().click();

    return this;
  }

  public AwsHomePage checkviewing() {
    awsS3viewing.findWebElement();

    return this;
  }

  public AwsHomePage enterUserID(String userId) {
    awsUserName.findWebElement().clear();
    awsUserName.findWebElement().sendKeys(userId);

    return this;
  }

  public AwsHomePage enterUserPassword(String userPassword) {
    awsPassword.findWebElement().clear();
    awsPassword.findWebElement().sendKeys(userPassword);

    return this;
  }

  public AwsHomePage selectLanguage(String language) {
    awsLanguage.findWebElement().click();
    // language 에 따라서 id를 선택해준다.
    switch (language) {
      case "en":
        awsLanguageEn.findWebElement().click();
        break;
    }

    return this;
  }

  public AwsHomePage submitSigninForm() {
    awsSigninForm.findWebElement().submit();

    return this;
  }

}
