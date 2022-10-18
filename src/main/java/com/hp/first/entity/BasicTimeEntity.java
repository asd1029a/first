package com.hp.first.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(EntityListeners.class)
public abstract class BasicTimeEntity {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime insertTime;

    @LastModifiedDate
    private LocalDateTime updateTime;
}
