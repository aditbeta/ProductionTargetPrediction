package repository;

public class ProductionObject {

    private Integer id;
    private String month;
    private Double sell;
    private Double order;
    private Double target;
    private Integer monthNum;

    public ProductionObject(String month, Double sell, Double order, Double target) {
        this.month = month;
        this.sell = sell;
        this.order = order;
        this.target = target;
    }

    public ProductionObject(Integer id, String month, Double sell, Double order, Double target) {
        this.id = id;
        this.month = month;
        this.sell = sell;
        this.order = order;
        this.target = target;
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
}
