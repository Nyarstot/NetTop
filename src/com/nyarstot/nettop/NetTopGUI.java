package com.nyarstot.nettop;

import org.jpl7.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NetTopGUI extends JFrame implements ActionListener {
    // Private

    private JTextArea textArea;
    private JMenuBar menuBar;
    private JButton buttonYes;
    private JButton buttonNo;

    // Public

    public NetTopGUI() {
        setTitle("NetTop Expert System");
        setBounds(350, 200, 500, 300);

        textArea = new JTextArea();
        textArea.setBounds(10, 10, 465, 200);
        textArea.setEditable(false);

        buttonYes = new JButton("Yes");
        buttonYes.setBounds(10, 220, 230, 25);
        buttonNo = new JButton("No");
        buttonNo.setBounds(245, 220, 230, 25);

        getContentPane().setLayout(null);
        getContentPane().add(buttonYes);
        getContentPane().add(buttonNo);
        getContentPane().add(textArea);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
