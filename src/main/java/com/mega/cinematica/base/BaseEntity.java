package com.mega.cinematica.base;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;
@MappedSuperclass
public abstract class BaseEntity {
    protected Date createdDate;
    protected Date updateDate;
    protected boolean status;
    @PrePersist
    void onCreate(){
        this.createdDate = new Date();
    }
    @PreUpdate
    void onUpdate(){
        this.updateDate = new Date();
    }
}
