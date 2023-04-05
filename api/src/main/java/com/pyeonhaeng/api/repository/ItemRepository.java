package com.pyeonhaeng.api.repository;

import com.pyeonhaeng.api.entity.ItemEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository {
    public abstract List<ItemEntity> searchItemsbyConditions(String name, String tag, String cvs, String order, Pageable pageable);
}
