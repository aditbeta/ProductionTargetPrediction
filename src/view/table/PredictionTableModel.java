package view.table;

import entity.Production;

import javax.swing.table.AbstractTableModel;
import java.text.DecimalFormat;
import java.util.List;

public class PredictionTableModel extends AbstractTableModel {

    private final DecimalFormat format = new DecimalFormat("###,###");
    private final String[] COLUMN_NAMES = {"Month", "Planning Production (Y)", "Actual Production (X1)", "Actual Sales (X2)",
            "X1²", "X2²", "Y²", "X1Y", "X2Y", "X1X2"};
    private final List<Production> productions;

    public PredictionTableModel(List<Production> productions) {
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
        return switch (columnIndex) {
            case 0 -> productions.get(rowIndex).getMonth();
            case 1 -> format.format(productions.get(rowIndex).getY());
            case 2 -> format.format(productions.get(rowIndex).getX1());
            case 3 -> format.format(productions.get(rowIndex).getX2());
            case 4 -> format.format(productions.get(rowIndex).getX1x1());
            case 5 -> format.format(productions.get(rowIndex).getX2x2());
            case 6 -> format.format(productions.get(rowIndex).getYy());
            case 7 -> format.format(productions.get(rowIndex).getX1y());
            case 8 -> format.format(productions.get(rowIndex).getX2y());
            case 9 -> format.format(productions.get(rowIndex).getX1x2());
            default -> "-";
        };
    }
}
