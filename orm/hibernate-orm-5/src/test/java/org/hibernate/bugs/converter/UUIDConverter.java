package org.hibernate.bugs.converter;

import org.hibernate.bugs.bean.SomeValue;

import javax.persistence.AttributeConverter;
import java.util.UUID;

public class UUIDConverter implements AttributeConverter<SomeValue, UUID> {
    @Override
    public UUID convertToDatabaseColumn(SomeValue attribute) {
        return attribute == null ? null : attribute.getUuid();
    }

    @Override
    public SomeValue convertToEntityAttribute(UUID dbData) {
        return dbData == null ? null : new SomeValue( dbData );
    }
}