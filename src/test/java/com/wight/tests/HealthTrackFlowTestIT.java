// src/test/java/com/wight/tests/HealthTrackFlowTest.java
package com.wight.tests;

import java.net.URI;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import com.wight.utils.WebDriverFactory;
import com.wight.pages.SignupPage;
import com.wight.pages.LoginPage;
import com.wight.pages.DashboardPage;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HealthTrackFlowTestIT {
  private static WebDriver driver;
  private static final String BASE = "http://localhost:8080";
  private static final String EMAIL = "test@example.com";
  private static final String PWD   = "Secret123!";



  @BeforeAll
  static void init() {
    driver = WebDriverFactory.create();
  }

  @AfterAll
  static void quit() {
    driver.quit();
  }

  @Test @Order(1)
  void signup() {
    driver.get(BASE + "/signup");
    new SignupPage(driver).signup(EMAIL, PWD);
    Assertions.assertTrue(driver.getPageSource().contains("¡Registro exitoso!"));
  }

  @Test @Order(2)
  void login() {
    driver.get(BASE + "/login");
    new LoginPage(driver).login(EMAIL, PWD);
    Assertions.assertTrue(driver.getCurrentUrl().contains("/dashboard"));
  }

  @Test @Order(3)
  void updateWeight() {
    driver.get(BASE + "/dashboard");
    DashboardPage dash = new DashboardPage(driver);
    dash.updateWeight("75");
    Assertions.assertTrue(dash.getLatestWeight().contains("75 kg"));
  }

  @Test @Order(4)
  void logout() {
    new DashboardPage(driver).logout();
    Assertions.assertTrue(driver.getCurrentUrl().contains("/login"));
  }
}
