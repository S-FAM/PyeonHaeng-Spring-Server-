package com.pyeonhaeng.api.service;

import com.pyeonhaeng.api.entity.ItemEntity;

import java.util.List;

public interface HistoryService {

    List<ItemEntity> lookHistory(String name, String cvs) throws Exception;
}
