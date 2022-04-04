package com.nyarstot.Nettop.dbeditor;

import javax.swing.*;

public class DBEEntryPoint extends JFrame {

    public static void main(String[] strings) {
        try {
            start();
        } catch (Exception e) {
            System.err.println("Failed to start application\n" + "Cause: " + e.getMessage());
            System.exit(-1);
        }
    }

    public static void start() {
        DBEMainWindow m_MainWindow = new DBEMainWindow();
    }

}
