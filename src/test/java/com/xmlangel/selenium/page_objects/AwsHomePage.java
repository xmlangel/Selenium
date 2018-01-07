package com.xmlangel.selenium.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.xmlangel.selenium.utils.Log;
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
      "//div[@id='c'] /div/div/awsui-tabs/div/div/div/span/div/ng-include/div/div[2]/div[2]/div/span"));
  // AWS Table Storage Title
  Query awsS3TableStorageClass = new Query(By.linkText("Storage class"));

  Query awsS3TableStorageClassOrder = new Query(By.xpath(
      "//div[@id='c']/div/div/awsui-tabs/div/div/div/span/div/ng-include/div/div[2]/table/thead/tr/th[4]/awsui-tooltip/span/span/span"));
  
  // AWS s3 More Button
  Query awsS3MoreBtn = new Query(By.xpath("//button[@type='button']"));

  // AWS s3 Archer Priod Date Text Box
  Query awsS3ArchivedDaysTextBox = new Query(By.id("awsui-textfield-0"));

  // S3 Glacier 리스토어 Summit버튼
  Query awsS3GlacierRestoreSummitBtn = new Query(By.xpath("(//button[@type='submit'])[13]"));
  
  Query awsS3GlacierConfirm = new Query(By.xpath("//div[@id='confirmation-modal']/div/div[3]/dialog-modal-content/div[2]"));

  public AwsHomePage clickGlacierConfirmBtn() {
    awsS3GlacierRestoreSummitBtn.findWebElement().click();

    return this;
  }

  
  public AwsHomePage clickGlacierRestoreSummitBtn() {
    awsS3GlacierRestoreSummitBtn.findWebElement().click();

    return this;
  }


  public AwsHomePage enterArchiverdDays(String Days) {
    awsS3ArchivedDaysTextBox.findWebElement().clear();
    awsS3ArchivedDaysTextBox.findWebElement().sendKeys(Days);

    return this;
  }

  public AwsHomePage selectMoreBtn() {
    awsS3MoreBtn.findWebElement().click();

    return this;
  }

  public AwsHomePage clickStorageClass() {
    awsS3TableStorageClass.findWebElement();
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

  public void selectItemInText(WebDriver driver, String Name, int totalTable) {
    String sColValue = Name;
    String FileName = null;
    String SizeField = null;
    String LastModifiedField = null;
    String StorageClassField = null;

    String S3Table =
        "//div[@id='c']/div/div/awsui-tabs/div/div/div/span/div/ng-include/div/div[2]/table/tbody/tr";

    for (int i = 1; i <= totalTable; i++) {
      By S3BucketFileNameField = By.xpath(S3Table + "[" + i + "]/td[2]");
      By S3BucketLastModifiedField = By.xpath(S3Table + "[" + i + "]/td[3]");
      By S3BucketSizeField = By.xpath(S3Table + "[" + i + "]/td[4]");
      By S3BucketStorageClassField = By.xpath(S3Table + "[" + i + "]/td[5]");

      FileName = getTextOfField(driver, S3BucketFileNameField);
      LastModifiedField = getTextOfField(driver, S3BucketLastModifiedField);
      SizeField = getTextOfField(driver, S3BucketSizeField);
      StorageClassField = getTextOfField(driver, S3BucketStorageClassField);

      if (StorageClassField.equalsIgnoreCase(sColValue)) {

        By firstCheckBox = By.xpath(S3Table + "/td/awsui-checkbox/label/div");

        By NthCheckBox = By.xpath(S3Table + "[" + i + "]/td/awsui-checkbox/label/div");

        driver.findElement(NthCheckBox).click();
      }
    }
  }

  private String getTextOfField(WebDriver driver, By locator) {
    String sValue;
    sValue = driver.findElement(locator).getText();
    Log.debug(sValue);
    return sValue;
  }

}
