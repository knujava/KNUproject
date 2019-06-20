package studentManagement;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class MyRenderer extends DefaultTableCellRenderer{
	public Component getTableCellRendererComponent(JTable table, Object value, boolean   isSelected, boolean hasFocus, int row, int column)
    {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (! table.isRowSelected(row)) //현재 선택된 행의 색상은 변경하지 않고 선택 해제된 경우에만 배경색상을 변경한다
        {
            if(table.getValueAt(row, 1).toString().indexOf("3")!=-1) { // 특정한 값을 가진 셀을 찾아서 그 셀만 배경색상을 변경한다
                
            	Color colory = new Color(0xFCF695);
            	c.setBackground(colory);
            }else{
                c.setBackground(Color.white);
            }
        }
        return c;
    }

}
