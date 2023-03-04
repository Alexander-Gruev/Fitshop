package com.fitshop.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "modifiedDate_19118051")
    private Instant modifiedDate;

    @PreUpdate
    @PrePersist
    public void beforeCreateAndUpdate() {
        this.modifiedDate = Instant.now();
    }
}
