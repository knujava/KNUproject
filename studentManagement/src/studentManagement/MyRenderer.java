package studentManagement;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class MyRenderer extends DefaultTableCellRenderer{
	public Component getTableCellRendererComponent(JTable table, Object value, boolean   isSelected, boolean hasFocus, int row, int column)
    {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (! table.isRowSelected(row)) //���� ���õ� ���� ������ �������� �ʰ� ���� ������ ��쿡�� �������� �����Ѵ�
        {
            if(table.getValueAt(row, 1).toString().indexOf("3")!=-1) { // Ư���� ���� ���� ���� ã�Ƽ� �� ���� �������� �����Ѵ�
                
            	Color colory = new Color(0xFCF695);
            	c.setBackground(colory);
            }else{
                c.setBackground(Color.white);
            }
        }
        return c;
    }

}
