package com.scalar.SplitWise.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Timestamp;

@MappedSuperclass
@Getter
@Setter
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp createdAt;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp updatedAt;
}
