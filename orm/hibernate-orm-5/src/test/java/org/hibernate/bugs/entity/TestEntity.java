package org.hibernate.bugs.entity;

import org.hibernate.bugs.bean.SomeValue;
import org.hibernate.bugs.converter.UUIDConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity(name = "TestEntity")
public class TestEntity {

    @Id
    @GeneratedValue
    private Integer id;

    @Convert(converter = UUIDConverter.class)
    @Column( columnDefinition = "BINARY(16)")
    private SomeValue someValue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SomeValue getSomeValue() {
        return someValue;
    }

    public void setSomeValue(SomeValue someValue) {
        this.someValue = someValue;
    }

}