package com.decrypto.challenge.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    protected Long id;

    @CreatedDate
    @Column(nullable = false, name = "created_at", updatable = false)
    protected LocalDateTime creationDate;

    @CreatedBy
    @Column(nullable = false, name = "created_by", updatable = false)
    protected String createdBy = "UNKNOWN";

    @LastModifiedDate
    @Column(nullable = false, name = "last_modified_at")
    protected LocalDateTime lastModifiedDate;

    @LastModifiedBy
    @Column(nullable = false, name = "last_modified_by")
    protected String lastModifiedBy = "UNKNOWN";
}
