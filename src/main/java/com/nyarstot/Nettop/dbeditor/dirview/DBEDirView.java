package com.nyarstot.Nettop.dbeditor.dirview;

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;
import java.io.File;

public class DBEDirView extends JFrame {
    // Protected

    protected JTree m_Tree;
    protected DefaultTreeModel m_DefaultModel;
    protected JTextField m_Display;
    protected JScrollPane scrollPane;

    // Public

    public DBEDirView(File path) {
        DefaultMutableTreeNode nodeTop = new DefaultMutableTreeNode(
                new DBEIconData(DBEIconData.ICON_COMPUTER, null, "Computer"));

        DefaultMutableTreeNode node;
        File[] roots = path.listFiles();
        for (int i = 0; i < roots.length; i++) {
            node = new DefaultMutableTreeNode(new DBEIconData(
                    DBEIconData.ICON_DISK, null, new DBEFileNode(roots[i])));
            nodeTop.add(node);
            node.add(new DefaultMutableTreeNode(Boolean.TRUE));
        }

        m_DefaultModel = new DefaultTreeModel(nodeTop);
        m_Tree = new JTree(m_DefaultModel);
        m_Tree.putClientProperty("JTree.lineStyle", "Angled");

        TreeCellRenderer renderer = new DBEIconCellRenderer();
        m_Tree.setCellRenderer(renderer);

        m_Tree.addTreeExpansionListener(new DBEDirExpansionListener(this));
        m_Tree.addTreeSelectionListener(new DBESelectionListener(this));

        m_Tree.getSelectionModel().setSelectionMode(
                TreeSelectionModel.SINGLE_TREE_SELECTION);
        m_Tree.setShowsRootHandles(true);
        m_Tree.setEditable(false);

        scrollPane = new JScrollPane();
        scrollPane.getViewport().add(m_Tree);
        scrollPane.setPreferredSize(new Dimension(250, 700));
    }

    public DefaultTreeModel getTreeModel() {
        return m_DefaultModel;
    }

    public JScrollPane getNode() {
        return scrollPane;
    }

    public DefaultMutableTreeNode getTreeNode(TreePath path) {
        return (DefaultMutableTreeNode)(path.getLastPathComponent());
    }

    public DBEFileNode getFileNode(DefaultMutableTreeNode node) {
        if (node == null) { return null; }

        Object obj = node.getUserObject();
        if (obj instanceof DBEIconData) {
            obj = ((DBEIconData)obj).getObject();
        }

        if (obj instanceof DBEFileNode) {
            return (DBEFileNode)obj;
        } else {
            return null;
        }
    }

}
