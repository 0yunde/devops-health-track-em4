package com.wight;

public class User {
    private String username;
    private double weight;

    public User(String username, double weight) {
        this.username = username;
        this.weight = weight;
    }

    public String getUsername() {
        return username;
    }

    public double getWeight() {
        return weight;
    }

    public void updateWeight(double newWeight) {
        this.weight = newWeight;
    }

    public void showInformation() {
        System.out.println("Usuario: " + username + ", Peso Actual: " + weight + " kg");
    }
}

