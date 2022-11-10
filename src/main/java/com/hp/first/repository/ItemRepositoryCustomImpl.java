package com.hp.first.repository;

import com.hp.first.dto.ItemSearchDto;
import com.hp.first.dto.MainItemDto;
import com.hp.first.dto.QMainItemDto;
import com.hp.first.entity.Item;
import com.hp.first.entity.QBasicTimeEntity;
import com.hp.first.entity.QItem;
import com.hp.first.entity.QItemImg;
import com.hp.first.status.ItemStatus;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

public class ItemRepositoryCustomImpl implements ItemRepositoryCustom {

    private JPAQueryFactory queryFactory;

    public ItemRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    private BooleanExpression searchSellItemStatus(ItemStatus itemStatus) {
        return itemStatus == null ? null : QItem.item.itemStatus.eq(itemStatus);
    }

    private BooleanExpression insertDtsAfter(String searchDateType) {
        LocalDateTime dateTime = LocalDateTime.now();

        if("all".equals(dateTime) || searchDateType == null) {
            return null;
        } else if("1d".equals(dateTime)) {
            dateTime.minusDays(1);
        } else if("1w".equals(dateTime)) {
            dateTime.minusWeeks(1);
        } else if("1m".equals(dateTime)) {
            dateTime.minusMonths(1);
        } else if("6m".equals(dateTime)) {
            dateTime.minusMonths(6);
        }
        return QItem.item.insertTime.after(dateTime);
    }

    private BooleanExpression searchByLike(String searchBy, String searchQuery) {

        if("name".equals(searchBy)) {
            return QItem.item.name.contains(searchQuery);
        } else if("createdBy".equals(searchBy)) {
            return QItem.item.createdBy.contains(searchQuery);
        }
        return null;
    }

    private BooleanExpression itemNameLike(String searchQuery) {
        return StringUtils.isEmpty(searchQuery) ? null : QItem.item.name.like("%"+searchQuery+"%");
    }



    @Override
    public Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable) {
        List<Item> content = queryFactory.selectFrom(QItem.item)
                .where(insertDtsAfter(itemSearchDto.getSearchDateType()),
                        searchSellItemStatus(itemSearchDto.getItemStatus()),
                        searchByLike(itemSearchDto.getSearchBy(), itemSearchDto.getSearchQuery()))
                .orderBy(QItem.item.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory.select(Wildcard.count).from(QItem.item)
                .where(insertDtsAfter(itemSearchDto.getSearchDateType()),
                        searchSellItemStatus(itemSearchDto.getItemStatus()),
                        searchByLike(itemSearchDto.getSearchBy(), itemSearchDto.getSearchQuery()))
                .fetchOne();

        return new PageImpl<>(content, pageable, total);
    }

    @Override
    public Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable) {
        QItem qItem = QItem.item;
        QItemImg qItemImg = QItemImg.itemImg;

        List<MainItemDto> content = queryFactory
                .select(
                        new QMainItemDto(
                                qItem.id,
                                qItem.name,
                                qItem.itemDetail,
                                qItemImg.url,
                                qItem.price
                        )
                )
                .from(qItemImg)
                .join(qItemImg.item, qItem)
                .where(qItemImg.repImgYn.eq("Y"))
                .where(itemNameLike(itemSearchDto.getSearchQuery()))
                .orderBy(qItem.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory.select(Wildcard.count)
                .from(qItemImg)
                .join(qItemImg.item, qItem)
                .where(qItemImg.repImgYn.eq("Y"))
                .where(itemNameLike(itemSearchDto.getSearchQuery()))
                .fetchOne();
        return new PageImpl<>(content, pageable, total);
    }
}