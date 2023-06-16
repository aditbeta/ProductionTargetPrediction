package view.table;

import entity.Prediction;
import entity.Production;

import javax.swing.table.AbstractTableModel;
import java.text.DecimalFormat;
import java.util.List;

public class RegressionResultTable extends AbstractTableModel {

    private final DecimalFormat formatCount = new DecimalFormat("###.#########");
    private final DecimalFormat format = new DecimalFormat("###.####");
    private final String[] COLUMN_NAMES = {"Month", "Actual Production", "Actual Sales", "Regression Value", "Target Production"};
    private List<Production> productions;
    private Prediction prediction;

    public RegressionResultTable(List<Production> productions, Prediction prediction) {
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
        switch(columnIndex) {
            case 0: return productions.get(rowIndex).getMonth();
            case 1: return format.format(productions.get(rowIndex).getSell());
            case 2: return format.format(productions.get(rowIndex).getOrder());
            case 3: return formatCount.format(productions.get(rowIndex).getRegressionResult(prediction));
            case 4: return format.format(productions.get(rowIndex).getRegressionResult(prediction));
            default: return "-";
        }
    }
}
