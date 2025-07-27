package com.wight.tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import com.wight.utils.WebDriverFactory;
import com.wight.pages.SignupPage;
import com.wight.pages.LoginPage;
import com.wight.pages.DashboardPage;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//  ──> Esto levanta un servidor en el puerto 8080 antes de tus tests:
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource(properties = "server.port=8080")
public class HealthTrackFlowTestIT {

  private static WebDriver driver;
  private static final String BASE = "http://localhost:8080";
  private static final String EMAIL = "test@example.com";
  private static final String PWD   = "Secret123!";

  @BeforeAll
  static void init() {
    driver = WebDriverFactory.create();
    // un pequeño implicit wait evita NoSuchElement instantáneo:
    driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(2));
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
