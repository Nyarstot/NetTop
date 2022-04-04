package com.nyarstot.Nettop.dbeditor;

import javax.swing.*;
import java.awt.*;
import java.io.File;

import com.nyarstot.Nettop.dbeditor.dirview.DBEDirView;

public class DBEMainWindow extends JFrame {
    // Private

    private JPanel m_JPanel;
    private BorderLayout m_BaseLayout;
    private GridBagLayout m_GridBagLayout;

    private JMenuBar m_MenuBar;
    private JMenu m_MenuItem;

    private TextArea m_TextArea;

    private DBEDirView m_DirView;

    // Public

    public DBEMainWindow() {
        setName("database editor");
        setSize(1280, 720);

        /* Menu bar initializing */
        m_MenuBar = new JMenuBar();
        m_MenuItem = new JMenu("File");
        m_MenuItem.add(m_MenuItem);

        /* Directory viewer initialization */
        m_DirView = new DBEDirView(new File("./database/"));

        /* Layout initializing */
        m_BaseLayout = new BorderLayout();
        m_JPanel = new JPanel(m_BaseLayout);
        setContentPane(m_JPanel);

        m_JPanel.add(m_MenuBar, BorderLayout.NORTH);
        m_JPanel.add(m_DirView.getNode(), BorderLayout.LINE_START);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(true);

    }

}
