package entity;

import java.text.DecimalFormat;

public class ProductionData {

    private String month;
    private Double sell;
    private Double order;
    private Double target;
    private Double x1x1;
    private Double x2x2;
    private Double yy;
    private Double x1y;
    private Double x2y;
    private Double x1x2;
    private DecimalFormat format = new DecimalFormat("###");

    public ProductionData(String month, Double sell, Double order, Double target) {
        this.month = month;
        this.sell = sell;
        this.order = order;
        this.target = target;

        sell = sell/10000;
        order = order/10000;
        target = target/10000;

        this.x1x1 = sell*sell;
        this.x2x2 = order*order;
        this.yy = target*target;
        this.x1y = sell*target;
        this.x2y = order*target;
        this.x1x2 = sell*order;
    }

    public ProductionData createProductionData(String month, Double sell, Double order, Double target) {
        return new ProductionData(month, sell, order, target);
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Double getSell() {
        return sell;
    }

    public void setSell(Double sell) {
        this.sell = sell;
    }

    public Double getOrder() {
        return order;
    }

    public void setOrder(Double order) {
        this.order = order;
    }

    public Double getTarget() {
        return target;
    }

    public void setTarget(Double target) {
        this.target = target;
    }

    public Double getX1x1() {
        return x1x1;
    }

    public void setX1x1(Double x1x1) {
        this.x1x1 = x1x1;
    }

    public Double getX2x2() {
        return x2x2;
    }

    public void setX2x2(Double x2x2) {
        this.x2x2 = x2x2;
    }

    public Double getYy() {
        return yy;
    }

    public void setYy(Double yy) {
        this.yy = yy;
    }

    public Double getX1y() {
        return x1y;
    }

    public void setX1y(Double x1y) {
        this.x1y = x1y;
    }

    public Double getX2y() {
        return x2y;
    }

    public void setX2y(Double x2y) {
        this.x2y = x2y;
    }

    public Double getX1x2() {
        return x1x2;
    }

    public void setX1x2(Double x1x2) {
        this.x1x2 = x1x2;
    }

    public DecimalFormat getFormat() {
        return format;
    }

    public void setFormat(DecimalFormat format) {
        this.format = format;
    }

    @Override
    public String toString() {
        return fixedLengthString(month, 12)
                + fixedLengthString(format.format(sell).toString(), 16)
                + fixedLengthString(format.format(order).toString(), 16)
                + fixedLengthString(format.format(target).toString(), 21);
    }
    public String toStringWithPrediction() {
        return toString()
                + fixedLengthString(format.format(x1x1).toString(), 20)
                + fixedLengthString(format.format(x2x2).toString(), 20)
                + fixedLengthString(format.format(yy).toString(), 20)
                + fixedLengthString(format.format(x1y).toString(), 20)
                + fixedLengthString(format.format(x2y).toString(), 20)
                + fixedLengthString(format.format(x1x2).toString(), 20);
    }

    public static String fixedLengthString(String string, int length) {
        return String.format("%1$"+length+"s", string);
    }
}
