package com.nyarstot.Nettop;

public class NTEntryPoint {

    public static void main(String[] args) {
        try {
            start();
        } catch (Exception e) {
            System.err.println("Failed to start system gui\n" + "Cause: " + e.getMessage());
            System.exit(-1);
        }
    }

    public static void start() {
        NTMainWindow mainWindow = new NTMainWindow();
    }

}
