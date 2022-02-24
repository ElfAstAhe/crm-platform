package psensor.net.wpitchoune.dto;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.json.bind.config.PropertyOrderStrategy;
import java.io.Serializable;

@JsonbPropertyOrder(PropertyOrderStrategy.ANY)
public class NetInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonbProperty("name")
    private String name;

    @JsonbProperty("bytes_in")
    private Long bytesIn;

    @JsonbProperty("bytes_out")
    private Long bytesOut;

    public NetInfo() {
        // default
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getBytesIn() {
        return bytesIn;
    }

    public void setBytesIn(Long bytesIn) {
        this.bytesIn = bytesIn;
    }

    public Long getBytesOut() {
        return bytesOut;
    }

    public void setBytesOut(Long bytesOut) {
        this.bytesOut = bytesOut;
    }
}
