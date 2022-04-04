package com.nyarstot.Nettop.dbeditor.dirview;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;
import java.awt.*;

public class DBEIconCellRenderer extends JLabel implements TreeCellRenderer {
    // Protected

    protected Color m_TextSelectionColor;
    protected Color m_TextNonSelectionColor;
    protected Color m_BgSelectionColor;
    protected Color m_BgNonSelectionColor;
    protected Color m_BorderSelectionColor;

    protected boolean m_Selected;

    // Public

    public DBEIconCellRenderer() {
        super();
        m_TextSelectionColor = UIManager.getColor("Tree.selectionForeground");
        m_TextNonSelectionColor = UIManager.getColor("Tree.textForeground");
        m_BgSelectionColor = UIManager.getColor("Tree.selectionBackground");
        m_BgNonSelectionColor = UIManager.getColor("Tree.textBackground");
        m_BorderSelectionColor = UIManager.getColor("Tree.selectionBorderColor");
        setOpaque(false);
    }

    public Component getTreeCellRendererComponent(JTree tree, Object value,
                                                  boolean selected, boolean expanded,
                                                  boolean leaf, int row, boolean hasFocus) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
        Object obj = node.getUserObject();
        setText(obj.toString());

        if (obj instanceof Boolean) {
            setText("Retrieving data...");
        }

        if (obj instanceof DBEIconData) {
            DBEIconData idata = (DBEIconData)obj;
            if (expanded) {
                setIcon(idata.getExpandedIcon());
            } else {
                setIcon(idata.getIcon());
            }
        } else {
            setIcon(null);
        }

        setFont(tree.getFont());
        setForeground(selected ? m_TextSelectionColor :
                m_TextNonSelectionColor);
        setBackground(selected ? m_BgSelectionColor :
                m_BgNonSelectionColor);
        m_Selected = selected;

        return this;
    }

    public void paintComponent(Graphics graphics) {
        Color bgColor = getBackground();
        Icon icon = getIcon();

        graphics.setColor(bgColor);
        int offset = 0;
        if (icon != null && getText() != null) {
            offset = (icon.getIconWidth() + getIconTextGap());
        }
        graphics.fillRect(offset, 0, getWidth() - 1 - offset, getHeight() - 1);

        if (m_Selected) {
            graphics.setColor(m_BorderSelectionColor);
            graphics.drawRect(offset, 0, getWidth() - 1 -offset, getHeight() - 1);
        }

        super.paintComponent(graphics);
    }

}
