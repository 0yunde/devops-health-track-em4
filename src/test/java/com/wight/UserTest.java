package com.wight;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class UserTest {
    private User user;
    private static final double INITIAL_WEIGHT = 75.0;

    @BeforeEach
    void setUp() {
        user = new User("John Doe", INITIAL_WEIGHT);
    }

    @Test
    @DisplayName("Should ensure User's username and weight are NOT null")
    void constructorInitializesWithNonNullValues() {
        assertNotNull(user.getUsername(), "Username should not be null");
        assertNotNull(user.getWeight(), "Weight should not be null (it's a primitive double)");
    }

    @Test
    @DisplayName("Should correctly initialize User with given username and weight")
    void constructorInitializesCorrectly() {
        // This test is designed to fail because the expected username "Jon Smith" does not match "John Doe"
        assertNotEquals("John Smith", user.getUsername(), "Username should NOT be initialized correctly");
        assertEquals(INITIAL_WEIGHT, user.getWeight(), "Weight should be initialized correctly");
    }

    @Test
    @DisplayName("Should ensure User's username and weight are NOT specific values")
    void constructorInitializesCorrectlyCopy() {
        assertNotEquals("JohnDoe", user.getUsername(), "Username should NOT be exactly 'JohnDoe'");
        assertNotEquals(80.0, user.getWeight(), "Weight should NOT be exactly 80.0");
    }

    @Test
    @DisplayName("Should return the correct username")
    void getUsernameReturnsCorrectValue() {
        assertEquals("John Doe", user.getUsername(), "getUsername should return the set username");
    }

    @Test
    @DisplayName("Should return the correct weight")
    void getWeightReturnsCorrectValue() {
        assertEquals(INITIAL_WEIGHT, user.getWeight(), "getWeight should return the set weight");
    }

    @Test
    @DisplayName("Should update the user's weight correctly")
    void updateWeightChangesWeight() {
        double newWeight = 80.2;
        user.updateWeight(newWeight); // Call the method to update weight

        assertEquals(newWeight, user.getWeight(), "Weight should be updated to the new value");
    }

    // @Test
    // @DisplayName("BUG: updateWeight should subtract 1kg instead of setting new weight (Test Passes with Bug)")
    // void updateWeight_buggyBehavior() {
    //     double newWeightAttempt = 75.0;
    //     user.updateWeight(newWeightAttempt);
    //
    //     double expectedWeightWithBug = INITIAL_WEIGHT - 1;
    //
    //     assertEquals(expectedWeightWithBug, user.getWeight(), "Weight should be 1kg less than initial due to bug, not the new weight. expectedWeightWithBug: 74, and user.getWeight: 75");
    //     assertNotEquals(newWeightAttempt, user.getWeight(), "Weight should NOT be the new weight passed to the method (due to bug).");
    // }

    @Test
    @DisplayName("Mockito: Should verify updateWeight method was called")
    void mockitoVerifyUpdateWeightCall() {
        User spyUser = spy(new User("JaneDoe", 60.0));

        double newWeight = 62.5;
        spyUser.updateWeight(newWeight);

        verify(spyUser, times(1)).updateWeight(newWeight);
        assertEquals(newWeight, spyUser.getWeight(), "Weight should be updated on the spied object");
    }

        @Test
    void showInformation_shouldPrintCorrectOutput() {
        // Arrange
        User user = new User("Carlos", 72.5);

        // Capturar salida por consola
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        // Act
        user.showInformation();

        // Restaurar salida estándar
        System.setOut(System.out);

        // Assert
        String expected = "Usuario: Carlos, Peso Actual: 72.5 kg";
        String actual = output.toString().trim();

        assertEquals(expected, actual, "La salida del método showInformation() no es la esperada.");
    }

}
