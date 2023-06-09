package entity;

import java.text.DecimalFormat;
import repository.ProductionObject;

import static service.helper.round;

public class Production {

    private Integer id;
    private String month;
    private Double sell;
    private Double order;
    private Double target;
    private Double x1;
    private Double x2;
    private Double y;
    private Double x1x1;
    private Double x2x2;
    private Double yy;
    private Double x1y;
    private Double x2y;
    private Double x1x2;
    private DecimalFormat format = new DecimalFormat("###");
    private Double divider = 10000.0;
    private Double divider2 = divider*divider;

    public Production(ProductionObject production) {
        this.id = production.getId();
        this.month = production.getMonth();
        this.sell = production.getSell();
        this.order = production.getOrder();
        this.target = production.getTarget();

        this.x1 = round(sell/divider);
        this.x2 = round(order/divider);
        this.y = round(target/divider);

        this.x1x1 = sell*sell / divider2;
        this.x2x2 = order*order / divider2;
        this.yy = target*target / divider2;
        this.x1y = sell*target / divider2;
        this.x2y = order*target / divider2;
        this.x1x2 = sell*order / divider2;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Double getX1() {
        return x1;
    }

    public void setX1(Double x1) {
        this.x1 = x1;
    }

    public Double getX2() {
        return x2;
    }

    public void setX2(Double x2) {
        this.x2 = x2;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
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
        return fixedLengthString(month, 12)
                + fixedLengthString(format.format(x1).toString(), 16)
                + fixedLengthString(format.format(x2).toString(), 16)
                + fixedLengthString(format.format(y).toString(), 21)
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
