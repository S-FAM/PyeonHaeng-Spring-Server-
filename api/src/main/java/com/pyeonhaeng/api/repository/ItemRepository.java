package com.pyeonhaeng.api.repository;

import com.pyeonhaeng.api.entity.ItemEntity;
import com.pyeonhaeng.api.entity.ItemReturnData;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository {
    public abstract List<ItemReturnData> searchItemsbyConditions(String name, String tag, String cvs, String order, Pageable pageable, Boolean history);
}
