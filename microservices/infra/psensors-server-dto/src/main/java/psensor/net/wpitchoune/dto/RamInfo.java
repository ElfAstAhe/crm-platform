package psensor.net.wpitchoune.dto;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.json.bind.config.PropertyOrderStrategy;
import java.io.Serializable;

@JsonbPropertyOrder(PropertyOrderStrategy.ANY)
public class RamInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonbProperty("total")
    private Long total;

    @JsonbProperty("free")
    private Long free;

    @JsonbProperty("shared")
    private Long shared;

    @JsonbProperty("buffer")
    private Long buffer;

    public RamInfo() {
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

    public Long getShared() {
        return shared;
    }

    public void setShared(Long shared) {
        this.shared = shared;
    }

    public Long getBuffer() {
        return buffer;
    }

    public void setBuffer(Long buffer) {
        this.buffer = buffer;
    }
}
