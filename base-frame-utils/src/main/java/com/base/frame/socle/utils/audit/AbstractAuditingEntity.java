package com.base.frame.socle.utils.audit;


import java.io.Serializable;
import java.time.Instant;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import org.hibernate.envers.Audited;

/**
 * Base abstract class for entities which will hold definitions for created,
 * last modified by and created, last modified by date.
 *
 * @author Alassani WILLIAM
 * @since 30-10-2017
 * @version 1.0.0
 */
@MappedSuperclass
@Audited
@EntityListeners({AuditingEntityListener.class})
public abstract class AbstractAuditingEntity implements Serializable{

    @CreatedBy
    @Column(name = "created_by", nullable = true, length = 50, updatable = false)
    private String createdBy;
    
    @CreatedDate
    @Column(name = "created_date", nullable = true)
    protected Instant createdDate = Instant.now();
    
    @LastModifiedBy
    @Column(name = "last_modified_by", length = 50)
    private String lastModifiedBy;
    
    @LastModifiedDate
    @Column(name = "last_modified_date")
    private  Instant lastModifiedDate;

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @PrePersist
    public void initCreatedDate() {
        setCreatedDate(Instant.now());
        setLastModifiedDate(Instant.now());
    }

    @PreUpdate
    public void initLastModifieddDate() {
        setLastModifiedDate(Instant.now());
    }
     
}
