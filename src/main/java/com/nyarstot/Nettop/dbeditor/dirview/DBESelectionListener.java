package com.nyarstot.Nettop.dbeditor.dirview;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

public class DBESelectionListener implements TreeSelectionListener {
    // Private

    private DBEDirView dirViewInstance;

    // Public
    DBESelectionListener(DBEDirView dirView) {
        dirViewInstance = dirView;
    }

    public void valueChanged(TreeSelectionEvent event) {
        DefaultMutableTreeNode node = dirViewInstance.getTreeNode(
                event.getPath());
        DBEFileNode fnode = dirViewInstance.getFileNode(node);

        if (fnode != null) {
            dirViewInstance.m_Display.setText(fnode.getFile()
                    .getAbsolutePath());
        } else {
            dirViewInstance.m_Display.setText("");
        }
    }
}
