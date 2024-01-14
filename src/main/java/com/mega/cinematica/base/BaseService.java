package com.mega.cinematica.base;

import java.util.List;

public interface BaseService<DTO extends BaseEntityDto>{
    DTO saveEntity(DTO e);
    void delete(DTO e);
    DTO update(DTO e);
    DTO findById(Long id);
    List<DTO> findAll();
}
