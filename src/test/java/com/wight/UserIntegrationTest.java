package com.wight;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Integration tests for the User class, focusing on the interaction of
 * calculateIdealWeight() and showInformation() with system output and
 * their internal logic based on User attributes.
 */
class UserIntegrationTest {

    // Streams to capture System.out and System.err
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    /**
     * Sets up the test environment before each test method.
     * Redirects System.out and System.err to our custom ByteArrayOutputStreams
     * so we can capture and inspect their output.
     */
    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    /**
     * Restores the original System.out and System.err after each test method.
     * This is crucial to prevent interference with other tests or the test runner's output.
     */
    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    // --- Integration Tests for calculateIdealWeight() ---

    @Test
    @DisplayName("IT-CIW-001: Should calculate ideal weight correctly for a male user (height > 150)")
    void calculateIdealWeight_MaleUser_CorrectCalculation() {
        // Given a male user with specific height
        User user = new User("JohnDoe", 75.0, 170.0, "masculino"); // Height 170cm

        // When calculating ideal weight
        double idealWeight = user.calculateIdealWeight();

        // Expected calculation: (170 - 100) - ((170 - 150) / 4) = 70 - (20 / 4) = 70 - 5 = 65.0
        double expectedIdealWeight = 65.0;

        // Then the ideal weight should be correct and no error should be printed
        assertEquals(expectedIdealWeight, idealWeight, 0.001, "Ideal weight for male should be calculated correctly");
        assertTrue(errContent.toString().isEmpty(), "No error message should be printed to System.err");
    }

    @Test
    @DisplayName("IT-CIW-002: Should calculate ideal weight correctly for a female user (height > 150)")
    void calculateIdealWeight_FemaleUser_CorrectCalculation() {
        // Given a female user with specific height
        User user = new User("JaneDoe", 60.0, 160.0, "femenino"); // Height 160cm

        // When calculating ideal weight
        double idealWeight = user.calculateIdealWeight();

        // Expected calculation: (160 - 100) - ((160 - 150) / 2) = 60 - (10 / 2) = 60 - 5 = 55.0
        double expectedIdealWeight = 55.0;

        // Then the ideal weight should be correct and no error should be printed
        assertEquals(expectedIdealWeight, idealWeight, 0.001, "Ideal weight for female should be calculated correctly");
        assertTrue(errContent.toString().isEmpty(), "No error message should be printed to System.err");
    }

    @Test
    @DisplayName("IT-CIW-003: Should return -1.0 and print error for invalid height (zero or negative)")
    void calculateIdealWeight_InvalidHeight_ReturnsErrorAndPrintsMessage() {
        // Given a user with invalid height (e.g., 0 or negative)
        User user1 = new User("InvalidUser1", 70.0, 0.0, "masculino");
        User user2 = new User("InvalidUser2", 70.0, -10.0, "femenino");

        // When calculating ideal weight for user1
        double idealWeight1 = user1.calculateIdealWeight();

        // Then it should return -1.0 and print the error message
        assertEquals(-1.0, idealWeight1, "Should return -1.0 for zero height");
        assertTrue(errContent.toString().contains("Error: La Altura debe ser un valor positivo"), "Error message for height should be printed");

        // Clear previous error output for the next assertion
        errContent.reset();

        // When calculating ideal weight for user2
        double idealWeight2 = user2.calculateIdealWeight();

        // Then it should return -1.0 and print the error message
        assertEquals(-1.0, idealWeight2, "Should return -1.0 for negative height");
        assertTrue(errContent.toString().contains("Error: La Altura debe ser un valor positivo"), "Error message for height should be printed again");
    }

    // --- Integration Tests for showInformation() ---

    @Test
    @DisplayName("IT-SI-001: Should print correct information for a standard user")
    void showInformation_StandardUser_PrintsCorrectly() {
        // Given a standard user
        User user = new User("Alice", 65.5, 168.0, "femenino");

        // When showing information
        user.showInformation();

        // Then the captured output should match the expected string
        String expectedOutput = "Usuario: Alice, Peso Actual: 65.5 kg, Altura: 168.0 cm, Genero: femenino\n";
        assertEquals(expectedOutput, outContent.toString(), "showInformation should print the correct user details");
        assertTrue(errContent.toString().isEmpty(), "No error message should be printed to System.err");
    }

    @Test
    @DisplayName("IT-SI-002: Should print information correctly for user with zero weight and height")
    void showInformation_ZeroWeightAndHeight_PrintsCorrectly() {
        // Given a user with zero weight and height
        User user = new User("BabyUser", 0.0, 0.0, "masculino");

        // When showing information
        user.showInformation();

        // Then the captured output should reflect zero values
        String expectedOutput = "Usuario: BabyUser, Peso Actual: 0.0 kg, Altura: 0.0 cm, Genero: masculino\n";
        assertEquals(expectedOutput, outContent.toString(), "showInformation should handle zero weight/height correctly");
        assertTrue(errContent.toString().isEmpty(), "No error message should be printed to System.err");
    }

    @Test
    @DisplayName("IT-SI-003: Should print information correctly for user with an unsupported gender")
    void showInformation_UnsupportedGender_PrintsCorrectly() {
        // Given a user with an unsupported gender value
        User user = new User("UnknownGender", 80.0, 180.0, "otro");

        // When showing information (this function doesn't validate gender, just prints it)
        user.showInformation();

        // Then the captured output should include the unsupported gender as is
        String expectedOutput = "Usuario: UnknownGender, Peso Actual: 80.0 kg, Altura: 180.0 cm, Genero: otro\n";
        assertEquals(expectedOutput, outContent.toString(), "showInformation should print unsupported gender as is");
        assertTrue(errContent.toString().isEmpty(), "No error message should be printed to System.err");
    }
}
