package GuiLayer;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * @author tai
 *
 *         custom table
 */
public class MyTable extends JTable {
	public MyTable() {
		super();
		// căn giữa header của bảng
		((DefaultTableCellRenderer) this.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
		// table.getTableHeader().setReorderingAllowed(false);
		this.getTableHeader().setToolTipText("Click đúp để sắp xếp");
		this.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.setRowHeight(25);
		this.setAutoCreateRowSorter(true);
		this.setIntercellSpacing(new Dimension(5, 5));
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	// tạo chữ gợi ý
	@Override
	public String getToolTipText(MouseEvent e) {
		String tip = null;
		java.awt.Point p = e.getPoint();
		int rowIndex = rowAtPoint(p);
		int colIndex = columnAtPoint(p);
		tip = getValueAt(rowIndex, colIndex).toString();
		return tip;
	}

	// tự động resize bảng
	@Override
	public boolean getScrollableTracksViewportWidth() {
		return getPreferredSize().width < getParent().getWidth();
	}
}
