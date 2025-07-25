package com.wight;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    /**
     * Redirige System.out a un stream temporal antes de cada test.
     */
    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    /**
     * Restaura System.out después de cada test.
     */
    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    /**
     * Verifica que App.main() no lanza excepciones.
     */
    @Test
    public void testAppMainMethodDoesNotThrowException() {
        assertDoesNotThrow(() -> App.main(new String[]{}));
    }

    /**
     * Verifica que App.main() imprime exactamente "Hello World!".
     */
    @Test
    public void testAppMainMethodPrintsHelloWorld() {
        // Ejecutar el método main
        App.main(new String[]{});

        // Capturar salida
        String output = outContent.toString().trim();

        // Depuración (puedes quitar esto luego)
        System.out.println("DEBUG OUTPUT: >" + output + "<");

        // Verificar contenido
        assertTrue(output.contains("Hello World!"), 
            "La salida esperada debe contener 'Hello World!', pero fue: >" + output + "<");
    }
}
