package com.pyeonhaeng.api.service;

import com.pyeonhaeng.api.entity.ItemEntity;

import java.util.List;

public interface SearchService {

    List<ItemEntity> searchItems(String name, String cvs, String tag, int offset, int limit, String order) throws Exception;
}
