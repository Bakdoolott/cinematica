package com.mega.cinematica.base;

import java.util.List;

public interface BaseService<ED extends BaseEntityDto>{
    ED save(ED e);
    void delete(ED e);
    ED update(ED e);
    ED findById(Long id);
    List<ED> findAll();
}
