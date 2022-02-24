package psensor.net.wpitchoune.dto;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.json.bind.config.PropertyOrderStrategy;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@JsonbPropertyOrder(PropertyOrderStrategy.ANY)
public class Measure implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonbProperty("value")
    private BigDecimal value;

    @JsonbProperty("time")
    private long time;

    public Measure() {
        // default
    }

    public Measure(BigDecimal value, Long time) {
        this.value = value;
        this.time = time;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public long getJavaTime() {
        return this.time * 1000;
    }

    public LocalDateTime getLocalDateTime() {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(getJavaTime()), ZoneId.systemDefault());
    }
}
