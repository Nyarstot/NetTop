package com.nyarstot.Nettop.dbeditor.dirview;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;
import java.util.Vector;

public class DBEFileNode {
    // Protected

    protected File m_File;

    protected File[] listOfFiles() {
        if (!m_File.isDirectory()) { return null; }
        try {
            return m_File.listFiles();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                    "Error when try to read directory " + m_File.getAbsolutePath(),
                    "Warning", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }

    // Public

    public DBEFileNode(File file) {
        m_File = file;
    }

    public String toString() {
        return m_File.getName().length() > 0 ?
                m_File.getName() : m_File.getPath();
    }

    public boolean expand(DefaultMutableTreeNode parent) {
        DefaultMutableTreeNode flag =
                (DefaultMutableTreeNode)parent.getFirstChild();

        if (flag == null) { return false; }
        Object obj = flag.getUserObject();
        if (!(obj instanceof Boolean)) { return false; }

        parent.removeAllChildren();

        File[] files = listOfFiles();
        if (files == null) { return true; }

        Vector vec = new Vector();

        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            if (!(file.isDirectory())) { continue; }

            DBEFileNode newNode = new DBEFileNode(file);

            boolean isAdded = false;
            for (int k = 0; k < vec.size(); k++) {
                DBEFileNode fileNode = (DBEFileNode)vec.elementAt(k);
                if (newNode.compareTo(fileNode) < 0) {
                    vec.insertElementAt(newNode, k);
                    isAdded = true;
                    break;
                }
            }

            if (!isAdded) {
                vec.addElement(newNode);
            }
        }

        for (int i = 0; i < vec.size(); i++) {
            DBEFileNode newNode = (DBEFileNode)vec.elementAt(i);
            DBEIconData idata = new DBEIconData(DBEIconData.ICON_FOLDER,
                    DBEIconData.ICON_EXPANDFOLDER, newNode);
            DefaultMutableTreeNode treeNode =
                    new DefaultMutableTreeNode(idata);
            parent.add(treeNode);

            if (newNode.hasSubDirs()) {
                treeNode.add(new DefaultMutableTreeNode(Boolean.TRUE));
            }
        }

        return true;
    }

    public boolean hasSubDirs() {
        File[] files = listOfFiles();
        if (files == null) { return false; }
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                return true;
            }
        }
        return false;
    }

    public int compareTo(DBEFileNode toCompare) {
        return m_File.getName().compareToIgnoreCase(
                toCompare.m_File.getName());
    }

    public File getFile() {
        return m_File;
    }

}
