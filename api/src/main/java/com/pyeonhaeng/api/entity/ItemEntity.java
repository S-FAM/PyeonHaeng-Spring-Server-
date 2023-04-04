package com.pyeonhaeng.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "items")
@Table(name = "items")
public class ItemEntity {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pk;

    @Column(nullable = true, length = 200)
    private String name;

    @Column(nullable = true, length = 200)
    private String img;

    @Column(nullable = true)
    private int price;

    @Column(nullable = true, length = 10)
    private String store;

    @Column(nullable = true, length = 5)
    private String tag;

    @Column(nullable = true, length = 10)
    private String sync_key;

    public ItemEntity(String name,String img, int price, String store, String tag, String sync_key){
        this.name = name;
        this.img = img;
        this.price = price;
        this.store = store;
        this.tag = tag;
        this.sync_key = sync_key;
    }
}
