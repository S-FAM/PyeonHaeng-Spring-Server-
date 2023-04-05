package com.pyeonhaeng.api.service;

import com.pyeonhaeng.api.entity.ItemEntity;
import com.pyeonhaeng.api.repository.ItemRepositoryImpl;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Builder
@Service
public class SearchServiceImpl implements SearchService{

    @Autowired
    private final ItemRepositoryImpl itemRepository;

    @Override
    public List<ItemEntity> searchItems(String name, String cvs, String tag, int offset, int limit, String order) throws Exception{

        List<ItemEntity> selectedItems = itemRepository.searchItemsbyConditions(name,tag,cvs,order, PageRequest.of(offset,limit));

        return selectedItems;

    }

}
