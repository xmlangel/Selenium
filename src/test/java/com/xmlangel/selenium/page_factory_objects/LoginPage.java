package com.xmlangel.selenium.page_factory_objects;

import com.xmlangel.selenium.config.DriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


public class LoginPage {
  
  @FindBy(how = How.ID, using = "username")
  private WebElement usernameLocator;
  @FindBy(how = How.ID, using = "password")
  private WebElement passwordLocator;
  @FindBy(how = How.ID, using = "login")
  private WebElement loginButtonLocator;

  public void logInWithUsernameAndPassword(String username, String password) {
    usernameLocator.sendKeys(username);
    passwordLocator.sendKeys(password);
    loginButtonLocator.click();
  }
}
