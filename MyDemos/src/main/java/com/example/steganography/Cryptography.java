package com.example.steganography;

public class Cryptography {

    private String password;
    private int index;
    private int pLength;

    public Cryptography(String password) {
        this.password = password;
        index = -1;
        this.pLength = this.password.length();
    }

    public int encrypt(int data) {
        index = (index + 1) % pLength;
        return data ^ password.charAt(index);
    }

    public int decrypt(int data) {
        index = (index + 1) % pLength;
        return data ^ password.charAt(index);
    }
}
