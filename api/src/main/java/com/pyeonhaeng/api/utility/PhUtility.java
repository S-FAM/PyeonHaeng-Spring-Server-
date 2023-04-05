package com.pyeonhaeng.api.utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pyeonhaeng.api.entity.ItemEntity;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.Objects;

public final class PhUtility {
    private PhUtility(){}


    public static String checkName(String name){
        if ("".equals(name) || name==null){
            return null;
        }
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


    public static String makeResponseJson(List<ItemEntity> entity){
        int count =entity.size();
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("count",count);
        if(count == 0){

            jsonObject.put("message","Can't find any products.");

        }
        else{
            entity.stream().forEach(item -> item.setPk(0));
            jsonObject.put("products",entity);
        }



        return jsonObject.toString();
    }
}
