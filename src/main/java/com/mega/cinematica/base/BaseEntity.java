package com.mega.cinematica.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Long id;
    protected Date createdDate;
    protected Date updateDate;
    protected boolean activityStatus;

    @PrePersist
    void onCreate(){
        this.createdDate = new Date();
        this.activityStatus = true;
    }
    @PreUpdate
    void onUpdate(){
        this.updateDate = new Date();
    }
}
