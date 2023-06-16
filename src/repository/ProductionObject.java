package repository;

public class ProductionObject {

    private Integer id;
    private String month;
    private Double sell;
    private Double order;
    private Double target;

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


    public Double getSell() {
        return sell;
    }

    public Double getOrder() {
        return order;
    }

    public Double getTarget() {
        return target;
    }
}
