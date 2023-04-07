package com.pyeonhaeng.api.entity;


import lombok.Data;

@Data
public class ItemReturnData {
    private  String name;

    private String img;

    private int price;

    private String store;

    private String tag;

    private String date;
}
