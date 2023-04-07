package com.pyeonhaeng.api.utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pyeonhaeng.api.entity.ItemEntity;
import com.pyeonhaeng.api.entity.ItemReturnData;
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

    public static String entity2Json(List<ItemReturnData> entity){
        ObjectMapper mapper = new ObjectMapper();
        String json = new String();
        try{
            json = mapper.writeValueAsString(entity);
        }catch (Exception e){
            //TODO: 에러 처리
        }
        return json;
    }


    public static String makeResponseJson(List<ItemReturnData> entity){
        int count =entity.size();
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("count",count);
        if(count == 0){

            jsonObject.put("message","Can't find any products.");

        }
        else{
            jsonObject.put("products",entity);
        }



        return jsonObject.toString();
    }
}
