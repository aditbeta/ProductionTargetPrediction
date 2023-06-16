package view.table;

import entity.Prediction;
import entity.Production;

import javax.swing.table.AbstractTableModel;
import java.text.DecimalFormat;
import java.util.List;

public class PredictionResultTable extends AbstractTableModel {

    private final DecimalFormat formatCount = new DecimalFormat("###,###.#########");
    private final DecimalFormat format = new DecimalFormat("###,###.####");
    private final String[] COLUMN_NAMES = {"Month", "Actual Production", "Actual Sales", "Regression Value", "Target Production"};
    private final List<Production> productions;
    private final Prediction prediction;

    public PredictionResultTable(List<Production> productions, Prediction prediction) {
        this.productions = productions;
        this.prediction = prediction;
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
            case 1 -> format.format(productions.get(rowIndex).getSell());
            case 2 -> format.format(productions.get(rowIndex).getOrder());
            case 3 -> formatCount.format(productions.get(rowIndex).getRegressionResult(prediction));
            case 4 -> format.format(productions.get(rowIndex).getRegressionResult(prediction));
            default -> "-";
        };
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        return (columnIndex == 0) ? String.class : Integer.class;
    }
}
