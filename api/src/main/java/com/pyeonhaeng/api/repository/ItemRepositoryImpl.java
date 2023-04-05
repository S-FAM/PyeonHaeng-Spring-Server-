package com.pyeonhaeng.api.repository;

import com.pyeonhaeng.api.entity.ItemEntity;
import com.pyeonhaeng.api.entity.QItemEntity;
import com.pyeonhaeng.api.entity.QSyncKey;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemRepositoryImpl implements ItemRepository{

    @Autowired
    private EntityManager em;

    @Override
    public List<ItemEntity> searchItemsbyConditions(String name, String tag, String cvs, String order, Pageable pageable){

        QItemEntity item = QItemEntity.itemEntity;
        QSyncKey syncKey = QSyncKey.syncKey;


        JPAQuery query =new JPAQuery(em);

        BooleanExpression syncKeyCondition = item.sync_key.eq(syncKey.month);

        BooleanExpression filterCondition = item.name.like("%" + name + "%")
                .and(item.store.eq(cvs))
                .and(item.tag.eq(tag));

        query.select(item)
                .from(item)
                .innerJoin(syncKey)
                .on(syncKeyCondition)
                .where(filterCondition);

        if(order == "ASC"){
            query.orderBy(item.price.asc());
        } else if (order == "DESC") {
            query.orderBy(item.price.desc());
        }

        query.offset(pageable.getOffset()).limit(pageable.getPageSize());

        List<ItemEntity> result = query.fetch();

        return result;

    }

}
