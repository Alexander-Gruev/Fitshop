package com.fitshop.model.entity;

import com.fitshop.constants.Constants;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "log_19118051", schema = Constants.SCHEMA_NAME)
public class LogEntity extends BaseEntity {

    @Column(nullable = false)
    private String tableName;

    @Column(nullable = false)
    private String operationType;

    @Column(nullable = false)
    private Instant operationDateTime;

}
