package view.table;

import entity.Prediction;

import javax.swing.table.AbstractTableModel;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class TotalTableModel extends AbstractTableModel {

    private final DecimalFormat format = new DecimalFormat("###,###");
    private final String[] COLUMN_NAMES = {"Total", "Value"};
    private final List<TotalTableObject> objects = new ArrayList<>();

    public TotalTableModel(Prediction prediction) {
        this.objects.add(new TotalTableObject("Total X1", prediction.getTotalX1()));
        this.objects.add(new TotalTableObject("Total X2", prediction.getTotalX2()));
        this.objects.add(new TotalTableObject("Total Y", prediction.getTotalY()));
        this.objects.add(new TotalTableObject("Total X1²", prediction.getTotalX1X1()));
        this.objects.add(new TotalTableObject("Total X2²", prediction.getTotalX2X2()));
        this.objects.add(new TotalTableObject("Total Y²", prediction.getTotalYY()));
        this.objects.add(new TotalTableObject("Total X1Y", prediction.getTotalX1Y()));
        this.objects.add(new TotalTableObject("Total X2Y", prediction.getTotalX2Y()));
        this.objects.add(new TotalTableObject("Total X1X2", prediction.getTotalX1X2()));
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
        return switch (columnIndex) {
            case 0 -> objects.get(rowIndex).getName();
            case 1 -> format.format(objects.get(rowIndex).getValue());
            default -> "-";
        };
    }
}

class TotalTableObject {
    protected String name;
    protected Double value;

    public TotalTableObject(String name, Double value) {
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
