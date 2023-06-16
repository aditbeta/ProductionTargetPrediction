package entity;

import repository.ProductionObject;

public class Production extends BaseEntity {

    private Integer id;
    private final String month;
    private final Double sell;
    private final Double order;
    private final Double target;
    private final Double x1;
    private final Double x2;
    private final Double y;
    private final Double x1x1;
    private final Double x2x2;
    private final Double yy;
    private final Double x1y;
    private final Double x2y;
    private final Double x1x2;

    public Production(ProductionObject production) {
        this.id = production.getId();
        this.month = production.getMonth();
        this.sell = production.getSell();
        this.order = production.getOrder();
        this.target = production.getTarget();

        Double divider = 10000.0;
        this.x1 = round(sell/ divider);
        this.x2 = round(order/ divider);
        this.y = round(target/ divider);

        double divider2 = divider * divider;
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

    public Double getSell() {
        return sell;
    }

    public Double getOrder() {
        return order;
    }

    public Double getTarget() {
        return target;
    }

    public Double getX1() {
        return x1;
    }

    public Double getX2() {
        return x2;
    }

    public Double getY() {
        return y;
    }

    public Double getX1x1() {
        return x1x1;
    }

    public Double getX2x2() {
        return x2x2;
    }

    public Double getYy() {
        return yy;
    }

    public Double getX1y() {
        return x1y;
    }

    public Double getX2y() {
        return x2y;
    }

    public Double getX1x2() {
        return x1x2;
    }

    public Double getRegressionResult(Prediction prediction) {
        return prediction.getB0() + (prediction.getB1() * x1) + (prediction.getB2() * x2);
    }
}
