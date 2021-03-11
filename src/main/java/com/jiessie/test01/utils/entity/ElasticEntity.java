package com.jiessie.test01.utils.entity;

import lombok.Data;

import javax.persistence.Transient;


@Data
public class ElasticEntity {
    public static String x = "0";

    private String id;

    @Transient
    private String data;


    public ElasticEntity(String id, String data) {
        this.id = id;
        this.data = data;
    }
}
