package com.pyeonhaeng.api.controller;


import com.pyeonhaeng.api.entity.ItemEntity;
import com.pyeonhaeng.api.service.SearchServiceImpl;
import com.pyeonhaeng.api.utility.PhUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/products")
@RestController
public class SearchController {

    private final SearchServiceImpl searchServiceImpl;
    @GetMapping("search")
    public String searchItem(
            @RequestParam(value = "name",required = false) String name,
            @RequestParam(value = "cvs") String cvs,
            @RequestParam(value = "event") String tag,
            @RequestParam(value = "offset",required = false,defaultValue = "0") int offset,
            @RequestParam(value = "limit",required = false,defaultValue = "10") int limit,
            @RequestParam(value = "order-by",required = false) String order){

        //name에서 따음표 제거
        String processedName = PhUtility.checkName(name);
        
        //따음표 제거
        String processedCvs = PhUtility.checkName(cvs);
        //all이면 null로
        if(processedCvs.equalsIgnoreCase("all")){
            processedCvs = null;
        }

        String processedTag = PhUtility.checkName(tag);
        if(processedTag.equalsIgnoreCase("all")){
            processedTag = null;
        }

        if(offset <0){
            offset =0;
        }

        if(limit <0){
            limit =0;
        }

        if("".equals(order) || order == null){
            order = null;
        }else if(order.equalsIgnoreCase("all")){
            order = null;
        }else if(order.equalsIgnoreCase("asc")){
            order = "asc";
        } else if(order.equalsIgnoreCase("desc")){
            order = "desc";
        }


        String result = new String();

        try{
            List<ItemEntity> searchData = searchServiceImpl.searchItems(processedName,processedCvs,processedTag,offset,limit,order);
            result = PhUtility.entity2Json(searchData);
        }catch (Exception e){
            //TODO:
        }

        return result;

    }
}
