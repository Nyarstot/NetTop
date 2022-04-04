package com.nyarstot.Nettop.dbeditor.dirview;

import javax.swing.*;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.tree.DefaultMutableTreeNode;

public class DBEDirExpansionListener implements TreeExpansionListener {
    // Private

    private DBEDirView dirViewInstance;

    // Public
    public DBEDirExpansionListener(DBEDirView dirView) {
        dirViewInstance = dirView;
    }

    public void treeExpanded(TreeExpansionEvent event) {
        final DefaultMutableTreeNode node = dirViewInstance.getTreeNode(
                event.getPath());
        final DBEFileNode fnode = dirViewInstance.getFileNode(node);

        Thread runner = new Thread() {
            @Override
            public void run() {
                if (fnode != null && fnode.expand(node)) {
                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            dirViewInstance.m_DefaultModel.reload(node);
                        }
                    };
                    SwingUtilities.invokeLater(runnable);
                }
            }
        };
        runner.start();
    }

    public void treeCollapsed(TreeExpansionEvent event) {}
}
