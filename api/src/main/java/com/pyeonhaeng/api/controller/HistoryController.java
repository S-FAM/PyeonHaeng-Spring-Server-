package com.pyeonhaeng.api.controller;


import com.pyeonhaeng.api.entity.ItemEntity;
import com.pyeonhaeng.api.service.HistoryServiceImpl;
import com.pyeonhaeng.api.utility.PhUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/products")
@RestController
public class HistoryController {

    private final HistoryServiceImpl historyServiceImpl;
    @GetMapping("history")
    public ResponseEntity searchItem(
            @RequestParam(value = "name",required = false) String name,
            @RequestParam(value = "cvs") String cvs){



        String processedName = PhUtility.checkName(name);
        //따음표 제거
        String processedCvs = PhUtility.checkName(cvs);
        //all이면 null로
        if(processedCvs.equalsIgnoreCase("all")){
            processedCvs = null;
        }

        String result = new String();
        int responseCount = 0;

        try{
            List<ItemEntity> searchData = historyServiceImpl.lookHistory(processedName,processedCvs);
            result = PhUtility.makeResponseJson(searchData);
            responseCount = searchData.size();
        }catch (Exception e){
            //TODO:
        }

        if(responseCount == 0){
            return new ResponseEntity(result, HttpStatus.BAD_REQUEST);
        }
        else{
            return new ResponseEntity(result, HttpStatus.OK);
        }
    }

}
