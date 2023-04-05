package com.pyeonhaeng.api.utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pyeonhaeng.api.entity.ItemEntity;

import java.util.List;
import java.util.Objects;

public final class PhUtility {
    private PhUtility(){}


    public static String checkName(String name){
        name = name.replaceAll("[\'\"']", "");
        return name;
    }

    public static String entity2Json(List<ItemEntity> entity){
        ObjectMapper mapper = new ObjectMapper();
        String json = new String();
        try{
            json = mapper.writeValueAsString(entity);
        }catch (Exception e){
            //TODO: 에러 처리
        }
        return json;
    }
}
