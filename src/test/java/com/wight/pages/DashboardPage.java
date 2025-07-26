// src/test/java/com/wight/pages/DashboardPage.java
package com.wight.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {
  @FindBy(name = "weight")           private WebElement weightInput;
  @FindBy(id = "update-weight")      private WebElement updateBtn;
  @FindBy(css = ".weight-history")   private WebElement history;
  @FindBy(id = "logout")             private WebElement logoutBtn;

  public DashboardPage(WebDriver driver) {
    PageFactory.initElements(driver, this);
  }

  public void updateWeight(String kg) {
    weightInput.clear();
    weightInput.sendKeys(kg);
    updateBtn.click();
  }

  public String getLatestWeight() {
    return history.getText().trim();
  }

  public void logout() {
    logoutBtn.click();
  }
}
