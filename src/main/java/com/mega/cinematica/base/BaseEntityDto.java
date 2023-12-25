package com.mega.cinematica.base;

import javax.persistence.MappedSuperclass;
import java.util.Date;
@MappedSuperclass
public abstract class BaseEntityDto {
    protected Date createdDate;
    protected Date updateDate;
    protected boolean status;
}
