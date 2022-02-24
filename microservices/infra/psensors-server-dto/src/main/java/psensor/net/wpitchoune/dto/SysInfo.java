package psensor.net.wpitchoune.dto;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.json.bind.config.PropertyOrderStrategy;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@JsonbPropertyOrder(PropertyOrderStrategy.ANY)
public class SysInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonbProperty("load")
    private BigDecimal load;

    @JsonbProperty("load_1")
    private BigDecimal load1;

    @JsonbProperty("load_5")
    private BigDecimal load5;

    @JsonbProperty("load_15")
    private BigDecimal load15;

    @JsonbProperty("uptime")
    private BigDecimal uptime;

    @JsonbProperty("mem_unit")
    private Integer memUnit;

    @JsonbProperty("ram")
    private RamInfo ram;

    @JsonbProperty("swap")
    private SwapInfo swap;

    @JsonbProperty("net")
    private List<NetInfo> net;

    public SysInfo() {
        net = new ArrayList<>();
    }

    public BigDecimal getLoad() {
        return load;
    }

    public void setLoad(BigDecimal load) {
        this.load = load;
    }

    public BigDecimal getLoad1() {
        return load1;
    }

    public void setLoad1(BigDecimal load1) {
        this.load1 = load1;
    }

    public BigDecimal getLoad5() {
        return load5;
    }

    public void setLoad5(BigDecimal load5) {
        this.load5 = load5;
    }

    public BigDecimal getLoad15() {
        return load15;
    }

    public void setLoad15(BigDecimal load15) {
        this.load15 = load15;
    }

    public Integer getMemUnit() {
        return memUnit;
    }

    public void setMemUnit(Integer memUnit) {
        this.memUnit = memUnit;
    }

    public RamInfo getRam() {
        return ram;
    }

    public void setRam(RamInfo ram) {
        this.ram = ram;
    }

    public SwapInfo getSwap() {
        return swap;
    }

    public void setSwap(SwapInfo swap) {
        this.swap = swap;
    }

    public List<NetInfo> getNet() {
        return net;
    }

    public void setNet(List<NetInfo> net) {
        this.net = net;
    }

    public BigDecimal getUptime() {
        return uptime;
    }

    public void setUptime(BigDecimal uptime) {
        this.uptime = uptime;
    }
}
