package com.xmlangel.selenium.page_objects;

//
//import com.lazerycode.selenium.util.Query;

import org.openqa.selenium.By;
import com.xmlangel.selenium.utils.Query;

public class AWSAccountHomePage {
  
  Query awsUserName = new Query(By.id("username"));
  Query awsPassword = new Query(By.id("password"));
  Query awsSigninForm = new Query(By.id("signin_form"));
  Query awsLanguage = new Query(By.id("awsc-language"));
  Query awsLanguageEn = new Query(By.id("en"));

  public AWSAccountHomePage enterUserID(String userId) {
    awsUserName.findWebElement().clear();
    awsUserName.findWebElement().sendKeys(userId);

    return this;
  }

  public AWSAccountHomePage enterUserPassword(String userPassword) {
    awsPassword.findWebElement().clear();
    awsPassword.findWebElement().sendKeys(userPassword);

    return this;
  }

  public AWSAccountHomePage selectLanguage(String language) {
    awsLanguage.findWebElement().click();
    //language 에 따라서 id를 선택해준다.
    switch (language) {
      case "en":
        awsLanguageEn.findWebElement().click();
        break;
    }

    return this;
  }

  public AWSAccountHomePage submitSigninForm() {
    awsSigninForm.findWebElement().submit();

    return this;
  }

}
