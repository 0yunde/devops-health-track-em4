package com.wight.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverFactory {

    /**
     * Crea un ChromeDriver headless, borra drivers viejos, descarga
     * el driver compatible con Chrome instalado y permite conexiones
     * WebSocket desde cualquier origen.
     */
    public static WebDriver create() {
        // 1) Descarga (o reutiliza) el ChromeDriver correcto para tu versión
        WebDriverManager.chromedriver()
                        .clearDriverCache()
                        .setup();

        // 2) Configura ChromeOptions con la flag necesaria
        ChromeOptions options = new ChromeOptions()
            .addArguments("--window-size=1280,800")
            .addArguments("--remote-allow-origins=*");  // <-- aquí está la clave

        // 3) Devuelve la instancia de ChromeDriver
        return new ChromeDriver(options);
    }
}
