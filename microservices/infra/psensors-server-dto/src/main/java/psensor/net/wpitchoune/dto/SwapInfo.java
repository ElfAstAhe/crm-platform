package psensor.net.wpitchoune.dto;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.json.bind.config.PropertyOrderStrategy;
import java.io.Serializable;

@JsonbPropertyOrder(PropertyOrderStrategy.ANY)
public class SwapInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonbProperty("total")
    private Long total;

    @JsonbProperty("free")
    private Long free;

    public SwapInfo() {
        // default
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getFree() {
        return free;
    }

    public void setFree(Long free) {
        this.free = free;
    }
}
