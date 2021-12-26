package com.guwave.onetest.instrument.domain.instrument.model;

import com.google.common.collect.Sets;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import java.util.Set;

@Setter
@Getter
public class RFPMInstrument extends InstrumentBase{
    private String customFPGAFilePath;
    private String calibrationFilePath;
    private String iviSwitchResourceName;
    private Set<String> ports;

    public RFPMInstrument(String name) {
        super(name, InstrumentType.RFRM);
    }

    public void setPorts(Set<String> ports) {
        if (CollectionUtils.isEmpty(ports)) {
            this.ports = Sets.newHashSet();
        } else {
            this.ports = ports;
        }
    }

    //省略其他业务函数
}
