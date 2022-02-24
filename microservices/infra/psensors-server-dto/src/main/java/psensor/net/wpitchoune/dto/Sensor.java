package psensor.net.wpitchoune.dto;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.json.bind.config.PropertyOrderStrategy;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@JsonbPropertyOrder(PropertyOrderStrategy.ANY)
public class Sensor implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonbProperty("id")
    private String id;

    @JsonbProperty("name")
    private String name;

    @JsonbProperty("type")
    private int type;

    @JsonbProperty("min")
    private BigDecimal min;

    @JsonbProperty("max")
    private BigDecimal max;

    @JsonbProperty("measures")
    private List<Measure> measures;

    @JsonbProperty("last_measure")
    private Measure last;

    public Sensor() {
        // default
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public BigDecimal getMin() {
        return min;
    }

    public void setMin(BigDecimal min) {
        this.min = min;
    }

    public BigDecimal getMax() {
        return max;
    }

    public void setMax(BigDecimal max) {
        this.max = max;
    }

    public List<Measure> getMeasures() {
        return measures;
    }

    public void setMeasures(List<Measure> measures) {
        this.measures = measures;
    }

    public Measure getLast() {
        return last;
    }

    public void setLast(Measure last) {
        this.last = last;
    }
}
