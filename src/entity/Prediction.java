package entity;

import java.util.List;

public class Prediction extends BaseEntity {

    private Integer id;
    private Double totalX1;
    private Double totalX2;
    private Double totalY;
    private Double totalX1X1;
    private Double totalX2X2;
    private Double totalYY;
    private Double totalX1Y;
    private Double totalX2Y;
    private Double totalX1X2;

    private Double b0;
    private Double b1;
    private Double b2;

    public Prediction() {
        this.totalX1 = 0.0;
        this.totalX2 = 0.0;
        this.totalY = 0.0;
        this.totalX1X1 = 0.0;
        this.totalX2X2 = 0.0;
        this.totalYY = 0.0;
        this.totalX1Y = 0.0;
        this.totalX2Y = 0.0;
        this.totalX1X2 = 0.0;
    }

    public Prediction(Integer id, Double totalX1, Double totalX2, Double totalY,
                      Double totalX1X1, Double totalX2X2, Double totalYY,
                      Double totalX1Y, Double totalX2Y, Double totalX1X2,
                      Double b0, Double b1, Double b2) {
        this.id = id;
        this.totalX1 = totalX1;
        this.totalX2 = totalX2;
        this.totalY = totalY;
        this.totalX1X1 = totalX1X1;
        this.totalX2X2 = totalX2X2;
        this.totalYY = totalYY;
        this.totalX1Y = totalX1Y;
        this.totalX2Y = totalX2Y;
        this.totalX1X2 = totalX1X2;
        this.b0 = b0;
        this.b1 = b1;
        this.b2 = b2;
    }

    public void setTotal(List<Production> productions) {
        for (Production data : productions) {
            this.incrementTotal(data);
        }

    }

    public void setPrediction(List<Production> productions) {
        calculatePrediction(this,
                productions.size(), round(this.getTotalX1()), round(this.getTotalX2()), round(this.getTotalY()),
                round(this.getTotalX1()), round(this.getTotalX1X1()), round(this.getTotalX1X2()), round(this.getTotalX1Y()),
                round(this.getTotalX2()), round(this.getTotalX1X2()), round(this.getTotalX2X2()), round(this.getTotalX2Y()));

    }

    public void incrementTotal(Production production) {
        this.totalX1 += production.getX1();
        this.totalX2 += production.getX2();
        this.totalY += production.getY();

        this.totalX1X1 += production.getX1x1();
        this.totalX2X2 += production.getX2x2();
        this.totalYY += production.getYy();

        this.totalX1Y += production.getX1y();
        this.totalX2Y += production.getX2y();
        this.totalX1X2 += production.getX1x2();

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getTotalX1() {
        return totalX1;
    }

    public Double getTotalX2() {
        return totalX2;
    }

    public Double getTotalY() {
        return totalY;
    }

    public Double getTotalX1X1() {
        return totalX1X1;
    }

    public Double getTotalX2X2() {
        return totalX2X2;
    }

    public Double getTotalYY() {
        return totalYY;
    }

    public Double getTotalX1Y() {
        return totalX1Y;
    }

    public Double getTotalX2Y() {
        return totalX2Y;
    }

    public Double getTotalX1X2() {
        return totalX1X2;
    }

    public Double getB0() {
        return b0;
    }

    public void setB0(Double b0) {
        this.b0 = b0;
    }

    public Double getB1() {
        return b1;
    }

    public void setB1(Double b1) {
        this.b1 = b1;
    }

    public Double getB2() {
        return b2;
    }

    public void setB2(Double b2) {
        this.b2 = b2;
    }

    public Double getB0Show() {
        return formatShow(b0);
    }

    public Double getB1Show() {
        return formatShow(b1);
    }

    public Double getB2Show() {
        return formatShow(b2);
    }

    private static double formatShow(double n) {
        return round(n*10000)/10000;
    }
}
