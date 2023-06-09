package show.table;

import entity.Prediction;

import javax.swing.table.AbstractTableModel;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class RegressionTableModel extends AbstractTableModel {

    private final DecimalFormat format = new DecimalFormat("###.#####");
    private final String[] COLUMN_NAMES = {"Name", "Value"};
    private List<TotalTableObject> objects = new ArrayList<>();

    public RegressionTableModel(Prediction prediction) {
        this.objects.add(new TotalTableObject("b0", prediction.getB0()));
        this.objects.add(new TotalTableObject("b1", prediction.getB1()));
        this.objects.add(new TotalTableObject("b2", prediction.getB2()));
    }

    @Override
    public int getRowCount() {
        return objects.size();
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
            case 0: return objects.get(rowIndex).getName();
            case 1: return format.format(objects.get(rowIndex).getValue());
            default: return  "-";
        }
    }
}

class RegressionTableObject {
    protected String name;
    protected Double value;

    public RegressionTableObject(String name, Double value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
