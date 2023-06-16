package view.table;

import entity.Production;

import javax.swing.table.AbstractTableModel;
import java.text.DecimalFormat;
import java.util.List;

public class ProductionTableModel extends AbstractTableModel {

    private final DecimalFormat format = new DecimalFormat("###,###");
    private final String[] COLUMN_NAMES = {"Month", "Planning Production", "Actual Production", "Actual Sales"};
    private List<Production> productions;

    public ProductionTableModel(List<Production> productions) {
        this.productions = productions;
    }

    @Override
    public int getRowCount() {
        return productions.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return COLUMN_NAMES[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0: return productions.get(rowIndex).getMonth();
            case 1: return format.format(productions.get(rowIndex).getTarget());
            case 2: return format.format(productions.get(rowIndex).getSell());
            case 3: return format.format(productions.get(rowIndex).getOrder());
            default: return "-";
        }
    }
}