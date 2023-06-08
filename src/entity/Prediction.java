package entity;

public class Prediction {

    private Double b0;
    private Double b1;
    private Double b2;

    public Double getB0() {
        return b0;
    }

    public Double getB1() {
        return b1;
    }

    public Double getB2() {
        return b2;
    }

    public Prediction(Double b0, Double b1, Double b2) {
        this.b0 = b0;
        this.b1 = b1;
        this.b2 = b2;
    }
}
