// src/test/java/com/wight/pages/SignupPage.java
package com.wight.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {
  @FindBy(name = "email")            private WebElement email;
  @FindBy(name = "password")         private WebElement password;
  @FindBy(name = "confirmPassword")  private WebElement confirmPassword;
  @FindBy(css = "button[type=submit]") private WebElement submitBtn;

  public SignupPage(WebDriver driver) {
    PageFactory.initElements(driver, this);
  }

  public void signup(String emailTxt, String pwd) {
    email.sendKeys(emailTxt);
    password.sendKeys(pwd);
    confirmPassword.sendKeys(pwd);
    submitBtn.click();
  }
}
