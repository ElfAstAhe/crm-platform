package org.hsh.crm.ms.study.ps.dto.convertor;

import org.hsh.crm.ms.study.ps.dal.entities.TestData;
import org.hsh.crm.ms.study.ps.dto.TestDto;

public class TestDtoConvertor {
    private TestDtoConvertor() {
        // hide
    }

    public static TestDto toDto(TestData entity) {
        if(entity == null)
            return null;

        TestDto dto = new TestDto();
        dto.setId(entity.getId());
        dto.setDummy(entity.getDummy());
        dto.setDummy2(entity.getDummy2());

        return dto;
    }
}
