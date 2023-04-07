package com.pyeonhaeng.api.service;

import com.pyeonhaeng.api.entity.ItemEntity;
import com.pyeonhaeng.api.entity.ItemReturnData;

import java.util.List;

public interface HistoryService {

    List<ItemReturnData> lookHistory(String name, String cvs) throws Exception;
}
