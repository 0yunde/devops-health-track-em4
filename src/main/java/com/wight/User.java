package com.wight;

public class User {
    private String username;
    private double weight;
    private double height;
    private String gender;

    public User(String username, double weight, double height, String gender) {
        this.username = username;
        this.weight = weight;
        this.height = height;
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public String getGender() {
        return gender;
    }

    public void updateWeight(double newWeight) {
        this.weight = newWeight;
    }

    public void updateHeight(double newHeight) {
        this.height = newHeight;
    }

    public double calculateIdealWeight() {
        if (height <= 0) {
            System.err.println("Error: La Altura debe ser un valor positivo para calcular el peso ideal.");
            return -1.0; // Indicate an error or invalid input
        }

        double idealWeight;
        if ("masculino".equals(gender)) {
            idealWeight = (height - 100) - ((height - 150) / 4);
        } else if ("femenino".equals(gender)) {
            idealWeight = (height - 100) - ((height - 150) / 2);
        } else {
            System.err.println("Error: Por Ahora debe especificar entre 'masculino' o 'femenino' para calcular el peso ideal. En un futuro se agregarán nuevas opciones.");
            return -1.0; // Indicate an error or unsupported gender
        }
        return idealWeight;
    }

    public void showInformation() {
        System.out.println("Usuario: " + username +
                           ", Peso Actual: " + weight + " kg" +
                           ", Altura: " + height + " cm" +
                           ", Genero: " + gender);
    }
}

