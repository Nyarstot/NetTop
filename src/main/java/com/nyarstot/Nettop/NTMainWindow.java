package com.nyarstot.Nettop;

import javax.swing.*;

public class NTMainWindow extends JFrame {
    // Private

    private JProgressBar progressBar1;
    private JPanel mainFrame;
    private JTextPane textPane1;
    private JScrollPane answerPane;
    private JScrollPane questionPane;

    public NTMainWindow() {
        setTitle("Nettop expert system");
        setSize(500, 500);

        setContentPane(mainFrame);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }


}
