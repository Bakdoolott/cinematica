package com.mega.cinematica.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class BaseEntityDto {
    protected Long id;
    protected Date createdDate;
    protected Date updateDate;
    protected boolean activityStatus;
}
