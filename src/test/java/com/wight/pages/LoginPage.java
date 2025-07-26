// src/test/java/com/wight/pages/LoginPage.java
package com.wight.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
  @FindBy(name = "email")    private WebElement email;
  @FindBy(name = "password") private WebElement password;
  @FindBy(css = "button[type=submit]") private WebElement submitBtn;

  public LoginPage(WebDriver driver) {
    PageFactory.initElements(driver, this);
  }

  public void login(String emailTxt, String pwd) {
    email.sendKeys(emailTxt);
    password.sendKeys(pwd);
    submitBtn.click();
  }
}
