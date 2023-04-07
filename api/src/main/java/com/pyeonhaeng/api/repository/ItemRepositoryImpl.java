package com.pyeonhaeng.api.repository;

import com.pyeonhaeng.api.entity.ItemEntity;
import com.pyeonhaeng.api.entity.ItemReturnData;
import com.pyeonhaeng.api.entity.QItemEntity;
import com.pyeonhaeng.api.entity.QSyncKey;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemRepositoryImpl implements ItemRepository{

    @Autowired
    private EntityManager em;

    @Override
    public List<ItemReturnData> searchItemsbyConditions(String name, String tag, String cvs, String order, Pageable pageable,Boolean history){

        QItemEntity item = QItemEntity.itemEntity;
        QSyncKey syncKey = QSyncKey.syncKey;


        JPAQuery query =new JPAQuery(em);


        BooleanExpression syncKeyCondition = null;
        if(history) {
            syncKeyCondition = item.sync_key.ne(syncKey.month);
        }
        else{
            syncKeyCondition = item.sync_key.eq(syncKey.month);
        }


        BooleanExpression filterCondition = item.name.like("%" + name + "%");
        if(cvs != null){
            filterCondition= filterCondition.and(item.store.eq(cvs));
        }
        if(tag != null){
            filterCondition= filterCondition.and(item.tag.eq(tag));
        }



        query.select(Projections.fields(ItemReturnData.class,
                        item.sync_key.as("date"),
                        item.img,
                        item.name,
                        item.price,
                        item.store,
                        item.tag)
                )
                .from(item)
                .innerJoin(syncKey)
                .on(syncKeyCondition)
                .where(filterCondition);

        if(order == "asc"){
            query.orderBy(item.price.asc());
        } else if (order == "desc") {
            query.orderBy(item.price.desc());
        }

        query.offset(pageable.getOffset()).limit(pageable.getPageSize());

        List<ItemReturnData> result = query.fetch();

        return result;

    }

}
