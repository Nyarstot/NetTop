package com.nyarstot.Nettop.dbeditor.dirview;

import javax.swing.*;

public class DBEIconData {
    // Protected

    protected Icon   m_Icon;
    protected Icon   m_ExpandedIcon;
    protected Object m_Data;

    // Public

    public static final ImageIcon ICON_COMPUTER = new ImageIcon(
            "main/resources/com/nyarstot/nettop/dbeditor/icons/computer.gif");
    public static final ImageIcon ICON_DISK = new ImageIcon(
            "main/resources/com/nyarstot/nettop/dbeditor/icons/harddrive.png");
    public static final ImageIcon ICON_FOLDER = new ImageIcon(
            "main/resources/com/nyarstot/nettop/dbeditor/icons/folder.png");
    public static final ImageIcon ICON_EXPANDFOLDER = new ImageIcon(
            "main/resources/com/nyarstot/nettop/dbeditor/icons/expandfolder.png");
    public static final ImageIcon ICON_FILE = new ImageIcon(
            "main/resources/com/nyarstot/nettop/dbeditor/icons/file.png");

    public DBEIconData(Icon icon, Object data) {
        m_Icon = icon;
        m_ExpandedIcon = null;
        m_Data = data;
    }

    public DBEIconData(Icon icon, Icon expandedIcon, Object data) {
        m_Icon = icon;
        m_ExpandedIcon = expandedIcon;
        m_Data = data;
    }

    public Icon getIcon() { return m_Icon; }
    public Icon getExpandedIcon() { return m_ExpandedIcon; }
    public Object getObject() { return m_Data; }
    public String toString() { return m_Data.toString(); }

}
