package com.nyarstot.nettop;

public class NetTopEntryPoint {
    public static void main(String[] args) {
        try {
            start();
        } catch (Exception e) {
            System.err.println("Failed to start system gui\n" + "Cause: " + e.getMessage());
            System.exit(-1);
        }
    }

    public static void start() {
        NetTopGUI netTopGui = new NetTopGUI();
    }
}
